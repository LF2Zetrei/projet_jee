package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPortfolio(@RequestParam Long id, Model model) {
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        model.addAttribute("portfolio", portfolio);
        return "editPortfolioAdmin";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Portfolio> updatePortfolio(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam String description
    ) {
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        if (portfolio != null) {
            portfolio.setTitle(title);
            portfolio.setDescription(description);
            portfolioRepository.save(portfolio);
        }
        return ResponseEntity.ok(portfolio);
    }
}
