package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Simulation {
    private static final Scanner scan = new Scanner(System.in);
    private static final int MIN_IN_HOUR = 60;
    private static final double SALE_PRICE_MULTIPLIER = 1.5;
    private static final double PEAK_HRS_BONUS = .33;

    private Restaurant restaurant;
    private Market market;

    public Simulation() {
        restaurant = JsonFileLoader.getRestaurantFromJsonFile("Restaurant.json");
        market = JsonFileLoader.getMarketFromJsonFile("Market.json");

        restaurant.initializeItemInventory();
        market.initializeInventory();
        Time.initializeTime();
    }

    public void runSimulation() {
        String userInput = "";

        System.out.println("Welcome to the Restaurant Simulation");

        while (!userInput.equalsIgnoreCase(StringConstants.QUIT_SIMULATION)) {
            userInput = getUserInput();
            usersNextMove(userInput);
        }

        System.out.println("Thanks for playing!");
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Market getMarket() {
        return market;
    }

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
            System.out.println(cookValidFood(usersNextMove[1], usersNextMove[2]));
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
        int time;

        try {
            time = Integer.parseInt(timePassed);
            if (time <= 0) {
                return "Sorry, the time you inputted is invalid";
            }
        } catch (NumberFormatException e) {
            return "Sorry, the time you inputted is invalid";
        }

        for (int i = 0; i < time; i++) {
            Time.passTime(1);

            if (Time.getHours() >= Restaurant.getOpeningTime()) {
                sellFood();
            }

            if (Time.getHours() == 0 && Time.getMinutes() == 0) {
                restaurant.setWealth(restaurant.getWealth() - restaurant.getEquipmentUpkeepCost());
            }
        }

        return "The time is now " + Time.getCurrentTime();
    }

    public void sellFood() {
        for (int i = 0; i < restaurant.getMenu().size(); i++) {
            Food potentialSale = restaurant.getFoodByName(restaurant.getMenu().get(i));
            double saleProbability = restaurant.getPopularity();

            if (Time.getHours() == restaurant.getPeakBreakfastHr()
                    || Time.getHours() == restaurant.getPeakLunchHr()
                    || Time.getHours() == restaurant.getPeakDinnerHr()) {
                saleProbability += PEAK_HRS_BONUS;
            }

            if (Math.random() >= 1 - saleProbability) {
                restaurant.sellItems(potentialSale, 1);
                restaurant.setWealth(restaurant.getWealth() + SALE_PRICE_MULTIPLIER
                        * potentialSale.getBaseValue());

                System.out.println("Sold " + potentialSale.getName() + " for "
                        + (SALE_PRICE_MULTIPLIER * potentialSale.getBaseValue()));
            }
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
            passTime(Integer.toString(recipe.getCookedDish().getCookingTime() * quantity));
            return "Cooked " + recipe.getCookedDish().getName();
        } else {
            return "Sorry, you can't cook " + recipeName;
        }
    }

    public void visitMarket() {
        System.out.println("You're at the market");
        String[] usersNextMove;

        do {
            String userInput  = getUserInput();
            usersNextMove = userInput.trim().toLowerCase().split("\\s+");

            if (usersNextMove.length == 2 && usersNextMove[0].equals(StringConstants.DISPLAY_ITEMS)) {
                listMarketItems(usersNextMove[1]);
            } else if (usersNextMove.length == 3 && usersNextMove[0].equals(StringConstants.BUY)) {
                System.out.println(buyFromMarket(usersNextMove[1], usersNextMove[2]));
            } else if (usersNextMove.length == 3 && usersNextMove[0].equals(StringConstants.SELL)) {
                System.out.println(sellToMarket(usersNextMove[1], usersNextMove[2]));
            } else if (usersNextMove[0].equals(StringConstants.EXIT_MARKET)) {
                continue;
            } else {
                System.out.println("Sorry, I don't understand " + userInput);
            }

        } while (!usersNextMove[0].equals(StringConstants.EXIT_MARKET));

        System.out.println("You're back at the restaurant");
        passTime(Integer.toString(MIN_IN_HOUR));
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

}