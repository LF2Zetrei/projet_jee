package com.example.projet_jee;

import com.example.projet_jee.model.Portfolio;
import com.example.projet_jee.model.Project;
import com.example.projet_jee.model.User;
import com.example.projet_jee.repository.PortfolioRepository;
import com.example.projet_jee.repository.ProjectRepository;
import com.example.projet_jee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjetJeeApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJeeApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository users;
	@Autowired
	private ProjectRepository projects;
	@Autowired
	private PortfolioRepository portfolios;



	@Override
	public void run(ApplicationArguments args) throws Exception {
		users.save(new User("user", passwordEncoder.encode("password"), "ROLE_USER"));
		users.save(new User("admin", passwordEncoder.encode("password"), "ROLE_ADMIN"));
		Portfolio portfolio = new Portfolio();
		portfolio.setTitle("portfolio1");
		portfolio.setDescription("description portfolio 1");

		portfolio = portfolios.save(portfolio);
		System.out.println(portfolio);

		Project project = new Project();
		project.setTitle("project1");
		project.setDescription("description project1");

		project = projects.save(project);
		portfolio.getProjects().add(project);
		project.setPortfolio(portfolio);
		System.out.println(project);

		Project project2 = new Project();
		project2.setDescription("description project2");
		project2.setTitle("project2");


		project2 = projects.save(project2);
		portfolio.getProjects().add(project2);
		project2.setPortfolio(portfolio);
		System.out.println(project2);

		System.out.println(portfolio);

		projects.save(project2);
		projects.save(project);
		portfolios.save(portfolio);
	}
}
