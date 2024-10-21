/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Josh Ivan Padua
 */

public class Menu {
    
    // Variables
    private final ExpenseManager eM;
    private final User user;
    Scanner scan;
    
    // Constructor
    public Menu(ExpenseManager eM, User user) {
        this.eM = eM;
        this.user = user;
        this.scan = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\nPlease select your Function:");
        System.out.println("1. Add Expenses");
        System.out.println("2. Remove Expenses");
        System.out.println("3. View Expenses");
        System.out.println("4. Generate Expense Report");
        System.out.println("[Press x to Exit]");
        System.out.print("Selected: ");
    }
    
    public void viewExpensesMenu() {
        System.out.println();
        ArrayList<Expense> expenses = eM.getExpenses(user);
        if (expenses.isEmpty()) {
            System.out.println("No expenses on record.");
        } else {
            System.out.println("All Expenses:");
            // Loop and print all expenses
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                System.out.println((i+1) + ". " + expense.getCategory() + ": " + expense.getDescription() + " - $" + expense.getAmount());
            }
        }
    }
    
    public void addExpenseMenu() {
        System.out.println("\nAdd Expense");
        System.out.print("Expense Category: ");
        String category = scan.nextLine();
        System.out.print("Expense Description: ");
        String description = scan.nextLine();
        // Error handling
        double amount = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Expense Cost: $");
            try {
                amount = Double.parseDouble(scan.nextLine());
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid value.");
            }
        }
        Expense expense = new Expense(category, description, amount);
        eM.addExpense(user, expense);
        System.out.println("Expense added.");
    }

    public void removeExpenseMenu() {
        System.out.println("\nRemove Expense");
        ArrayList<Expense> expenses = eM.getExpenses(user);
        if (expenses.isEmpty()) {
            System.out.println("No expenses on record.");
        } else {
            viewExpensesMenu();
            int input = 0;
            // Error Handling
            boolean valid = false;
            
            while (!valid) {
                System.out.print("Choose which expense to remove. Number: ");
                try {
                    input = Integer.parseInt(scan.nextLine());
                    if (input > 0 && input <= expenses.size()) {
                        Expense expense = expenses.get(input - 1);
                        eM.removeExpense(user, expense);
                        System.out.println("Expense removed.");
                        valid = true;
                    } else {
                        System.out.print("Invalid input. Please choose a number between 1 and " + expenses.size() + ": ");
                    }
                    
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid value.\n");
                }
            }
        }
    }
    
    public void GenerateReportMenu() {
        System.out.println("Generating Report... \n");
        ReportGenerator rG = new ReportGenerator(eM);
        rG.generateReport(user);
    }
}
