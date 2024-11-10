package com.example.projet_jee.repository;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
