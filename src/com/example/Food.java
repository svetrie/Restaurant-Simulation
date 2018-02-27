package com.example;

public class Food extends Item {
    private static final double RESALE_MULTIPLIER = 0.8;
    private int cookingTime;

    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Prep time: " + cookingTime);
    }

    @Override
    public double getMarketValue() {
        return RESALE_MULTIPLIER * getBaseValue();
    }
}
