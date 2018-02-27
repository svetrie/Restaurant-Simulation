package com.example;

import com.google.gson.Gson;

import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileLoader {

    public static Restaurant getRestaurantFromJsonFile(String filename) {
        Restaurant restaurant = null;
        Gson gson = new Gson();

        final Path path = FileSystems.getDefault().getPath("src/com/example/" + filename);

        try {
            String jsonString = new String(Files.readAllBytes(path));
            restaurant = gson.fromJson(jsonString, Restaurant.class);
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
        }

        return restaurant;
    }

    public static Market getMarketFromJsonFile(String filename) {
        Market market = null;
        Gson gson = new Gson();

        final Path path = FileSystems.getDefault().getPath("src/com/example/" + filename);

        try {
            String jsonString = new String(Files.readAllBytes(path));
            market = gson.fromJson(jsonString, Market.class);
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
        }

        return market;
    }

}
