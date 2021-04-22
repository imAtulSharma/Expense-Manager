package com.streamliners.expensemanager.driver;

/**
 * Represents class to print the text in console with different colors
 */
public class Printings {
    // Color Strings
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String RED = "\u001B[31m";

    /**
     * Prints the text in th given color
     * @param text text to be printed
     * @param type color of the text to be in printed
     */
    public static void print(String text, String type) {
        System.out.print(type + text + "\u001B[0m");
    }
}
