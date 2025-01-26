package com.quesedex.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.quesedex.entities.Cheese;
import com.quesedex.models.CheeseFilter;

@Repository
public class CustomCheeseRepositoryImpl implements CustomCheeseRepository {
	
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Cheese> findCheesesByFilter(CheeseFilter filter) {
    	Query query = new Query();
    	
    	if(filter.getName() != null && !filter.getName().isEmpty()) {
    		query.addCriteria(Criteria.where("name").regex(filter.getName(), "i"));
    	}
    	
    	if(filter.getOrigin() != null && !filter.getOrigin().isEmpty()) {
    		query.addCriteria(Criteria.where("origin").regex(filter.getOrigin(), "i"));
    	}
    	
    	return mongoTemplate.find(query, Cheese.class);
    }
}
