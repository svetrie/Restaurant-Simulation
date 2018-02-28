package com.example;

public class Time {
    private static final int MIN_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int DOUBLE_DIGITS = 10;
    private static int hours;
    private static int minutes;
    private static int days;

    public static String getCurrentTime() {
        String hoursAsString = Integer.toString(hours);
        String minutesAsString = Integer.toString(minutes);

        if (hours < DOUBLE_DIGITS) {
            hoursAsString = "0" + hoursAsString;
        }

        if (minutes < DOUBLE_DIGITS) {
            minutesAsString = "0" + minutesAsString;
        }


        return hoursAsString + ":" + minutesAsString;
    }

    public static int getHours() {
        return hours;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static int getDays() {
        return days;
    }

    public static void passTime(int minPassed) {
        minutes += minPassed;

        if (minutes >= MIN_IN_HOUR) {
            hours += minutes / MIN_IN_HOUR;
            minutes = minutes % MIN_IN_HOUR;
        }

        if (hours >= HOURS_IN_DAY) {
            hours = hours % HOURS_IN_DAY;
            days++;
        }
    }

    public static void initializeTime() {
        hours = 0;
        minutes = 0;
        days = 0;
    }
}
