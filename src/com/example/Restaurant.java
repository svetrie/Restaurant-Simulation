package com.example;

import java.util.ArrayList;

public class Restaurant {
    private static final int CLOSING_TIME = 0;
    private static final int OPENING_TIME = 6;
    private static final double SALE_MULTIPLIER = 1.5;
    private ArrayList<Food> menu;
    private ArrayList<Food> foodInventory;
    private ArrayList<Recipe> recipes;
    private ArrayList<Equipment> equipment;
    private double wealth;
    private int peakLunchHr;
    private int peakBreakfastHr;
    private int peakDinnerHr;

    public double getWealth() {
        return wealth;
    }

    public void setWealth(double wealth) {
        this.wealth = wealth;
    }

    public ArrayList<Food> getFoodInventory() {
        return foodInventory;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void addToMenu(Food food) {
        menu.add(food);
    }

    public void removeFromMenu(String foodName) {
        if (menu.contains(getFoodByName(foodName))) {
            menu.remove(getFoodByName(foodName));
        } else {
            System.out.println(foodName + " cannot be removed from the menu");
        }
    }

    public Food getFoodByName(String foodName) {
        for (Food food : foodInventory) {
            if (food.getName().equalsIgnoreCase(foodName)) {
                return food;
            }
        }
        return null;
    }

    public Equipment getEquipmentByName(String equipmentName) {
        for (Equipment equipment : equipment) {
            if (equipment.getName().equalsIgnoreCase(equipmentName)) {
                return equipment;
            }
        }
        return null;
    }

    public Recipe getRecipeByName(String recipeName) {
        for (Recipe recipe : recipes) {
            if (recipe.getCookedDish().getName().equalsIgnoreCase(recipeName)) {
                return recipe;
            }
        }
        return null;
    }

    public void printMenu() {
        for (Food food : menu) {
            System.out.println("Entree: " + food + " " + food.getBaseValue() * SALE_MULTIPLIER);
        }
    }
}
