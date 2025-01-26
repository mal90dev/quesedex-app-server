package com.quesedex.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "cheeses")
public class Cheese {
	@Id
	public String id;
	@NotEmpty(message = "Name is required")
	public String name;
    public String origin;
    public String milkType;
    public String pasteurisation;
    public String maturation;
    public String texture;
    public String flavor;
    public String aroma;
    public String shape;
    public String color;
    public String rind;
    public boolean multiMilk;
    public String dopIgp;
    public String fatContent;
    public String moistureContent;
    public String additives;
    public String culinaryUses;
    public String historyAndTradition;
}
