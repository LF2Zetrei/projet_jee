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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PortfolioRepository portfolioRepository;

    @GetMapping("/")
    public String home(ModelMap model) {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        List<Portfolio> publicPortfolios = new ArrayList<>();
        for( Portfolio portfolio : portfolios ){
            if(portfolio.isEstPublic()){
                publicPortfolios.add(portfolio);
            }
        }
        model.addAttribute("publicPortfolios", publicPortfolios);
        return "home";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
