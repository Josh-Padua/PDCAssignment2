/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import TestingChanges.*;
import java.sql.*;

/**
 *
 * @author Josh Ivan Padua
 */

/*
Database setup with notes taken from PDC Task 8_02 & Task 09_3
*/
public class DBManager {
    private static final String USER_NAME = "expense";
    private static final String PASSWORD = "expense";
    private static final String URL = "jdbc:derby://localhost:1527/expense";
    
    Connection conn;
    
    public DBManager()
    {
        establishConnection();
    }
    
    public static void main(String[] args) {
        DBManager db = new DBManager();
        System.out.println(db.getConnection());
    }
    
    private void establishConnection() 
    {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement statement = conn.createStatement();
            String userTable = "users";
            String expenseTable = "expenses";
            
            if (!checkExistingTable(userTable)) {
                statement.executeUpdate("CREATE TABLE " + userTable + " (username VARCHAR(50) PRIMARY KEY NOT NULL, login_count INT)");
            }
            if (!checkExistingTable(expenseTable)) {
                statement.executeUpdate("CREATE TABLE " + expenseTable + " (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), username VARCHAR(255) NOT NULL, category VARCHAR(255) NOT NULL, description VARCHAR(500), amount DOUBLE NOT NULL)");
            }
            statement.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    private boolean checkExistingTable(String tableToCheck) {
        boolean flag = false;
        try {
            System.out.println("Checking tables");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(tableToCheck) == 0) {
                    System.out.println(tableName + " exists.");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            
        }
        return flag;
    }
    
    public void closeConnections()
    {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public ResultSet queryDB(String sql)
    {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultSet;
    }
    
    public void updateDB(String sql)
    {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
