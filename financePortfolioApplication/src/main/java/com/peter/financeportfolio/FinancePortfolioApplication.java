package com.peter.financeportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.peter.financeportfolio.model") // Specify the base package for entities
@EnableJpaRepositories("com.peter.financeportfolio.repository") // Specify the base package for repositories
public class FinancePortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancePortfolioApplication.class, args);
	}
}

