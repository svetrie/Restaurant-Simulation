package com.example;

public class Equipment extends Item {
    //private String name;
    //private double baseValue;
    private static final double RESALE_MULTIPLIER = 0.5;
    private double dailyUpkeep;
/*
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }
*/
    public double getDailyUpkeep() {
        return dailyUpkeep;
    }

    @Override
    public void printInfo() {

        super.printInfo();
        //System.out.println("Name: " + name);
       //System.out.println("Base Value: " + baseValue);
       System.out.println("Daily Upkeep Cost: " + dailyUpkeep);
    }

    @Override
    public double getMarketValue() {
        return RESALE_MULTIPLIER * getBaseValue();
    }
}
