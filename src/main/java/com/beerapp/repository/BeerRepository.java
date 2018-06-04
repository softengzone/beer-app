package com.beerapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beerapp.domain.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
	
	Optional<Beer> findBeerByName(String name);
	
}
