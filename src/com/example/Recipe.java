package com.example;

public class Recipe extends Item {
    private static final double RESALE_MULTIPLIER = 0.5;
    private String[] ingredients;
    private String[] requiredEquipments;
    private Food cookedDish;

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getRequiredEquipments() {
        return requiredEquipments;
    }

    public Food getCookedDish() {
        return cookedDish;
    }

    @Override
    public void printInfo() {
        super.printInfo();

        System.out.println("The ingredients are: ");
        for (int i = 0; i < ingredients.length; i++) {
            System.out.print(ingredients[i]);

            //Makes sure there is not a trailing comma after the last item is printed
            if (i < ingredients.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("Equipment needed: ");
        for (int i = 0; i < requiredEquipments.length; i++) {
            System.out.print(requiredEquipments[i]);

            if (i < requiredEquipments.length - 1) {
                System.out.print(", ");
            }
        }
    }

    @Override
    public double getMarketValue() {
        return RESALE_MULTIPLIER * getBaseValue();
    }
}
