/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import TestingChanges.*;
import java.util.ArrayList;

/**
 *
 * @author Josh Ivan Padua
 */
public class ReportGenerator {
    private ExpenseManager eM;
    public ReportGenerator(ExpenseManager eM) {
        this.eM = eM;
    }
    
    public void generateReport(User user) {
        System.out.println("Expense Report \n");
        System.out.print("Total Number of Expenses: " + calcExpenseCount(user) + "\n");
        System.out.print("Total Cost of Expenses: " + calcTotalExpenseAmount(user) + "\n");
        System.out.print("Highest Expense Cost: " + highestExpense(user) + "\n");
        System.out.print("Lowest Expense Cost: " + lowestExpense(user) + "\n");
    }
    
    private double calcTotalExpenseAmount(User user) {
        ArrayList<Expense> expenses = eM.getExpenses(user);
        double expenseTotal = 0;
        for (Expense e : expenses) {
            expenseTotal += e.getAmount();
        }
        // ChatGPT to display the value as a Rounded value
        return Math.round(expenseTotal * 100) / 100;
    }
    
    private int calcExpenseCount(User user) {
        ArrayList<Expense> expenses = eM.getExpenses(user);
        int count = 0;
        for (Expense e : expenses) {
            count++;
        }
        return count;
    }
    
    private double highestExpense(User user) {
        ArrayList<Expense> expenses = eM.getExpenses(user);
        double highest = 0;
        for (Expense e : expenses) {
            if (e.getAmount() > highest) {
                highest = e.getAmount();
            }
        }
        return highest;
    }
    
    private double lowestExpense(User user) {
        ArrayList<Expense> expenses = eM.getExpenses(user);
        double lowest = 999999999;
        for (Expense e : expenses) {
            if (e.getAmount() < lowest) {
                lowest = e.getAmount();
            }
        }
        return lowest;
    }
}