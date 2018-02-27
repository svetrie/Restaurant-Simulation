package com.example;

public abstract class Item {
    private String name;
    private double baseValue;

    public String getName() {
        return name;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void printInfo() {
        System.out.println(name);
        System.out.println(baseValue);
    }

    public abstract double getMarketValue();
}
