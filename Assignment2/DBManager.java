/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import java.sql.*;

/**
 *
 * @author Josh Ivan Padua
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
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
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
