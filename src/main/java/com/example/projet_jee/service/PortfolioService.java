package com.example.projet_jee.service;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Portfolio createPortfolio(String title, String description, User user){
        Portfolio portfolio = new Portfolio(title, description);
        portfolio.getOwners().add(user);
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    public void deletePortfolio(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio introuvable pour l'id : " + id));
        // Supprime tous les projets associés au Portfolio
        projectRepository.deleteAll(portfolio.getProjects());
        // Ensuite, supprime le Portfolio
        portfolioRepository.delete(portfolio);
    }
}
