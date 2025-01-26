package com.quesedex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import com.quesedex.dto.CheeseDTO;
import com.quesedex.entities.Cheese;

@Mapper(componentModel = "spring")
public interface CheeseMapper {
	
	@Mapping(target = "id", ignore = true)
    Cheese toEntity(CheeseDTO cheeseDTO);
	
    CheeseDTO toDto(Cheese cheese);
    
	@Mapping(target = "id", ignore = true)
    void updateCheeseFromDto(CheeseDTO cheeseDTO, @MappingTarget Cheese cheese);

}
