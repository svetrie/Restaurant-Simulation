package com.example;

import java.util.ArrayList;

public class Restaurant {
    private static final int CLOSING_TIME = 0;
    private static final int OPENING_TIME = 6;
    private static final double SALE_MULTIPLIER = 1.5;
    private ArrayList<String> menu;
    private ArrayList<Food> foodInventory;
    private ArrayList<Recipe> recipes;
    private ArrayList<Equipment> equipmentInventory;
    private ArrayList<Item> itemInventory;
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

    public ArrayList<Item> getItemInventory() {
        return itemInventory;
    }

    public void initializeItemInventory() {
        itemInventory = new ArrayList<Item>(foodInventory);
        itemInventory.addAll(equipmentInventory);
        itemInventory.addAll(recipes);
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
        if (equipmentNames == null)
            System.out.println("da fuq");

        for (String equipmentName : equipmentNames) {
            if (getEquipmentByName(equipmentName) == null) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getMenu() {
        return menu;
    }

    public String addToMenu(String foodName) {
        if (!menu.contains(foodName) && getFoodByName(foodName) != null) {
            menu.add(foodName);
            return "Added " + foodName + " to menu";
        } else {
            return "Sorry, you can't add " + foodName + " to the menu";
        }
    }

    public String removeFromMenu(String foodName) {
        if (menu.contains(foodName)) {
            menu.remove(foodName);
            return "Removed " + foodName + " from menu";
        } else {
            return "Sorry, you can't remove " + foodName + " from the menu";
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
            if (recipe.getName().equalsIgnoreCase(recipeName)) {
                return recipe;
            }
        }
        return null;
    }

    public Item getItemByName(String itemName) {
        for (Item item : itemInventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void printMenu() {
        if (menu.size() == 0 ) {
            System.out.println("menu doesn't contain anything");
        }

        for (String foodName : menu) {
            System.out.println("\tEntree: " + foodName + " "
                    + getFoodByName(foodName).getBaseValue() * SALE_MULTIPLIER);
        }
    }

    public void printFoodInventory() {
        System.out.println("Restaurant's food inventory contains: ");

        for (Food food : foodInventory) {
            System.out.println('\t' + food.getName());
        }
    }

    public void printEquipmentInventory() {
        System.out.println("Restaurant's equipment inventory contains: ");

        for (Equipment equipment : equipmentInventory) {
            System.out.println('\t' + equipment.getName());
        }
    }

    public void printRecipes() {
        System.out.println("Restaurant has recipes for the following dishes");

        for (Recipe recipe : recipes) {
            System.out.println('\t' + recipe.getCookedDish().getName());
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

    /*public ArrayList<Food> getUniqueFoods() {
        ArrayList<Food> uniqueFoods = new ArrayList<Food>();

        for (Food food : foodInventory) {
            if (!uniqueFoods.contains(food)) {
                uniqueFoods.add(food);
            }
        }

        return uniqueFoods;
    }*/

    public double getMenuComplexity() {
        double complexity = 0;

        for (String foodName : menu) {
            if (getFoodByName(foodName) == null) {
                System.out.println("food DNE");
            }
            complexity += getFoodByName(foodName).getCookingTime();
        }

        return complexity;
    }

    public double getPopularity() {
        /*if (menu == null) {
            System.out.println("Menu is null");
        }*/

        return (menu.size() + getMenuComplexity() / menu.size()) / 100;
    }

    public void buyItems(ArrayList<Item> purchasedItems) {
        for (Item item : purchasedItems) {
            if (item instanceof Food) {
                foodInventory.add((Food) item);
            } else if (item instanceof Recipe) {
                recipes.add((Recipe) item);
            } else {
                equipmentInventory.add((Equipment) item);
            }
        }

        itemInventory.addAll(purchasedItems);
    }

    public void sellItems(Item itemSold, int quantity) {
        for (int i = 0; i < quantity; i++) {
            itemInventory.remove(itemSold);

            if (itemSold instanceof Food) {
                foodInventory.remove(itemSold);
            } else if (itemSold instanceof Recipe) {
                recipes.remove(itemSold);
            } else {
                equipmentInventory.remove(itemSold);
            }
        }

        if (itemSold instanceof Food) {
            if (getFoodByName(itemSold.getName()) == null && menu.contains(itemSold.getName())) {
                menu.remove(itemSold.getName());
            }
        }
    }

}
