package com.quesedex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CheeseFilter {
	@Schema(description = "Name of cheese")
	public String name;
    @Schema(description = "Origin of cheese")
	public String origin;
}
