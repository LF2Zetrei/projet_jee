package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.UserRepository;
import com.example.projet_jee.service.PortfolioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioRepository portfolios;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioService portfolioService;
    @Autowired
    private PortfolioRepository portfolioRepository;

    public PortfolioController(PortfolioRepository portfolioRepository) {
        this.portfolios = portfolioRepository;
    }

    @GetMapping
    public String showAllPortfolios(Model model){
        List<Portfolio> portfolios = this.portfolios.findAll();
        model.addAttribute("portfolios", portfolios);
        return "portfolios";
    }

    @GetMapping("/myPortfolio")
    public String getPortfolioByUsername(Principal principal, Model model){
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        model.addAttribute("portfolios", user.getPortfolios());
        return "portfolios";
    }

    @GetMapping("/modifPortfolio")
    public String modifPortfolio(@RequestParam String id, Model model){
        Portfolio portfolio = portfolioRepository.findById(Long.valueOf(id)).get();
        model.addAttribute("portfolio", portfolio);
        return "modifPortfolio";
    }

    @PostMapping(value ="/modifPortfolio", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Portfolio> createPortfolio(@RequestParam String title, @RequestParam String description, Model model, Principal principal) {
        try {
            String username = principal.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

            // Créer un nouveau portfolio liée à son owner
            Portfolio newPortfolio = portfolioService.createPortfolio(title, description, user);
            return ResponseEntity.ok(newPortfolio);
        } catch (EntityNotFoundException e) {
            System.err.println("Erreur serveur lors de la création : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/{id}")
    public Portfolio findById(@PathVariable("id") Portfolio portfolio){
        return portfolio;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id, Principal principal) {
        try {
            // Récupération de l'utilisateur connecté via Principal
            String username = principal.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

            // Vérifier si le portfolio appartient à cet utilisateur
            Portfolio portfolio = portfolioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Portfolio introuvable"));

            if (!portfolio.getOwners().contains(user)) {
                System.err.println("Utilisateur non autorisé à supprimer ce portfolio.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Suppression du portfolio
            portfolioService.deletePortfolio(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.err.println("Erreur serveur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
