package com.example.projet_jee.service;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.ProjectRepository;
import com.example.projet_jee.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;

    public Portfolio createPortfolio(String title, String description, User user){
        Portfolio portfolio = new Portfolio(title, description);
        portfolio.getOwners().add(user);
        user.getPortfolios().add(portfolio);
        portfolioRepository.save(portfolio);
        user.getPortfolios().add(portfolio);
        userRepository.save(user);
        return portfolio;
    }

    public void setPublic(Portfolio portfolio){
        portfolio.setEstPublic(!portfolio.isEstPublic());
        portfolioRepository.save(portfolio);
    }

    public void modifyPortfolio(String title , String description, Long id){
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new RuntimeException("Portfolio introuvable"));
        if (title != null && !title.isEmpty()) {
            portfolio.setTitle(title);
        }
        if (description != null && !description.isEmpty()) {
            portfolio.setDescription(description);
        }
        portfolioRepository.save(portfolio);

    }

    public void deletePortfolio(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio introuvable pour l'id : " + id));
        // Supprime tous les projets associés au Portfolio
        List<Project> projects = portfolio.getProjects();
        if (!projects.isEmpty()){
            for (Project project : projects) {
                projectService.deleteProject(project.getId());
            }
        }
        // Ensuite, supprime le Portfolio
        portfolioRepository.delete(portfolio);
    }

    public boolean isFirstOwner(Long portfolio_id, UUID user_id){
        Portfolio portfolio = portfolioRepository.findById(portfolio_id).orElseThrow(() -> new RuntimeException("Portfolio introuvable"));
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User introuvable"));
        return portfolio.getOwners().get(0).getId().equals(user.getId()) || Objects.equals(user.getRoles(), "ROLE_ADMIN");
    }
}
