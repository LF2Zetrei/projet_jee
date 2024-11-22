package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/portfolios")
public class PortfolioAdminController {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioService portfolioService;

    public PortfolioAdminController(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String showAllPortfoliosAdmin(Model model) {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        model.addAttribute("portfolios", portfolios);
        return "portfoliosAdmin";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> deletePortfolio(@PathVariable Long id) {
        try {
            // Vérifier si le portfolio existe avant de le supprimer
            Optional<Portfolio> portfolio = portfolioRepository.findById(id);
            if (portfolio.isPresent()) {
                portfolioRepository.deleteById(id);
                return ResponseEntity.ok("Portfolio deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Portfolio not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne lors de la suppression");
        }
    }

}
