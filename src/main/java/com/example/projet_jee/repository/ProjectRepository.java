package com.example.projet_jee.repository;

import com.example.projet_jee.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
