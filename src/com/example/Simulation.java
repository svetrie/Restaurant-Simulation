package com.example;

import java.util.Arrays;
import java.util.Scanner;

public class Simulation {
    private static final Scanner scan = new Scanner(System.in);
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
        String[] usersNextMove = userInput.trim().split("\\s+");

        /* Each conditional checks for an action user could take (first word of userInput)
        and checks that the object of the action (remaining words of userInput) exists
        using a helper method. Helper method will also execute action if the object is valid */
        if (usersNextMove[0].equalsIgnoreCase(StringConstants.DISPLAY_WEALTH)) {
            System.out.println("Wealth: " +  restaurant.getWealth());
        } else if (usersNextMove[0].equalsIgnoreCase(StringConstants.DISPLAY_TIME)) {
            System.out.println("The current time is: " + Time.getCurrentTime());
            System.out.println(Time.getDays() + " has passed");
        } else if (usersNextMove.length == 3 && (usersNextMove[0] + usersNextMove[1])
                .equalsIgnoreCase(StringConstants.PASS_TIME)) {
            System.out.println(passTime(usersNextMove[2]));
        } else if (usersNextMove[0].equalsIgnoreCase(StringConstants.INVENTORY)) {
            //printInventory method
        } else if (usersNextMove[0].equalsIgnoreCase(StringConstants.ITEM_INFO)) {
            // getItemInfo method
        } else if (usersNextMove.length == 3 && usersNextMove[0]
                .equalsIgnoreCase(StringConstants.COOK_FOOD)) {
            //call cookfood method in restuarant class
        } else if (usersNextMove.length == 3 && usersNextMove[0].equalsIgnoreCase(StringConstants.ADD_TO_MENU)) {
            // call add to menu
        } else if (usersNextMove.length == 3 && usersNextMove[0].equalsIgnoreCase(StringConstants.REMOVE_FROM_MENU)) {
            // call remove from menu
        } else if (usersNextMove.length == 2 && (usersNextMove[0]
                + usersNextMove[1]).equalsIgnoreCase(StringConstants.DISPLAY_MENU)) {
            // display menu
        } else if (usersNextMove[0].equalsIgnoreCase(StringConstants.MARKET)) {
            // access market
        } else {
            System.out.println("Sorry I don't understand " + userInput);
        }

    }

    public static void main(String[] args) {
	// write your code here
    }



}
