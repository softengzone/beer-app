package com.beerapp.services;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T, ID> {

	Optional<T> findById(ID id);
	
	List<T> findAll();
	
	void save(T t);
	
	void delete(T t);
	
	void deleteAll();
	
}
