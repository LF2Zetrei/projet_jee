package com.example.projet_jee.controller;
import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.ProjectRepository;
import com.example.projet_jee.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/portfolios/projects")
public class ProjectAdminController {

    private final ProjectRepository projectRepository;
    private final PortfolioRepository portfolioRepository;

    public ProjectAdminController(ProjectRepository projectRepository, PortfolioRepository portfolioRepository) {
        this.projectRepository = projectRepository;
        this.portfolioRepository = portfolioRepository;
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        try {
            // Vérifier si le portfolio existe avant de le supprimer
            Optional<Project> project = projectRepository.findById(id);
            if (project.isPresent()) {
                projectRepository.deleteById(id);
                return ResponseEntity.ok("Project deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
            }
        } catch (Exception e) {
            // Log de l'erreur pour aider au diagnostic
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne lors de la suppression");
        }
    }
}
