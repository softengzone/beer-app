package com.beerapp;

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
	
	public static void main(String[] args) {
		SpringApplication.run(BeerApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
}
