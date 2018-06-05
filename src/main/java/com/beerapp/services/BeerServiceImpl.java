package com.beerapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerapp.domain.Beer;
import com.beerapp.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {

	private BeerRepository beerRepository;
	
	@Autowired
	public BeerServiceImpl(BeerRepository repository) {
		this.beerRepository = repository;
	}

	@Override
	public Optional<Beer> findById(Long id) {
		return beerRepository.findById(id);
	}

	@Override
	public List<Beer> findAll() {
		return beerRepository.findAll();
	}

	@Override
	public Beer save(Beer beer) {
		return beerRepository.save(beer);
	}

	@Override
	public void delete(Beer beer) {
		beerRepository.delete(beer);
	}

	@Override
	public void deleteAll() {
		beerRepository.deleteAll();
	}

	@Override
	public boolean doesBeerExist(String name) {
		Optional<Beer> beer = beerRepository.findBeerByName(name);
		if (beer.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public long getTotalNumberOfBeers() {
		return beerRepository.count();
	}
	
	
}
