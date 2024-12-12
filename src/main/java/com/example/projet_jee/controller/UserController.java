package com.example.projet_jee.controller;

import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.UserRepository;
import com.example.projet_jee.service.PortfolioService;
import com.example.projet_jee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/profil")
    public String profil(@RequestParam String username, Model model) {
        model.addAttribute("user", userService.getUserbyUsername(username));
        return "profil";
    }

    @PutMapping("/modif")
    public ResponseEntity<Void> update(@RequestParam String username, @RequestParam UUID id) {
        userService.modifyUser(username, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/genererCode")
    public ResponseEntity<Void> generateFriendsCode(Principal principal) {
        try {
            String username = principal.getName();
            userService.generateFriendsCode(username);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/partager")
    public ResponseEntity<Void> partager(@RequestParam Long portfolio_id, @RequestParam String friendCode) {
        System.out.println("Requête reçue : portfolio_id=" + portfolio_id + ", friendCode=" + friendCode);
        userService.shareWithFriend(friendCode, portfolio_id);
        return ResponseEntity.ok().build();
    }

}
