package com.example.projet_jee.controller;

import com.example.projet_jee.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class modifPortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/modifPortfolio")
    public String modifPortfolio(){
        return "modifPortfolio";
    }

    @PostMapping("/modifPortfolio")
    public String createPortfolio(@RequestParam String title, @RequestParam String description){
        portfolioService.createPortfolio(title, description);
        return "redirect:/modifPortfolio";
    }
}
