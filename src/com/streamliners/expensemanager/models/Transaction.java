package com.streamliners.expensemanager.models;

import com.streamliners.expensemanager.driver.Printings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represent class of transaction for income and expense
 */
public class Transaction {
    // For the expense transaction
    public static String TYPE_EXPENSE = "Expense";

    // for the income transaction
    public static String TYPE_INCOME = "Income";

    private Date date;
    private String description;
    private float amount;
    private String type;

    // date formatter in dd-mm-yy
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");

    /**
     * Constructor for the transaction
     * @param date        date of the transaction
     * @param description description of the transaction
     * @param amount      amount of the transaction
     * @param type        type of the transaction
     */
    public Transaction(String date, String description, float amount, String type) {
        try {
            // parsing date from string into date object
            this.date = dateFormat.parse(date);
        } catch (Exception e) {
            // Handling exception while parsing the date from string
            Printings.print("\n" + e + "\n", Printings.RED);
        }
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // All the getter and setter of the class

    /**
     * Setter for date
     * @param date date to be set to the onject
     */
    public void setDate(String date) {
        try {
            // parsing date from string into date object
            this.date = dateFormat.parse(date);
        } catch (Exception e) {
            // Handling exception while parsing the date from string
            Printings.print("\n" + e + "\n", Printings.RED);
        }
    }

    /**
     * Setter for description
     * @param description to be set to the object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for amount
     * @param amount amount to be set to the object
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Setter for the type of the object
     * @param type type to be set to the object
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for date
     * @return date of the transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter for date in string
     * @return date of transaction in string
     */
    public String getDateInString() {
        String dateString = dateFormat.format(date);
        return dateString.split(" ")[0];
    }

    /**
     * Getter for the description
     * @return description of the transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for amount
     * @return amount of the transaction
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Getter for type
     * @return type of the transaction
     */
    public String getType() {
        return type;
    }
}
