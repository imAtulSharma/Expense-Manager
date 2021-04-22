package com.streamliners.expensemanager.driver;

import com.streamliners.expensemanager.models.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents class of the operations for the transaction(s)
 */
public class TransactionOperations
{
    private static Scanner scanner = new Scanner(System.in);

    /**
     * To create a new transaction
     * @return the newly created transaction
     */
    public static Transaction newTransaction() {
        // Initialising the transaction
        Transaction transaction = null;

        // Printing the menu
        String menu = "Please choose from the given types:" +
                "\n0: Go Back" +
                "\n1: Expense" +
                "\n2: Income" +
                "\nYour response: ";

        while (true) {
            System.out.print(menu);
            int chooseOption = scanner.nextInt();

            switch (chooseOption) {
                case 0:
                    return transaction;
                case 1:
                    transaction = newExpenseTransaction();
                    return transaction;
                case 2:
                    transaction = newIncomeTransaction();
                    return transaction;
                default:
                    Printings.print("ERROR! Incorrect option.\n", Printings.RED);
            }
        }
    }

    /**
     * creating new expense transaction
     * @return newly created expense transaction
     */
    private static Transaction newExpenseTransaction() {
        System.out.print("Enter amount of transaction: ");
        float amount = -scanner.nextFloat();

        System.out.print("Enter date of transaction (dd-mm-yy): ");
        String date = scanner.nextLine();

        // Making sure that the user must enter something
        while(date.isEmpty()) date = scanner.nextLine();

        System.out.print("Enter description of transaction: ");
        String description = scanner.nextLine();

        // Creating a transaction with the input fields
        Transaction transaction = new Transaction(date, description, amount, Transaction.TYPE_EXPENSE);

        return transaction;
    }

    /**
     * creating new income transaction
     * @return newly created income transaction
     */
    private static Transaction newIncomeTransaction() {
        System.out.print("Enter amount of transaction: ");
        float amount = scanner.nextFloat();

        System.out.print("Enter date of transaction (dd-mm-yy): ");
        String date = scanner.nextLine();

        // Making sure that the user must enter something
        while(date.isEmpty()) date = scanner.nextLine();

        System.out.print("Enter description of transaction: ");
        String description = scanner.nextLine();

        // Creating a transaction with the input fields
        Transaction transaction = new Transaction(date, description, amount, Transaction.TYPE_INCOME);

        return transaction;
    }

    /**
     * To edit the given transaction
     * @param transaction transaction to be edited
     */
    public static void editTransaction(Transaction transaction) {
        // Taking new details for the transaction
        System.out.print("Enter new amount of transaction: ");
        float newAmount = scanner.nextFloat();

        System.out.print("Enter new date of transaction: ");
        String newDate = scanner.nextLine();

        // Making sure that the user must enter something
        while(newDate.isEmpty()) newDate = scanner.nextLine();


        System.out.print("Enter new description of transaction: ");
        String newDescription = scanner.nextLine();

        Scanner scanner = new Scanner(System.in);
        String menu = "Please choose from new type: " +
                "\n1: Income" +
                "\n2: Expense" +
                "\n" +
                "\nYour response: ";
        int chooseOption = 0;

        // Return the loop when user select 1 or 2 option
        while (!(chooseOption == 1 || chooseOption == 2) ) {
            // Printing menu
            System.out.print(menu);
            chooseOption = scanner.nextInt();

            // taking the type for the transaction and updating the transaction
            switch (chooseOption) {
                case 1:
                    transaction.setType(Transaction.TYPE_INCOME);
                    transaction.setAmount(newAmount);
                    break;
                case 2:
                    transaction.setType(Transaction.TYPE_EXPENSE);
                    transaction.setAmount(-newAmount);
                    break;
                default:
                    Printings.print("ERROR! Incorrect option.\n", Printings.RED);
            }
        }

        transaction.setDate(newDate);
        transaction.setDescription(newDescription);
    }

    /**
     * To get the transaction from the list
     * @param transactions list of the transactions
     * @return the selected transaction
     */
    public static Transaction getTransaction(ArrayList<Transaction> transactions) {
        // Making sure that the list contains at least one transaction
        if (transactions.isEmpty()) {
            Printings.print("ERROR! No transactions available.\n", Printings.RED);
            return null;
        }

        Scanner scanner = new Scanner(System.in);

        int index = 1;
        while (true) {
            System.out.print("\nSelect Transaction: " +
                    "\n0: Go Back");

            // Calling function to list all the transaction in the given list
            listingTransactions(transactions, index);

            // Taking response from the user
            System.out.print("\n\nYour response: ");
            index = scanner.nextInt();

            if (index == 0) return null;

            try {
                // Return the transaction by using its index in the actual list
                return transactions.get(index-1);
            } catch (Exception e) {
                // Error handling if the user choose wrong option
                Printings.print("ERROR! Incorrect option.\n", Printings.RED);
            }
        }
    }

    /**
     * To print all the available transaction
     * @param transactions list of the transactions
     */
    public static void seeAllTransactions(ArrayList<Transaction> transactions) {
        // Making sure that the list contains at least one transaction
        if (transactions.isEmpty()) {
            Printings.print("ERROR! No transactions available.\n", Printings.RED);
            return;
        }

        // Declaring fields
        float totalExpenses = 0, totalIncomes = 0;
        int noOfTransactions = 0, index = 1;

        // Calling function to print the whole list of transactions
        listingTransactions(transactions, index);

        // Traversing the list for the totals
        for (Transaction transaction: transactions) {
            // Checking type of transaction
            if (transaction.getType() == Transaction.TYPE_EXPENSE) totalExpenses += transaction.getAmount();
            else totalIncomes += transaction.getAmount();
            noOfTransactions++;
        }

        // Printing brief summary of all the transactions
        Printings.print("\nTotal Income = " + totalIncomes +
                        "\nTotal Expense = " + -totalExpenses +
                        "\nNumber of Transactions = " + noOfTransactions,
                Printings.YELLOW);
    }

    /**
     * For listing all the transaction(s) in the list
     * @param transactions list of transactions to be listed
     * @param index        starting index for the list
     */
    private static void listingTransactions(ArrayList<Transaction> transactions, int index) {

        // traversing all the transactions in the list provided
        for (Transaction transaction: transactions) {
            /**
             * Format of the transaction:
             *{index}    {type}    {Amount}    {date}    {description}
             *
             *For example
             *1: Expense 2000 07-04-21 party
             */

            // Checking the type of the transaction
            if (transaction.getType() == Transaction.TYPE_EXPENSE) Printings.print("\n" + (index++) + ": " +
                            transaction.getType() +
                            "    " + -transaction.getAmount() +
                            "    " + transaction.getDateInString() +
                            "    " + transaction.getDescription(),
                    Printings.RED);

            else Printings.print("\n" + (index++) + ": " +
                            transaction.getType() +
                            "     " + transaction.getAmount() +
                            "    " + transaction.getDateInString() +
                            "    " + transaction.getDescription(),
                    Printings.GREEN);
        }
    }

    /**
     * Filter the transaction according to the date
     * @param transactions list of the transactions
     */
    public static void filterTransactions(ArrayList<Transaction> transactions) {
        // Making sure that the list contains at least one transaction
        if (transactions.isEmpty()) {
            Printings.print("ERROR! No transactions available.\n", Printings.RED);
            return;
        }

        // Declaring fields for the brief summary of the period
        float totalExpenses = 0, totalIncomes = 0;
        int noOfTransactions = 0, index = 1;
        String dateInput;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");
        Date startDate, endDate;

        // Taking date as string from the user
        System.out.print("Enter starting date (dd-mm-yy): ");
        dateInput = scanner.nextLine();
        while (dateInput.isEmpty()) dateInput = scanner.nextLine();

        try {
            // Parsing date from the string
            startDate = dateFormat.parse(dateInput);
        } catch (Exception e) {
            // if failed in parsing then printing the error in red color
            Printings.print("\n" + e + "\n", Printings.RED);
            return;
        }

        // Same as earlier
        System.out.print("Enter end date (dd-mm-yy): ");
        dateInput = scanner.nextLine();
        while (dateInput.isEmpty()) dateInput = scanner.nextLine();

        try {
            endDate = dateFormat.parse(dateInput);
        } catch (Exception e) {
            Printings.print("\n" + e + "\n", Printings.RED);
            return;
        }

        // Checking that the date is of correct order
        // if false then we have to swap it
        if(startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        // traversing the list of the transactions
        for (Transaction transaction : transactions) {
            // Date of the transaction
            Date date = transaction.getDate();
            // Checking that the transaction is between the period mention by the user
            if (date.before(endDate) && date.after(startDate)) {
                // Checking the type of the transaction
                if(transaction.getType() == Transaction.TYPE_EXPENSE) {
                    // Incrementing total expenses
                    totalExpenses += transaction.getAmount();
                    Printings.print("\n" + (index++) + ": " +
                            transaction.getType() +
                            "    " + -transaction.getAmount() +
                            "    " + transaction.getDateInString() +
                            "    " + transaction.getDescription(),
                            Printings.RED);
                }
                else if(transaction.getType() == Transaction.TYPE_INCOME) {
                    // Incrementing total expenses
                    totalIncomes += transaction.getAmount();
                    Printings.print("\n" + (index++) + ": " +
                            transaction.getType() +
                            "    " + transaction.getAmount() +
                            "    " + transaction.getDateInString() +
                            "    " + transaction.getDescription(),
                            Printings.GREEN);
                }
                // Incrementing number of transactions
                noOfTransactions++;
            }
        }
        // Printing brief summary of the period
        Printings.print("\nTotal Income = " + totalIncomes +
                        "\nTotal Expense = " + -totalExpenses +
                        "\nNumber of Transactions = " + noOfTransactions,
                Printings.YELLOW);
    }
}
