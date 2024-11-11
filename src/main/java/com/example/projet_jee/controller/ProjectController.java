package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.service.PortfolioService;
import com.example.projet_jee.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/create", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Project> create(@RequestParam String title, @RequestParam String description, @RequestParam Long id) {
        Project project = projectService.createProject(title, description, id);
        return ResponseEntity.ok(project); // Retourne l'objet Project sous forme JSON
    }

    @PutMapping("/modif")
    public ResponseEntity<Void> update(@RequestParam String title, @RequestParam String description, @RequestParam Long id) {
        projectService.modifyProject(title, description, id);
        return ResponseEntity.ok().build(); // Retourne une réponse 200 OK
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build(); // retourne une réponse 200 OK
    }

}
