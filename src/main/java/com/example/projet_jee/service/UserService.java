package com.example.projet_jee.service;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private PortfolioRepository portfolioRepository;

    public void registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Le nom d'utilisateur est déjà pris.");
        }
        User user = new User(username, passwordEncoder.encode(password), "ROLE_USER");
        userRepository.save(user);
    }

    public User getUserbyUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void modifyUser(String username, UUID id) {
        User user = userRepository.findById(id).get();
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }
        userRepository.save(user);
    }

    public void generateFriendsCode(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("L'utilisateur n'existe pas"));
        if (Objects.equals(user.getCodeAmi(), "") || user.getCodeAmi().isEmpty()) {
            String generatedCode = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
            user.setCode_ami(generatedCode);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Le code ami a déjà été généré");
        }
    }

    public void shareWithFriend(String codeAmi, Long portfolio_id) {
        Portfolio portfolio = portfolioRepository.findById(portfolio_id)
                .orElseThrow(() -> new IllegalArgumentException("Le portfolio n'existe pas"));

        User user = userRepository.findByCodeAmi(codeAmi)
                .orElseThrow(() -> new IllegalArgumentException("Le codeAmi n'existe pas"));

        user.getPortfolios().add(portfolio);

        user.setCode_ami("");

        userRepository.save(user);

        portfolio.getOwners().add(user);
        portfolioRepository.save(portfolio);
    }
}
