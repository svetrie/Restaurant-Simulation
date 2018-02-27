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

    // might want to add to simulation class
    public double getPurchasePrice(String itemName, int quantity) {
        double totalPrice = 0;

        if (getFoodByName(itemName) != null) {
            totalPrice = getFoodByName(itemName).getBaseValue() * quantity;
        } else if (getEquipmentByName(itemName) != null) {
            totalPrice = getEquipmentByName(itemName).getBaseValue() * quantity;
        } else if (getRecipeByName(itemName) != null) {
            totalPrice = getRecipeByName(itemName).getBaseValue() * quantity;
        }

        return totalPrice;
    }


    public void printMarketFoods() {
        System.out.println("The market has: ");

        for (int i = 0; i < marketFoods.length; i++) {
            System.out.println(marketFoods[i].getName());
        }
    }

    public void printMarketRecipes() {
        System.out.println("The market has: ");

        for (int i = 0; i < marketRecipes.length; i++) {
            System.out.println(marketRecipes[i].getCookedDish().getName());
        }
    }

    public void printMarketEquipment() {
        System.out.println("The market has: ");

        for (int i = 0; i < marketEquipment.length; i++) {
            System.out.print(marketEquipment[i].getName());
        }
    }
}
