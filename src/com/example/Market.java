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

    /**
     * Searches through market's item inventory for item and returns
     * the quantity specfied
     *
     * @param itemName name of the items user is searching for
     * @param quantity number of items user wants
     * @return ArrayList of items
     */
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

    /**
     * Searches through market's item inevntory for a particular item
     *
     * @param itemName name of item user is searching for
     * @return Item object
     */
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
