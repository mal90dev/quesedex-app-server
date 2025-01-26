package com.quesedex.repository;

import java.util.List;

import com.quesedex.entities.Cheese;
import com.quesedex.models.CheeseFilter;

public interface CustomCheeseRepository {
	List<Cheese> findCheesesByFilter(CheeseFilter filters);
}
