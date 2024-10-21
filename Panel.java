/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestingChanges;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Josh Ivan Padua
 */
public class Panel extends JFrame {
    private JPanel userPanel = new JPanel();
    private JPanel menuPanel = new JPanel();
    private JPanel addExpensePanel = new JPanel();
    private JPanel removeExpensePanel = new JPanel();
    private JPanel viewExpensePanel = new JPanel();
    private JPanel expenseReportPanel = new JPanel();
    
    // userPanel
    private JLabel uName = new JLabel("Username: ");
    public JTextField userInput = new JTextField(50);
    public JButton loginButton = new JButton("Log in");
    
    // menuPanel
    public JButton addExpense = new JButton("Add Expenses");
    public JButton removeExpense = new JButton("Remove Expenses");
    public JButton viewExpense = new JButton("View Expenses");
    public JButton expenseReport = new JButton("Expense Report");
    public JButton exitMenu = new JButton("Exit");
    
    // addExpensePanel
    private JLabel category = new JLabel("Category: ");
    public JTextField categoryInput = new JTextField(255);
    private JLabel description = new JLabel("Description: ");
    public JTextField descriptionInput = new JTextField(500);
    private JLabel amount = new JLabel("Amount: ");
    public JTextField amountInput = new JTextField(10);
    public JButton addExpenseButton = new JButton("Add Expense");

    // removeExpensePanel
    private JLabel expenseToRemove = new JLabel("Expense To Remove: ");
    public JTextField removeInput = new JTextField(3);
    public JButton removeButton = new JButton("Remove");
    
    // viewExpensePanel
    public JButton exitView = new JButton("Exit");
    
    // expenseReportPanel
    public JButton exitReport = new JButton("Exit");
    
    
    public Panel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        
        userPanel.add(uName);
        userPanel.add(userInput);
        userPanel.add(loginButton);
        
        menuPanel.add(addExpense);
        menuPanel.add(removeExpense);
        menuPanel.add(viewExpense);
        menuPanel.add(expenseReport);
        menuPanel.add(exitMenu);
        
        addExpensePanel.add(category);
        addExpensePanel.add(categoryInput);
        addExpensePanel.add(description);
        addExpensePanel.add(descriptionInput);
        addExpensePanel.add(amount);
        addExpensePanel.add(amountInput);
        addExpensePanel.add(addExpenseButton);
        
        viewExpensePanel.add(exitView);
        
        expenseReportPanel.add(exitReport);
        
        
        
        this.add(userPanel); // default Panel to start on
    }
    
    public void menuPanel() {
        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.revalidate();
        this.repaint();
    }
    
    public void addExpensePanel() {
        this.getContentPane().removeAll();
        this.add(addExpensePanel);
        this.revalidate();
        this.repaint();
    }

    public void removeExpensePanel() {
        this.getContentPane().removeAll();
        this.add(removeExpensePanel);
        this.revalidate();
        this.repaint();
    }

    public void viewExpensePanel() {
        this.getContentPane().removeAll();
        this.add(viewExpensePanel);
        this.revalidate();
        this.repaint();
    }

    public void expenseReportPanel() {
        this.getContentPane().removeAll();
        this.add(expenseReportPanel);
        this.revalidate();
        this.repaint();
    }    
}
