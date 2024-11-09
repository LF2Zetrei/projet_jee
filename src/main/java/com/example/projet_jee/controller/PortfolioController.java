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
import java.util.Optional;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioRepository portfolios;

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

    @GetMapping("/modifPortfolio")
    public String modifPortfolio(@RequestParam String id, Model model){
        Portfolio portfolio = portfolioRepository.findById(Long.valueOf(id)).get();
        model.addAttribute("portfolio", portfolio);
        return "modifPortfolio";
    }

    @PostMapping("/modifPortfolio")
    public String createPortfolio(@RequestParam String title, @RequestParam String description, Model model) {
        Portfolio newPortfolio = portfolioService.createPortfolio(title, description);
        return "redirect:/portfolios/modifPortfolio?id=" + newPortfolio.getId();
    }


    @GetMapping("/{id}")
    public Portfolio findById(@PathVariable("id") Portfolio portfolio){
        return portfolio;
    }



}
