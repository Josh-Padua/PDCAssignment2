/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestingChanges;

/**
 *
 * @author Josh Ivan Padua
 */
public class ExpenseTracker {
    // Variables
    private final Menu menu;
    
    // Constructor
    public ExpenseTracker(ExpenseManager eM, User user) {
        menu = new Menu(eM, user);
    }
    
    // Starts expense tracker
    public void start() {
        boolean exit = false;
        while (!exit) {
            menu.displayMenu();
            String input = menu.scan.nextLine();
            
            // switch class to determine user input
            switch(input) {
                case "1":
                    menu.addExpenseMenu();
                    break;
                case "2":
                    menu.removeExpenseMenu();
                    break;
                case "3":
                    menu.viewExpensesMenu();
                    break;
                case "4":
                    menu.GenerateReportMenu();
                    break;
                case "x":
                case "X":
                    exit = true;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid selection. Please re-enter.");
                    break;
            }
        }
    }
}
