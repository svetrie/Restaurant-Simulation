package com.example;

public class Food extends Item {
    //private String name;
   // private double baseValue;
    private static final double RESALE_MULTIPLIER = 0.8;
    private int cookingTime;

  /*  @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }
*/
    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        //System.out.println("Name: " + name);
        //System.out.println("Base Value: " + baseValue);
        System.out.println("Prep time: " + cookingTime);
    }

    @Override
    public double getMarketValue() {
        return RESALE_MULTIPLIER * getBaseValue();
    }
}
