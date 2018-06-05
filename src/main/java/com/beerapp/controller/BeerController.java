package com.beerapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beerapp.domain.Beer;
import com.beerapp.errors.ErrorMessage;
import com.beerapp.services.BeerServiceImpl;
import com.beerapp.utils.SingleValueWrapper;

/**
 * This controller support the following REST endpoints:
 * URL        | HTTP Method | Description
 * -----------------------------------------------------------------
 * /beer      | GET         | Obtains the list of all beers stored in the database
 * /beer      | POST        | Creates a new beer entry
 * /beer/{id} | GET         | Obtains the beer associated with the given ID
 * /beer/{id} | PUT         | Updates an existing beer
 * /beer/{id} | DELETE      | Deletes a beer with the given ID
 * /beer      | DELETE      | Deletes all beers
 *  
 * @author Krzysztof Anczyk
 *
 */
@RestController
@RequestMapping(value = "/rest")
public class BeerController {

	private BeerServiceImpl beerService;
	
	@Autowired
	public BeerController(BeerServiceImpl service) {
		this.beerService = service;
	}
	
	
	/*
	 * Retrieve Beer by id
	 */
	@GetMapping(value="/beer/{id}")
	public ResponseEntity<Beer> getBeerById(@PathVariable long id) {
		Optional<Beer> beer = beerService.findById(id);
		if (beer.isPresent()) {
			return new ResponseEntity<Beer>(beer.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Beer>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * Retrieve all beers
	 */
	@GetMapping(value="/beer")
	public ResponseEntity<List<Beer>> getAllBeers() {
		List<Beer> beers = beerService.findAll();
		if (beers.isEmpty()) {
			return new ResponseEntity<List<Beer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Beer>>(beers, HttpStatus.OK);
	}
	
	/*
	 * Create a new beer 
	 */
	@PostMapping(value="/beer")
	public ResponseEntity<?> createBeer(@RequestBody Beer beer, UriComponentsBuilder builder) {
		if (beerService.doesBeerExist(beer.getName())) {
			return new ResponseEntity<>(new ErrorMessage("Beer with name " + beer.getName() + " already exist."), HttpStatus.CONFLICT);
		}
		if (beer.getId() != null) {
			Optional<Beer> _beer = beerService.findById(beer.getId());
			if (_beer.isPresent()) {
				return new ResponseEntity<>(new ErrorMessage("Beer with id " + beer.getId() + " already exist."), HttpStatus.CONFLICT);
			}
		}
		beerService.save(beer);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/rest/beer/{id}").buildAndExpand(beer.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	/*
	 * Update a beer
	 */
	@PutMapping(value="/beer/{id}")
	public ResponseEntity<?> updateBeer(@PathVariable long id, @RequestBody Beer beer) {
		Optional<Beer> foundBeer = beerService.findById(id);
		if (!foundBeer.isPresent()) {
			return new ResponseEntity<>(new ErrorMessage("Beer with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		Beer _beer = foundBeer.get();
		_beer.setName(beer.getName());
		_beer.setDescription(beer.getDescription());
		_beer.setBreweryId(beer.getBreweryId());
		_beer.setAbv(beer.getAbv());
		
		beerService.save(_beer);
		
		return new ResponseEntity<Beer>(_beer, HttpStatus.OK);
	}
	
	/*
	 * Delete a beer
	 */
	@DeleteMapping(value="/beer/{id}")
	public ResponseEntity<?> deleteBeer(@PathVariable long id) {
		Optional<Beer> beer = beerService.findById(id);
		if (!beer.isPresent()) {
			return new ResponseEntity<>(new ErrorMessage("Beer with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		beerService.delete(beer.get());
		return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
	}
	
	/*
	 * Delete all beers
	 */
	@DeleteMapping(value="/beer")
	public ResponseEntity<?> deleteAllBeers() {
		beerService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/*
	 * Retrieve total number of beers
	 */
	@GetMapping(value="/beer/total")
	public ResponseEntity<SingleValueWrapper> getTotalNumberOfBeers() {
		return new ResponseEntity<>(new SingleValueWrapper(String.valueOf(beerService.getTotalNumberOfBeers())), HttpStatus.OK);
	}
	
	
}
