package com.quesedex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import com.quesedex.entities.Cheese;

@Repository
public interface CheeseRepository extends MongoRepository<Cheese, String>, CustomCheeseRepository {
	
	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<Cheese> findByName(String name);
	
}
