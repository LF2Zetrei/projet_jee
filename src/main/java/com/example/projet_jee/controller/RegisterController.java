package com.example.projet_jee.controller;

import com.example.projet_jee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class
RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // Affiche le formulaire d'enregistrement
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Redirige vers register.html
    }

    // Gère la soumission du formulaire d'enregistrement
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return "redirect:/login"; // Redirige vers la page de connexion après inscription
    }
}

