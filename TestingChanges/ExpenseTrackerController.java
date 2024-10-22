/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestingChanges;

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
        loginButton, addExpense, removeExpense, viewExpense, expenseReport, exitMenu
        addExpenseButton, exitAddExpense, removeButton, exitRemove, exitView, exitReport, 
        */
        
        this.panel.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = panel.userInput.getText();
                user = new User(name);
                Map<String, Integer> userMap = UserData.loadUsers();
                UserData.checkUser(user, userMap);
                ExpenseManager eM = new ExpenseManager();
                
                if (eM.checkUserExpenses(user)) {
                    JOptionPane.showMessageDialog(panel, "Welcome back " + user.getUser() + "!");
                    eM.loadUserExpenses(user);
                } else {
                    JOptionPane.showMessageDialog(panel, user.getUser() + " not found. Adding user!");
                }
                
                panel.menuPanel();
            }
        });
        
        // All exit methods
        this.panel.exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        this.panel.exitAddExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        this.panel.exitRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        
        this.panel.exitView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
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
