package com.beerapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.beerapp.domain.Brewery;
import com.beerapp.repository.BreweryRepository;

@Service
public class BreweryServiceImpl implements BreweryService {

	private BreweryRepository breweryRepository;
	
	public BreweryServiceImpl(BreweryRepository repository) {
		this.breweryRepository = repository;
	}

	@Override
	public Optional<Brewery> findById(Long id) {
		return breweryRepository.findById(id);
	}

	@Override
	public List<Brewery> findAll() {
		return breweryRepository.findAll();
	}

	@Override
	public Brewery save(Brewery brewery) {
		return breweryRepository.save(brewery);
	}

	@Override
	public void delete(Brewery brewery) {
		breweryRepository.delete(brewery);
	}

	@Override
	public void deleteAll() {
		breweryRepository.deleteAll();
	}	
	

}
