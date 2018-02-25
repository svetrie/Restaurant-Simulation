package com.example;

public class Market {
    private Food[] marketFoods;
    private Equipment[] marketEquipment;
    private Recipe[] marketRecipes;

    public Food getFoodByName(String foodName) {
        for (Food food : marketFoods) {
            if (food.getName().equalsIgnoreCase(foodName)) {
                return food;
            }
        }
        return null;
    }

    public Equipment getEquipmentByName(String equipmentName) {
        for (Equipment equipment : marketEquipment) {
            if (equipment.getName().equalsIgnoreCase(equipmentName)) {
                return equipment;
            }
        }
        return null;
    }

    public Recipe getRecipeByName(String recipeName) {
        for (Recipe recipe : marketRecipes) {
            if (recipe.getCookedDish().getName().equalsIgnoreCase(recipeName)) {
                return recipe;
            }
        }
        return null;
    }
}
