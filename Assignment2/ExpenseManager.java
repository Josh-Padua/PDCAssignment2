/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import TestingChanges.*;
import Assignment2.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Josh Ivan Padua
 */


public class ExpenseManager {
    // Variables
    private final HashMap<String, ArrayList<Expense>> userExpenses;
    private final DBManager db = new DBManager();

    // Constructor
    public ExpenseManager() {
        userExpenses = new HashMap<>();
    }
    
    public void addExpense(User user, Expense expense) {
        // Checks for existing user
        if (!userExpenses.containsKey(user.getUser())) {
            userExpenses.put(user.getUser(), new ArrayList<>());
        }
        
        ArrayList<Expense> expenses = userExpenses.get(user.getUser());
        expenses.add(expense);
    }
    
    public void removeExpense(User user, Expense expense) {
        ArrayList<Expense> expenses = userExpenses.get(user.getUser());
        // Condition makes sure you can't remove expenses if there are no expenses to remove
        if (expenses != null) {
            expenses.remove(expense);
        }
    }
    
    // GPT Assisted to fix the user check part of my program
    public ArrayList<Expense> getExpenses(User user) {
        // Condition to check if the user exists, otherwise return empty ArrayList
        if (userExpenses.containsKey(user.getUser())) {
            return userExpenses.get(user.getUser());
        } else {
            return new ArrayList<>();
        }
    }
    
    // Method changed from File I/O to jdbc
    public void loadUserExpenses(User user) {
        ArrayList<Expense> expenses = new ArrayList<>();
        
        try {
            Connection conn = db.getConnection();
            String SQLQuery = "SELECT category, description, amount FROM expenses WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(SQLQuery);
            statement.setString(1, user.getUser());
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                String category = rs.getString("category");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                
                Expense expense = new Expense(category, description, amount);
                expenses.add(expense);
            }
            
            userExpenses.put(user.getUser(), expenses);
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println("Error loading user expenses from DB: " + ex.getMessage());
        }
    }
    
    // new method created to check for user expenses for jdbc
    public boolean checkUserExpenses(User user) {
        boolean expensesExist = false;
        
        try {
            Connection conn = db.getConnection();
            String SQLQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            
            PreparedStatement statement = conn.prepareStatement(SQLQuery);
            statement.setString(1, user.getUser());
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    expensesExist = true;
                }
            }
            
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println("Error checking user in DB: " + ex.getMessage());
        }
        
        return expensesExist;
    }
    
    public void saveUserExpenses(User user) {
        ArrayList<Expense> expenses = userExpenses.get(user.getUser());
        if (expenses != null) {
            ExpenseData.saveExpenses(user, new ArrayList<>(expenses));
        }
    }
}
