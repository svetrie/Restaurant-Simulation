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

    /**
     * Returns true if food inventory contains the necessary ingredients
     *
     * @param foodNames name of ingredients used in a recipe
     * @return true or false
     */
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

    /**
     * Return true if equipment inventory has specfied equipment
     *
     * @param equipmentNames name of equipments
     * @return a boolean
     */
    public boolean hasEquipment(String[] equipmentNames) {
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

    /**
     * Tries to add food to menu if food exists in food inventory
     * and isn't already on the menu
     *
     * @param foodName name of food to be added to menu
     * @return String that lets user know if food was added
     */
    public String addToMenu(String foodName) {
        if (!menu.contains(foodName) && getFoodByName(foodName) != null) {
            menu.add(foodName);
            return "Added " + foodName + " to menu";
        } else {
            return "Sorry, you can't add " + foodName + " to the menu";
        }
    }

    /**
     * Tries to remove food if it is on the menu
     *
     * @param foodName name of food to be removed from menu
     * @return String that lets user know if food was removed
     */
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

    /**
     * Tries to cook recipe for specified quantity if the restaurant contains the
     * needed ingredients and equipment
     *
     * @param recipe is the recipe that the user wants to cook
     * @param quantity number of times user wants to cook this recipe
     */
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

    /**
     * Calculates total cost of equipment upkeep
     *
     * @return cost of equipment upkeep
     */
    public int getEquipmentUpkeepCost() {
        int totalUpkeep = 0;

        for (Equipment equipment : equipmentInventory) {
            totalUpkeep += equipment.getDailyUpkeep();
        }

        return totalUpkeep;
    }

    /**
     * Calculates complexity of menu by using the cooking times
     * of foods on the menu
     *
     * @return double that represents complexity of menu
     */
    public double getMenuComplexity() {
        double complexity = 0;

        for (String foodName : menu) {
            complexity += getFoodByName(foodName).getCookingTime();
        }

        return complexity;
    }

    /**
     * Calculates popularity based on menu complexity and menu size
     *
     * @return double that represent the popularity of menu
     */
    public double getPopularity() {
        return (menu.size() + getMenuComplexity() / menu.size()) / 100;
    }

    /**
     * Sorts items into proper inventory based on type
     *
     * @param purchasedItems items that were bought
     */
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

    /**
     * Removes a quantity of items from the correct inventory
     *
     * @param itemSold type of item sold
     * @param quantity number of items sold
     */
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
