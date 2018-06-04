package com.beerapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Project: Beer App
 * ver: 1.0
 * @author Krzysztof Anczyk *
 */

@SpringBootApplication
@ComponentScan("com.beerapp")
public class BeerApp implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(BeerApp.class);

	public static void main(String[] args) {
		SpringApplication.run(BeerApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
}
