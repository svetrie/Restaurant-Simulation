package com.example;

public class Recipe {
    private String[] ingredients;
    private String[] requiredEquipments;
    private Food cookedDish;
    private double baseValue;
    private double timeToCook;

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getRequiredEquipments() {
        return requiredEquipments;
    }

    public Food getCookedDish() {
        return cookedDish;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getTimeToCook() {
        return timeToCook;
    }
}
