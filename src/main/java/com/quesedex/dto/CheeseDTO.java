package com.quesedex.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CheeseDTO {
    @NotEmpty(message = "Name is required")
    private String name;
    private String origin;
    private String milkType;
    private String pasteurisation;
    private String maturation;
    private String texture;
    private String flavor;
    private String aroma;
    private String shape;
    private String color;
    private String rind;
    private boolean multiMilk;
    private String dopIgp;
    private String fatContent;
    private String moistureContent;
    private String additives;
    private String culinaryUses;
    private String historyAndTradition;
}
