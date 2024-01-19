package com.peter.financeportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import javax.persistence.Entity;

import java.io.IOException;

@SpringBootApplication
@EntityScan("com.peter.financeportfolio.model.*")
@EnableJpaRepositories("com.peter.financeportfolio.repository.*")
@ComponentScan("com.peter.financeportfolio")
public class FinancePortfolioApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinancePortfolioApplication.class, args);
	}

}
