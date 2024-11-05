package com.example.projet_jee.service;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public void createPortfolio(String title, String description){
        Portfolio portfolio = new Portfolio(title, description);
        portfolioRepository.save(portfolio);
    }
}
