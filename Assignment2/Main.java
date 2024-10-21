/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import java.util.Scanner;
import java.util.Map;

/**
 *
 * @author Josh Ivan Padua
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("          Welcome to          ");
        System.out.println("             your             ");
        System.out.println("       Expense Tracker!       ");
        System.out.println();
        
        Scanner scan = new Scanner(System.in);
        
        // Gets userName
        System.out.print("Please enter your first name: ");
        String name = scan.nextLine();
        
        User user = new User(name);
        Map<String, Integer> userMap = UserData.loadUsers();
        UserData.checkUser(user, userMap);
        
        ExpenseManager eM = new ExpenseManager();
        ExpenseTracker eT = new ExpenseTracker(eM, user);
        
        // Condition to check if user has an existing record
        // Old code modified to account for jdbc
        if (eM.checkUserExpenses(user)) {
            eM.loadUserExpenses(user);
            System.out.println("Welcome back " + user.getUser() + "!");
        } else {
            System.out.println("New user detected. Creating a new Expense Tracker for you " + user.getUser() + "!");
        }
        
        // Start the expenseTracker Loop
        eT.start();
        
        // Save user's expenses before exiting
        eM.saveUserExpenses(user);
        UserData.saveUsers(userMap);
        System.out.println("Expenses saved. see you next time!");
    }
}
