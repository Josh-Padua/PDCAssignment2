/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import TestingChanges.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Josh Ivan Padua
 */
public class ExpenseTrackerController {
    private Panel panel;
    private DBManager db;
    private ExpenseManager eM;
    private User user;
    
    public ExpenseTrackerController(Panel panel, DBManager db) {
        this.panel = panel;
        this.db = db;
        this.eM = new ExpenseManager();
        
        /*
        all Buttons that require action listeners
        loginButton - userPanel
        addExpense, removeExpense, viewExpense, expenseReport - menuPanel
        addExpenseButton - addExpensePanel
        removeButton - removeExpensePanel
        
        exit and return buttons
        exitMenu - menuPanel
        menuAddExpense, exitAddExpense - addExpensePanel
        menuRemoveExpense, exitRemove - removeExpensePanel
        menuViewExpense, exitView - viewExpensePanel
        menuExpenseReport, exitReport - expenseReportPanel
        */
        
        // Login button and logic
        this.panel.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = panel.userInput.getText();
                user = new User(name);
                Map<String, Integer> userMap = UserData.loadUsers();
                UserData.checkUser(user, userMap);
                
                if (eM.checkUserExpenses(user)) {
                    JOptionPane.showMessageDialog(panel, "Welcome back " + user.getUser() + "!");
                    eM.loadUserExpenses(user);
                } else {
                    JOptionPane.showMessageDialog(panel, user.getUser() + " not found. Adding user!");
                }
                
                panel.menuPanel();
            }
        });
        
        // Menu Navigation Buttons
        this.panel.addExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.addExpensePanel();
            }
        });
        
        this.panel.removeExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Expense> expenses = eM.getExpenses(user);
                panel.updateRemoveExpenseList(expenses);
                panel.removeExpensePanel();
            }
        });
        
        this.panel.viewExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Expense> expenses = eM.getExpenses(user);
                panel.updateViewExpenseList(expenses);
                panel.viewExpensePanel();
            }
        });
        
        this.panel.expenseReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.expenseReportPanel();
            }
        });
        
        // Adding and removing expenses
        this.panel.addExpenseButton.addActionListener(new ActionListener() {
            // Implementing the addExpenseMenu method from Menu.java to be a button
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = panel.categoryInput.getText();
                String description = panel.descriptionInput.getText();
                double amount;
                try {
                    amount = Double.parseDouble(panel.amountInput.getText());
                    Expense expense = new Expense(category, description, amount);
                    eM.addExpense(user, expense);
                    JOptionPane.showMessageDialog(panel, "Expense added!");
                    panel.categoryInput.setText("");
                    panel.descriptionInput.setText("");
                    panel.amountInput.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid amount. Please enter a valid value.");
                    panel.amountInput.setText("");
                }
            }
        });
        
        this.panel.removeButton.addActionListener(new ActionListener() {
            // Implementing the removeExpenseMenu method from Menu.java to be a button
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Expense> expenses = eM.getExpenses(user);
                int input;
                try {
                    input = Integer.parseInt(panel.removeInput.getText());
                    if (input > 0 && input <= expenses.size()) {
                        Expense expense = expenses.get(input - 1);
                        eM.removeExpense(user, expense);
                        JOptionPane.showMessageDialog(panel, "Expense removed.");
                        ArrayList<Expense> updatedExpenseList = eM.getExpenses(user);
                        panel.updateRemoveExpenseList(updatedExpenseList);
                        panel.removeInput.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid input. Please choose a number between 1 and " + expenses.size());
                        panel.removeInput.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a valid value.");
                    panel.removeInput.setText("");
                }
                
            }
        });
        
        // All exit methods
        
        // menuPanel
        this.panel.exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        // addExpensePanel
        this.panel.menuAddExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.menuPanel();
            }
        });
        this.panel.exitAddExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        // removeExpensePanel
        this.panel.menuRemoveExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.menuPanel();
            }
        });
        this.panel.exitRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        // viewExpensePanel
        this.panel.menuViewExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.menuPanel();
            }
        });
        this.panel.exitView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        // expenseReportPanel
        this.panel.menuExpenseReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.menuPanel();
            }
        });
        this.panel.exitReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
    }
    
    // helper method to exit the program smoothly
    private void exitProgram() {
        Map<String, Integer> userMap = UserData.loadUsers();
        UserData.checkUser(user, userMap);
        UserData.saveUsers(userMap);
        eM.saveUserExpenses(user);
        JOptionPane.showMessageDialog(panel, "User Expenses saved!\nSee you next time!");
        System.exit(0);
    }
}
