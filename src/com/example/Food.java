package com.example;

public class Food {
    private static final double RESALE_MULTIPLIER = 0.8;
    private String name;
    private double baseValue;


    public String getName() {
        return name;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Base Value: " + baseValue);
    }
}
