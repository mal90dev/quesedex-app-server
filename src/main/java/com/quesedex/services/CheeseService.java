package com.quesedex.services;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.quesedex.entities.Cheese;
import com.quesedex.models.CheeseFilter;

public interface CheeseService {
	
	public List<Cheese> findAllCheeses();
	
	public List<Cheese> findCheesesByFilter(CheeseFilter filter);
	
	public List<Cheese> findCheesesByName(String name);
	
	public Optional<Cheese> findCheeseById(String id);
	
	public Cheese addCheese(Cheese cheese);
	
	public Cheese updateCheese(Cheese cheese);
	
	public void deleteCheese(String id);
	
	public Page<Cheese> findAllByPage(Pageable pageable);

}
