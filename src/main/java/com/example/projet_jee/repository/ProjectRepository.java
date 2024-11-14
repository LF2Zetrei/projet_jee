package com.example.projet_jee.repository;

import com.example.projet_jee.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {

}
