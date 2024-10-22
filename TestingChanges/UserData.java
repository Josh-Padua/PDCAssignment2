/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestingChanges;

// Importing libraries
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

/**
 *
 * @author Josh Ivan Padua
 */

/*
This code was taken from assignment 1 and modified from file I/O to jdbc.
*/
public class UserData {
    
    private static DBManager db = new DBManager();
    
    public static Map<String, Integer> loadUsers() {
        Map<String, Integer> userMap = new HashMap<>();
        try {
            Connection conn = db.getConnection();
            String sqlQuery = "SELECT username, login_count FROM users";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            
            while (rs.next()) {
                String username = rs.getString("username");
                int loginCount = rs.getInt("login_count");
                userMap.put(username, loginCount);
            }
        } catch (SQLException ex) {
            System.err.println("Error loading users: " + ex.getMessage());
        }
        return userMap;
    }
    
    
    public static void saveUsers(Map<String, Integer> userMap) {
        try {
            Connection conn = db.getConnection();
            String SQLUpdate = "UPDATE users SET login_count = ? WHERE username = ?";
            String SQLInsert = "INSERT INTO users (username, login_count) VALUES (?, ?)";
            
            PreparedStatement update = conn.prepareStatement(SQLUpdate);
            PreparedStatement insert = conn.prepareStatement(SQLInsert);
            
            for (Map.Entry<String, Integer> entry : userMap.entrySet()) {
                String username = entry.getKey();
                int loginCount = entry.getValue();
                
                update.setInt(1, loginCount);
                update.setString(2, username);
                
                // condition to check if the user has not been found it adds it to queue
                int rowsChanged = update.executeUpdate();
                if (rowsChanged == 0) {
                    insert.setString(1, username);
                    insert.setInt(2, loginCount);
                    insert.executeUpdate();
                }
                
            }
        } catch (SQLException ex) {
            System.err.println("Error saving users: " + ex.getMessage());
        }
    }
    
    // Checks the map if the user has used the expense tracker before
    public static void checkUser(User user, Map<String, Integer> userMap) {
        if (userMap.containsKey(user.getUser())) {
            int count = userMap.get(user.getUser());
            userMap.put(user.getUser(), count + 1);
        } else {
            userMap.put(user.getUser(), 1);
        }
    }
}
