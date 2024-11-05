package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioRepository portfolios;

    public PortfolioController(PortfolioRepository portfolioRepository) {
        this.portfolios = portfolioRepository;
    }

    @GetMapping
    public String showAllPortfolios(Model model){
        List<Portfolio> portfolios = this.portfolios.findAll();
        model.addAttribute("portfolios", portfolios);
        return "portfolios";
    }

    @GetMapping("/{id}")
    public Portfolio findById(@PathVariable("id") Portfolio portfolio){
        return portfolio;
    }
}
