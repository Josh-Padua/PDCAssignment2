/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Josh Ivan Padua
 */

// Layout Notes https://iitk.ac.in/esc101/05Aug/tutorial/uiswing/layout/visual.html#box

public class Panel extends JFrame {
    private JPanel userPanel = new JPanel();
    private JPanel menuPanel = new JPanel();
    private JPanel addExpensePanel = new JPanel();
    private JPanel removeExpensePanel = new JPanel();
    private JPanel viewExpensePanel = new JPanel();
    private JPanel expenseReportPanel = new JPanel();
    
    // userPanel
    private JLabel uName = new JLabel("Username: ");
    public JTextField userInput = new JTextField(10);
    public JButton loginButton = new JButton("Log in");
    
    // menuPanel
    public JButton addExpense = new JButton("Add Expenses");
    public JButton removeExpense = new JButton("Remove Expenses");
    public JButton viewExpense = new JButton("View Expenses");
    public JButton expenseReport = new JButton("Expense Report");
    
    public JButton exitMenu = new JButton("Exit");
    
    // addExpensePanel
    private JLabel category = new JLabel("Category: ");
    public JTextField categoryInput = new JTextField(50);
    private JLabel description = new JLabel("Description: ");
    public JTextField descriptionInput = new JTextField(50);
    private JLabel amount = new JLabel("Amount: ");
    public JTextField amountInput = new JTextField(10);
    public JButton addExpenseButton = new JButton("Add Expense");
    
    public JButton menuAddExpense = new JButton("Menu");
    public JButton exitAddExpense = new JButton("Exit");

    // removeExpensePanel
    private JTextArea removeExpenseList = new JTextArea(15, 40);
    private JLabel expenseToRemove = new JLabel("Expense To Remove: ");
    public JTextField removeInput = new JTextField(3);
    
    public JPanel removeExpenseButtons = new JPanel();
    public JButton removeButton = new JButton("Remove");
    public JButton menuRemoveExpense = new JButton("Menu");
    public JButton exitRemove = new JButton("Exit");
    
    // viewExpensePanel
    private JTextArea viewExpenseList = new JTextArea(15, 40);
    
    public JPanel viewExpenseButtons = new JPanel();
    public JButton menuViewExpense = new JButton("Menu");
    public JButton exitView = new JButton("Exit");
    
    // expenseReportPanel
    private JTextArea reportText = new JTextArea(15, 40);
    
    public JPanel expenseReportButtons = new JPanel();
    public JButton menuExpenseReport = new JButton("Menu");
    public JButton exitReport = new JButton("Exit");
    
    
    public Panel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 100);
        this.setLocationRelativeTo(null); // align to center of screen
        
        // userPanel
        userPanel.add(uName);
        userPanel.add(userInput);
        userPanel.add(loginButton);
        
        // menuPanel
        menuPanel.add(addExpense);
        menuPanel.add(removeExpense);
        menuPanel.add(viewExpense);
        menuPanel.add(expenseReport);
        menuPanel.add(exitMenu);
        
        // addExpensePanel
        addExpensePanel.add(category);
        addExpensePanel.add(categoryInput);
        addExpensePanel.add(description);
        addExpensePanel.add(descriptionInput);
        addExpensePanel.add(amount);
        addExpensePanel.add(amountInput);
        addExpensePanel.add(addExpenseButton);
        addExpensePanel.add(menuAddExpense);
        addExpensePanel.add(exitAddExpense);
        
        // removeExpensePanel
        removeExpensePanel.setLayout(new BorderLayout());
        removeExpenseList.setEditable(false);
        removeExpensePanel.add(new JScrollPane(removeExpenseList), BorderLayout.CENTER);
        removeExpenseButtons.add(expenseToRemove);
        removeExpenseButtons.add(removeInput);
        removeExpenseButtons.add(removeButton);
        removeExpenseButtons.add(menuRemoveExpense);
        removeExpenseButtons.add(exitRemove);
        removeExpensePanel.add(removeExpenseButtons, BorderLayout.SOUTH);
        
        // viewExpensePanel
        viewExpensePanel.setLayout(new BorderLayout());
        viewExpenseList.setEditable(false);
        viewExpensePanel.add(new JScrollPane(viewExpenseList), BorderLayout.CENTER);
        viewExpenseButtons.add(menuViewExpense);
        viewExpenseButtons.add(exitView);
        viewExpensePanel.add(viewExpenseButtons, BorderLayout.SOUTH);
        
        // expenseReportPanel
        reportText.setEditable(false);
        reportText.setLineWrap(true);
        reportText.setWrapStyleWord(true);
        expenseReportPanel.setLayout(new BorderLayout());
        expenseReportPanel.add(new JScrollPane(reportText), BorderLayout.CENTER);
        expenseReportButtons.add(menuExpenseReport);
        expenseReportButtons.add(exitReport);
        expenseReportPanel.add(expenseReportButtons, BorderLayout.SOUTH);
        
        
        
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
    
    public void updateRemoveExpenseList(ArrayList<Expense> expenses) {
        removeExpenseList.setText("");
        if (expenses.isEmpty()) {
            removeExpenseList.append("No existing expenses");
        } else {
            removeExpenseList.append("Expenses:\n");
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                removeExpenseList.append((i+1) + ". " + expense.getCategory() + ": " + expense.getDescription() + " - $" + expense.getAmount() + "\n");
            }
        }
    }

    public void viewExpensePanel() {
        this.getContentPane().removeAll();
        this.add(viewExpensePanel);
        this.revalidate();
        this.repaint();
    }
    
    
    public void updateViewExpenseList(ArrayList<Expense> expenses) {
        viewExpenseList.setText("");
        if (expenses.isEmpty()) {
            viewExpenseList.append("No existing expenses");
        } else {
            viewExpenseList.append("Expenses:\n");
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                viewExpenseList.append((i+1) + ". " + expense.getCategory() + ": " + expense.getDescription() + " - $" + expense.getAmount() + "\n");
            }
        }
    }

    public void expenseReportPanel(String report) {
        this.getContentPane().removeAll();
        reportText.setText(report);
        this.add(expenseReportPanel);
        this.revalidate();
        this.repaint();
    }    
}
