package com.example;

public class Food {
    private static final double RESALE_MULTIPLIER = 0.8;
    private String name;
    private double baseValue;
    private double cookingTime;


    public String getName() {
        return name;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Prep time: " + cookingTime);
        System.out.println("Base Value: " + baseValue);
    }
}
