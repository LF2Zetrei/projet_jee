package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioRepository portfolios;

    @Autowired
    PortfolioService portfolioService;

    public PortfolioController(PortfolioRepository portfolioRepository) {
        this.portfolios = portfolioRepository;
    }

    @GetMapping
    public String showAllPortfolios(Model model){
        List<Portfolio> portfolios = this.portfolios.findAll();
        model.addAttribute("portfolios", portfolios);
        return "portfolios";
    }

    @GetMapping("/modifPortfolio")
    public String modifPortfolio(){
        return "modifPortfolio";
    }

    @PostMapping("/modifPortfolio")
    public String createPortfolio(@RequestParam String title, @RequestParam String description, Model model) {
        Portfolio newPortfolio = portfolioService.createPortfolio(title, description);
        model.addAttribute("portfolio", newPortfolio);
        return "modifPortfolio"; // Charge la page modifPortfolio avec le portfolio nouvellement créé
    }


    @GetMapping("/{id}")
    public Portfolio findById(@PathVariable("id") Portfolio portfolio){
        return portfolio;
    }



}
