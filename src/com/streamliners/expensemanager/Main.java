package com.streamliners.expensemanager;

import com.streamliners.expensemanager.driver.Printings;
import com.streamliners.expensemanager.driver.TransactionOperations;
import com.streamliners.expensemanager.models.Transaction;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List for all the transactions
        ArrayList<Transaction> transactions = new ArrayList<>();

        String menu = "\nPlease choose from the given options: " +
                "\n0: To exit Application" +
                "\n1: New Transaction" +
                "\n2: Edit Transaction" +
                "\n3: Remove Transaction" +
                "\n4: See All Transactions" +
                "\n5: Filter Transactions" +
                "\n" +
                "\nYour response: ";

        // Loop until the user exit the application
        while (true) {
            System.out.print(menu);

            // Options chosen in the driven menu
            int chooseOption = scanner.nextInt();

            switch (chooseOption) {
                case 0:
                    Printings.print("You closed the application.", Printings.YELLOW);
                    return;
                case 1:
                    System.out.println("You select to add new transaction.");
                    Transaction transaction = TransactionOperations.newTransaction();

                    // Making sure that the transaction is not null
                    if (transaction != null) {
                        transactions.add(transaction);
                        Printings.print("DONE! Addition successful.\n", Printings.GREEN);
                    }
                    break;
                case 2:
                    System.out.println("You select to edit a transaction.");
                    Transaction transaction1 = TransactionOperations.getTransaction(transactions);

                    // Making sure that the transaction is not null
                    if (transaction1 != null) {
                        TransactionOperations.editTransaction(transaction1);
                        Printings.print("DONE! Editing successful.\n", Printings.GREEN);
                    }
                    break;
                case 3:
                    System.out.println("You select to remove transaction.");
                    Transaction transaction2 = TransactionOperations.getTransaction(transactions);

                    // Making sure that the transaction is not null
                    if (transaction2 != null) {
                        transactions.remove(transaction2);
                        Printings.print("DONE! Removed successfully.\n", Printings.GREEN);
                    }
                    break;
                case 4:
                    System.out.println("You select to see all transactions.");
                    TransactionOperations.seeAllTransactions(transactions);
                    break;
                case 5:
                    System.out.println("You select to filter transactions.");
                    TransactionOperations.filterTransactions(transactions);
                    break;
                default:
                    Printings.print("ERROR! Incorrect option.\n", Printings.RED);
            }
        }
    }
}
