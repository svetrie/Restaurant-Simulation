package com.example;

public class Time {
    private static final int MIN_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static int hours;
    private static int minutes;

    public static void printTime() {
        System.out.println(hours + ":" + minutes);
    }

    public static void passTime(int minPassed) {
        if (minPassed > MIN_IN_HOUR) {
            hours += minPassed / MIN_IN_HOUR;
            minutes = minPassed % MIN_IN_HOUR;
        }

        if (hours >= HOURS_IN_DAY) {
            hours = hours / HOURS_IN_DAY;
        }
    }
}
