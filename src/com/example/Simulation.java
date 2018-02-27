package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Simulation {
    private static final Scanner scan = new Scanner(System.in);
    private static final int MIN_IN_HOUR = 60;
    private static final int CLOSING_TIME = 0;
    private static final int OPENING_TIME = 6;

    private Restaurant restaurant;
    private Market market;

    public String getUserInput() {
        System.out.println("What would you like to do?");
        return scan.nextLine();
    }

    public void usersNextMove(String userInput){

        /* Splits userInput into words to identify the user's action and
        the object the action is performed on */
        String[] usersNextMove = userInput.trim().toLowerCase().split("\\s+");

        /* Each conditional checks for an action user could take (first word of userInput)
        and checks that the object of the action (remaining words of userInput) exists
        using a helper method. Helper method will also execute action if the object is valid */
        if (usersNextMove[0].equals(StringConstants.DISPLAY_WEALTH)) {
            System.out.println("Wealth: " +  restaurant.getWealth());
        } else if (usersNextMove[0].equals(StringConstants.DISPLAY_TIME)) {
            System.out.println("The current time is: " + Time.getCurrentTime());
            System.out.println(Time.getDays() + " has passed");
        } else if (usersNextMove.length == 3 && (usersNextMove[0] + usersNextMove[1])
                .equalsIgnoreCase(StringConstants.PASS_TIME)) {
            System.out.println(passTime(usersNextMove[2]));
        } else if (usersNextMove.length == 2 && usersNextMove[0].equals(StringConstants.INVENTORY)) {
            printItemInventory(usersNextMove[1]);
        } else if (usersNextMove.length == 2 && usersNextMove[0].equals(StringConstants.ITEM_INFO)) {
            printItemInfo(usersNextMove[1]);
        } else if (usersNextMove.length == 3 && usersNextMove[0].equals(StringConstants.COOK_FOOD)) {
            cookValidFood(usersNextMove[1], usersNextMove[2]);
        } else if (usersNextMove.length == 3 && (usersNextMove[0] + usersNextMove[1])
                .equals(StringConstants.ADD_TO_MENU)) {
            System.out.println(restaurant.addToMenu(usersNextMove[2]));
        } else if (usersNextMove.length == 3 && (usersNextMove[0] + usersNextMove[1])
                .equals(StringConstants.REMOVE_FROM_MENU)) {
            System.out.println(restaurant.removeFromMenu(usersNextMove[2]));
        } else if (usersNextMove.length == 2 && (usersNextMove[0]
                + usersNextMove[1]).equals(StringConstants.DISPLAY_MENU)) {
            restaurant.printMenu();
        } else if (usersNextMove[0].equals(StringConstants.MARKET)) {
            visitMarket();
        } else {
            System.out.println("Sorry I don't understand " + userInput);
        }

    }

    public String passTime(String timePassed) {
        try {
            int time = Integer.parseInt(timePassed);
            Time.passTime(time);
            return "The time is now " + Time.getCurrentTime();
        } catch (NumberFormatException e) {
            return "Sorry, the time you inputted is invalid";
        }
    }

    public void printItemInventory(String itemType) {
        if (itemType.equals(StringConstants.FOOD)) {
            restaurant.printFoodInventory();
        } else if (itemType.equals(StringConstants.EQUIPMENT)) {
            restaurant.printEquipmentInventory();
        } else if (itemType.equals(StringConstants.RECIPE)) {
            restaurant.printRecipes();
        } else {
            System.out.println("Sorry, the restaurant doesn't have that type of item");
        }
    }

    public void printItemInfo(String itemName) {
        if (market.getItemByName(itemName) != null) {
            market.getItemByName(itemName).printInfo();
        } else {
            System.out.println("The item you searched for does not exist");
        }
    }

    public String cookValidFood(String recipeName, String numFood) {
        int quantity;

        try {
             quantity = Integer.parseInt(numFood);
        } catch (NumberFormatException e) {
            return "Sorry, the quantity you inputted is invalid";
        }

        Recipe recipe = restaurant.getRecipeByName(recipeName);

        if (recipe != null) {
            restaurant.cookFood(recipe, quantity);
            Time.passTime(recipe.getCookedDish().getCookingTime() * quantity);
            return "Cooked" + recipeName;
        } else {
            return "Sorry, you can't cook " + recipeName;
        }
    }

    public void visitMarket() {
       String[] usersNextMove;

        do {
            String userInput  = getUserInput();
            usersNextMove = userInput.trim().toLowerCase().split("\\s+");

            if (usersNextMove.length == 2 && usersNextMove[0].equals(StringConstants.DISPLAY_ITEMS)) {
                listMarketItems(usersNextMove[1]);
            } else if (usersNextMove.length == 3 && usersNextMove[0].equals(StringConstants.BUY)) {
                buyFromMarket(usersNextMove[1], usersNextMove[2]);
            } else if (usersNextMove.length == 3 && usersNextMove[0].equals(StringConstants.SELL)) {
                sellToMarket(usersNextMove[1], usersNextMove[2]);
            } else {
                System.out.println("Sorry, I don't understand " + userInput);
            }

        } while (!usersNextMove[0].equals(StringConstants.EXIT_MARKET));

        Time.passTime(MIN_IN_HOUR);
    }

    public void listMarketItems(String itemType) {
        if (itemType.equals(StringConstants.FOOD)) {
            market.printMarketFoods();
        } else if (itemType.equals(StringConstants.EQUIPMENT)) {
            market.printMarketEquipment();
        } else if (itemType.equals(StringConstants.RECIPE)) {
            market.printMarketRecipes();
        } else {
            System.out.println("The market doesn't have items of that type");
        }
    }

    public String buyFromMarket(String itemName, String numItems ) {
        int quantity;

        try {
            quantity = Integer.parseInt(numItems);
        } catch (NumberFormatException e) {
            return "Sorry, the quantity you inputted is invalid";
        }

        ArrayList<Item> purchase = market.getPurchasedItems(itemName, quantity);

        if (purchase != null && purchase.get(0).getBaseValue() * quantity <= restaurant.getWealth()) {
            restaurant.setWealth(restaurant.getWealth() - purchase.get(0).getBaseValue() * quantity);
            restaurant.buyItems(purchase);
            return "Purchase was successful";
        } else {
            return "Sorry, you can't buy " + quantity + " " + itemName;
        }
    }

    public String sellToMarket(String itemName, String numItems) {
        int quantity;

        try {
            quantity = Integer.parseInt(numItems);
        } catch (NumberFormatException e) {
            return "Sorry, the quantity you inputted is invalid";
        }

        Item itemSold = restaurant.getItemByName(itemName);

        if (itemSold != null && Collections.frequency(restaurant.getItemInventory(),
                itemSold) >= quantity) {
            restaurant.sellItems(itemSold, quantity);
            restaurant.setWealth(restaurant.getWealth() + itemSold.getMarketValue() * quantity);
            return "Successfully sold items";
        } else {
            return "Sorry, you can't sell " + quantity + " " + itemName;
        }
    }

    public static void main(String[] args) {
	// write your code here
    }



}
