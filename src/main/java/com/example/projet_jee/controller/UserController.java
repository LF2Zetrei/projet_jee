package com.example.projet_jee.controller;

import com.example.projet_jee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/profil")
    public String profil(@RequestParam String username, Model model) {
        model.addAttribute("user", userService.getUserbyUsername(username));
        return "profil";
    }


}
