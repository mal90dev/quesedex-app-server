package com.quesedex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quesedex.entities.Cheese;
import com.quesedex.exception.ResourceNotFoundException;
import com.quesedex.models.CheeseFilter;
import com.quesedex.repository.CheeseRepository;

@Service
public class CheeseServiceImpl implements CheeseService{
	
	@Autowired
	private CheeseRepository cheeseRepository;
	
	@Override
	public List<Cheese> findAllCheeses() {
		return cheeseRepository.findAll();
	}

	@Override
	public Optional<Cheese> findCheeseById(String id) {
		return cheeseRepository.findById(id);
	}
	
	@Override
	public Cheese addCheese(Cheese cheese) {
		return cheeseRepository.save(cheese);
	}
	
	@Override
	public Cheese updateCheese(Cheese cheese) {
		return cheeseRepository.save(cheese);
	}

	@Override
	public void deleteCheese(String id) {
		Cheese cheeseToRemove = findCheeseById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cheese not found with id " + id));
		cheeseRepository.delete(cheeseToRemove);
	}

	@Override
	public List<Cheese> findCheesesByName(String name) {
		return cheeseRepository.findByName(name);
	}

	@Override
	public List<Cheese> findCheesesByFilter(CheeseFilter filter) {
		return cheeseRepository.findCheesesByFilter(filter);
	}

	@Override
	public Page<Cheese> findAllByPage(Pageable pageable) {
		return cheeseRepository.findAll(pageable);
	}
	
}
