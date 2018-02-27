package com.example;

public class Time {
    private static final int MIN_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static int hours;
    private static int minutes;
    private static int days;

    public static String getCurrentTime() {
        return hours + ":" + minutes;
    }

    public static int getDays() {
        return days;
    }

    public static void passTime(int minPassed) {
        if (minPassed > MIN_IN_HOUR) {
            hours += minPassed / MIN_IN_HOUR;
            minutes = minPassed % MIN_IN_HOUR;
        }

        if (hours >= HOURS_IN_DAY) {
            hours = hours / HOURS_IN_DAY;
            days++;
        }
    }

    public static void initializeTime() {
        hours = 0;
        minutes = 0;
        days = 0;
    }
}
