package com.example;

public class Equipment extends Item {
    private static final double RESALE_MULTIPLIER = 0.5;
    private double dailyUpkeep;

    public double getDailyUpkeep() {
        return dailyUpkeep;
    }

    @Override
    public void printInfo() {
        super.printInfo();
       System.out.println("Daily Upkeep Cost: " + dailyUpkeep);
    }

    @Override
    public double getMarketValue() {
        return RESALE_MULTIPLIER * getBaseValue();
    }
}
