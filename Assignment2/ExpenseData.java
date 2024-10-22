/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

// Importing libraries
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Josh Ivan Padua
 */

/*
This code was taken from assignment 1 and modified from file I/O to jdbc.
*/
public class ExpenseData {
    
    private static DBManager db = new DBManager();
    
    public static void saveExpenses(User user, ArrayList<Expense> expenses) {
        try {
            Connection conn = db.getConnection();
            conn.setAutoCommit(false); // gpt assisted to fix atomic error with executing batch of queries
            String delete = "DELETE FROM Expenses WHERE username = ?";
            PreparedStatement SQLDelete = conn.prepareStatement(delete);
            SQLDelete.setString(1, user.getUser());
            SQLDelete.executeUpdate();
            
            String insert = "INSERT INTO Expenses (username, category, description, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement SQLInsert = conn.prepareStatement(insert);
            
            for (Expense e : expenses)
            {
                SQLInsert.setString(1, user.getUser());
                SQLInsert.setString(2, e.getCategory());
                SQLInsert.setString(3, e.getDescription());
                SQLInsert.setDouble(4, e.getAmount());
                SQLInsert.addBatch(); // add the command to a list that can all be executed at once
            }
            SQLInsert.executeBatch();
            conn.commit();
            System.out.println("Expenses added to database.");
        } catch (SQLException ex) {
            System.err.println("Error saving expenses: " + ex.getMessage());
            
            // Using GPT to add extra conditions to debug my code.
            SQLException nextEx = ex.getNextException();
            while (nextEx != null) {
                System.err.println("Next exception: " + nextEx.getMessage());
                nextEx = nextEx.getNextException();
            }
            
            try {
                db.getConnection().rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                db.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Error resetting auto-commit: " + ex.getMessage());
            }
        }
    }
    
    public static ArrayList<Expense> loadExpenses(User user) {
        ArrayList<Expense> expenses = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            String sqlQuery = "SELECT category, description, amount FROM Expenses WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, user.getUser());
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                String category = rs.getString("category");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                expenses.add(new Expense(category, description, amount));
            }
        } catch (SQLException ex) {
            System.err.println("Error loading expenses: " + ex.getMessage());
        }
        return expenses;
    }
}
