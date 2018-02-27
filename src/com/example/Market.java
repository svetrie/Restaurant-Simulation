package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Market {
    private Food[] marketFoods;
    private Equipment[] marketEquipment;
    private Recipe[] marketRecipes;
    private ArrayList<Item> inventory;

    public void initializeInventory() {
        inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(marketFoods));
        inventory.addAll(Arrays.asList(marketEquipment));
        inventory.addAll(Arrays.asList(marketRecipes));
    }

   /* public Food getFoodByName(String foodName) {
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
    } */

   public ArrayList<Item> getPurchasedItems(String itemName, int quantity) {
       ArrayList<Item> purchasedItems = new ArrayList<Item>();

       Item item = getItemByName(itemName);

       if (item != null) {

           for (int i = 0; i < quantity; i++) {
               purchasedItems.add(item);
           }

           return purchasedItems;
       } else {
           return null;
       }
   }

    public Item getItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void printMarketItems(Item[] items) {
        System.out.println("The market has: ");

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].getName());
        }
    }

    /* might want to add to simulation class
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
    }*/


    public void printMarketFoods() {
        printMarketItems(marketFoods);
    }

    public void printMarketRecipes() {
        printMarketItems(marketRecipes);
    }

    public void printMarketEquipment() {
        printMarketItems(marketEquipment);
    }
}
