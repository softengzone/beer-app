package com.beerapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerapp.domain.Brewery;
import com.beerapp.services.BreweryServiceImpl;

/**
 * This controller supports the following REST endpoints:
 * URL           | HTTP Method | Description
 * -----------------------------------------------------------------
 * /brewery      | GET         | Obtains the list of all breweries stored in the database
 * /brewery/{id} | GET         | Obtains the brewery associated with the given ID
 *  
 * @author Krzysztof Anczyk
 *
 */

@RestController
@RequestMapping("/rest")
public class BreweryController {

	private BreweryServiceImpl breweryService;
	
	@Autowired
	public BreweryController(BreweryServiceImpl service) {
		this.breweryService = service;
	}
	
	/*
	 * Retrieve Brewery by id
	 */
	@GetMapping(value="/brewery/{id}")
	public ResponseEntity<Brewery> getBreweryById(@PathVariable long id) {
		Optional<Brewery> brewery = breweryService.findById(id);
		if (brewery.isPresent()) {
			return new ResponseEntity<Brewery>(brewery.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Brewery>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * Retrieve all breweries
	 */
	@GetMapping(value="/brewery")
	public ResponseEntity<List<Brewery>> getAllBreweries() {
		List<Brewery> breweries = breweryService.findAll();
		if (breweries.isEmpty()) {
			return new ResponseEntity<List<Brewery>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Brewery>>(breweries, HttpStatus.OK);
	}
	
	
}
