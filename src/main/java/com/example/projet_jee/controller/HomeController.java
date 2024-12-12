package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PortfolioRepository portfolioRepository;

    /*
     * Affiche la page d'accueil avec une liste de portfolios publics.
     * Retourne La vue "home" contenant les portfolios publics.
     */

    @GetMapping("/")
    public String home(ModelMap model) {
        // Récupère tous les portfolios
        List<Portfolio> portfolios = portfolioRepository.findAll();
        List<Portfolio> publicPortfolios = new ArrayList<>();

        // Filtre les portfolios pour ne conserver que ceux qui sont publics
        for( Portfolio portfolio : portfolios ){
            if(portfolio.isEstPublic()){
                publicPortfolios.add(portfolio);
            }
        }
        model.addAttribute("publicPortfolios", publicPortfolios);
        return "home";
    }

    /*
     * Affiche le style public d'un portfolio spécifique.
     * Paramètre id L'identifiant du portfolio à visualiser.
     * Retourne La vue "visualiseur" pour afficher les détails du portfolio.
     */

    @GetMapping("/visualiser")
    public String visualiser(@RequestParam Long id, Model model) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio non trouvé"));
        model.addAttribute("portfolio", portfolio);
        return "visualiseur";
    }

    /*
     * Affiche la page utilisateur.
     * Retourne La vue "user".
     */

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    /*
     * Affiche la page administrateur.
     * Retourne La vue "admin".
     */

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    /*
     * Affiche la page de connexion.
     * Retourne La vue "login".
     */

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
