package com.quesedex.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;

import com.quesedex.dto.CheeseDTO;
import com.quesedex.entities.Cheese;
import com.quesedex.exception.ResourceNotFoundException;
import com.quesedex.mapper.CheeseMapper;
import com.quesedex.models.CheeseFilter;
import com.quesedex.services.CheeseService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cheese")
public class CheeseController {
	
	@Autowired
	private CheeseService cheeseService;
	
    @Autowired
    private PagedResourcesAssembler<Cheese> pagedResourcesAssembler;
	
	@Autowired
	private CheeseMapper cheeseMapper;
	
	@Operation(summary = "List cheeses")
	@GetMapping("/list")
	public ResponseEntity<List<Cheese>> getAllCheeses() {
		List<Cheese> cheeses = new ArrayList<>();
		cheeses = cheeseService.findAllCheeses();
		return new ResponseEntity<List<Cheese>>(cheeses, HttpStatus.OK);
	}
	
	@Operation(summary = "List cheeses by name")
	@GetMapping("/listByName")
	public ResponseEntity<List<Cheese>> getCheesesByName(@RequestParam String name) {
		List<Cheese> cheeses = new ArrayList<>();
		cheeses = cheeseService.findCheesesByName(name);
		return new ResponseEntity<List<Cheese>>(cheeses, HttpStatus.OK);
	}
	
	@Operation(summary = "List cheeses with filters")
	@PostMapping("/listFilter")
	public ResponseEntity<List<Cheese>> getCheesesByFilter(@RequestBody CheeseFilter filter) {
		List<Cheese> cheeses = new ArrayList<>();
		cheeses = cheeseService.findCheesesByFilter(filter);
		return new ResponseEntity<List<Cheese>>(cheeses, HttpStatus.OK);
	}
	
	@Operation(summary = "Get cheese by id")
    @GetMapping("/{id}")
	public ResponseEntity<Cheese> getCheeseById(@PathVariable String id) {
    	 Cheese cheese = cheeseService.findCheeseById(id)
    			 .orElseThrow(() -> new ResourceNotFoundException("Cheese not found with id " + id));
    	 return ResponseEntity.ok(cheese);
	}
    
	@Operation(summary = "Add cheese")
    @PostMapping
    public ResponseEntity<Cheese> addCheese(@Valid @RequestBody CheeseDTO cheeseDTO) {
    	Cheese cheese = cheeseMapper.toEntity(cheeseDTO);
    	Cheese cheeseResponse = cheeseService.addCheese(cheese);
    	return ResponseEntity.ok(cheeseResponse);	
    }
    
	@Operation(summary = "Modify cheese")
    @PutMapping("/{id}")
    public ResponseEntity<Cheese> updateCheese(@PathVariable String id, @RequestBody CheeseDTO cheeseDTO) {
    	Cheese cheese = cheeseMapper.toEntity(cheeseDTO);
    	Cheese existingCheese = cheeseService.findCheeseById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Cheese not found with id " + id));

    	BeanUtils.copyProperties(cheese, existingCheese, "id");

        Cheese updatedCheese = cheeseService.updateCheese(existingCheese);
        return ResponseEntity.ok(updatedCheese);
    }
	
	@Operation(summary = "Remove cheese")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCheese(@PathVariable String id) {
    	cheeseService.deleteCheese(id);
    	return ResponseEntity.noContent().build();
    }
	
	@Operation(summary = "Get cheeses with paginator")
	@GetMapping("/cheeses")
	public ResponseEntity<PagedModel<EntityModel<Cheese>>> getCheeses(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "6") int sizePerPage,
			@RequestParam(defaultValue = "ID") String sortField,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
			
		Pageable pageable = PageRequest.of(page, sizePerPage, sortDirection, sortField);
		Page<Cheese> cheesePage = cheeseService.findAllByPage(pageable);
		PagedModel<EntityModel<Cheese>> pagedModel = pagedResourcesAssembler.toModel(cheesePage,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CheeseController.class).getCheeses(page, sizePerPage, sortField, sortDirection)).withSelfRel());
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}

}
