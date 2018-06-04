package com.beerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beerapp.domain.Brewery;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {

}
