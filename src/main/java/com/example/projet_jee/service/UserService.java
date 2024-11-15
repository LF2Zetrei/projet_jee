package com.example.projet_jee.service;

import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        User user = new User(username, passwordEncoder.encode(password), "ROLE_USER");
        userRepository.save(user);
    }

    public User getUserbyUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void modifyUser(String username, Long id) {
        User user = userRepository.findById(id).get();
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }
        userRepository.save(user);
    }
}
