package com.example;

public class Equipment {
    private static final double RESALE_MULTIPLIER = 0.5;
    private String name;
    private double baseValue;
    private double dailyUpkeep;

    public String getName() {
        return name;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getDailyUpkeep() {
        return dailyUpkeep;
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Base Value: " + baseValue);
        System.out.println("Daily Upkeep Cost: " + dailyUpkeep);
    }
}
