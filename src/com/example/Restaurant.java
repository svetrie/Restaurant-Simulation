package com.example;

import java.util.ArrayList;

public class Restaurant {
    private static final int CLOSING_TIME = 0;
    private static final int OPENING_TIME = 6;
    private static final double SALE_MULTIPLIER = 1.5;
    private ArrayList<Food> menu;
    private ArrayList<Food> foodInventory;
    private ArrayList<Recipe> recipes;
    private ArrayList<Equipment> equipmentInventory;
    private double wealth;
    private int peakLunchHr;
    private int peakBreakfastHr;
    private int peakDinnerHr;

    public static int getClosingTime() {
        return CLOSING_TIME;
    }

    public static int getOpeningTime() {
        return OPENING_TIME;
    }

    public int getPeakBreakfastHr() {
        return peakBreakfastHr;
    }

    public int getPeakLunchHr() {
        return peakLunchHr;
    }

    public int getPeakDinnerHr() {
        return peakDinnerHr;
    }

    public double getWealth() {
        return wealth;
    }

    public void setWealth(double wealth) {
        this.wealth = wealth;
    }

    public ArrayList<Food> getFoodInventory() {
        return foodInventory;
    }

    public boolean hasFoods(String[] foodNames) {
        for (String foodName : foodNames) {
            if (getFoodByName(foodName) == null) {
                return false;
            }
        }
        return true;
    }

    public void removeFoods(String[] foodNames) {
        for (String foodName : foodNames) {
            foodInventory.remove(getFoodByName(foodName));
        }
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<Equipment> getEquipmentInventory() {
        return equipmentInventory;
    }

    public boolean hasEquipment(String[] equipmentNames) {
        for (String equipmentName : equipmentNames) {
            if (getEquipmentByName(equipmentName) == null) {
                return false;
            }
        }
        return true;
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
        for (Equipment equipment : equipmentInventory) {
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

    public void cookFood(Recipe recipe, int quantity) {
        ArrayList<Food> ingredientsNeeded = new ArrayList<Food>();

        for (int i = 0; i < quantity; i++) {

            if (hasEquipment(recipe.getRequiredEquipments()) && hasFoods(recipe.getIngredients())) {
                foodInventory.add(recipe.getCookedDish());
                removeFoods(recipe.getIngredients());
            } else {
                System.out.println("Sorry, you can't make " + recipe.getCookedDish().getName());
            }
        }
    }

    public int getEquipmentUpkeepCost() {
        int totalUpkeep = 0;

        for (Equipment equipment : equipmentInventory) {
            totalUpkeep += equipment.getDailyUpkeep();
        }

        return totalUpkeep;
    }
}
