package com.example;

/*
public interface Item {
    public String getName();
        //return name;


    public double getBaseValue();
        //return baseValue;

    public void printInfo();

    public double getMarketValue();
}*/

public abstract class Item {
    private String name;
    private double baseValue;

    public String getName() {
        return name;
    }
    //return name;


    public double getBaseValue() {
        return baseValue;
    }
    //return baseValue;

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Base Value: " + baseValue);
    }

    public abstract double getMarketValue();
}