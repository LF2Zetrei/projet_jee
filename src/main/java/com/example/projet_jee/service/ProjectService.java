package com.example.projet_jee.service;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void createProject(String title, String description, Long id){
        Project project = new Project(title, description);
        project.setPortfolio(portfolioRepository.findById(id).orElseThrow());
        projectRepository.save(project);
    }

    public void modifyProject(String title, String description, Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet introuvable"));
        if (title != null && !title.isEmpty()) {
            project.setTitle(title);
        }
        if (description != null && !description.isEmpty()) {
            project.setDescription(description);
        }
        projectRepository.save(project);
    }
}
