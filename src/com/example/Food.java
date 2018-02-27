package com.example;

public class Food extends Item {
    private static final double RESALE_MULTIPLIER = 0.8;
    private int cookingTime;

    public int getCookingTime() {
        return cookingTime;
    }

    public void printInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Prep time: " + cookingTime);
        System.out.println("Base Value: " + getBaseValue());
    }
}
