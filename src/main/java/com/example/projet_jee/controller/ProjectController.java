package com.example.projet_jee.controller;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.service.PortfolioService;
import com.example.projet_jee.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public String create(@RequestParam String title, @RequestParam String description, @RequestParam Long id, Model model) {
        projectService.createProject(title, description, id);
        return "redirect:/portfolios/modifPortfolio?id=" + id;
    }

}
