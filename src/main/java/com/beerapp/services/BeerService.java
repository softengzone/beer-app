package com.beerapp.services;

import com.beerapp.domain.Beer;

public interface BeerService extends AbstractService<Beer, Long> {
	
	boolean doesBeerExist(String name);
	
	long getTotalNumberOfBeers();
	
}
