package com.example.projet_jee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    public Portfolio() {
        this.publishedOn = LocalDateTime.now();
    }

    public Portfolio(String title, String description) {
        this.title = title;
        this.description = description;
        this.publishedOn = LocalDateTime.now();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Portfolio portfolio = (Portfolio) object;
        return id == portfolio.id && Objects.equals(title, portfolio.title) && Objects.equals(description, portfolio.description) && Objects.equals(publishedOn, portfolio.publishedOn) && Objects.equals(updatedOn, portfolio.updatedOn) && Objects.equals(projects, portfolio.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, publishedOn, updatedOn, projects);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishedOn=" + publishedOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
