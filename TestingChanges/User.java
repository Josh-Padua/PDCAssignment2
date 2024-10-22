/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestingChanges;

/**
 *
 * @author Josh Ivan Padua
 */

public class User {
    
    // Variables
    private String name;
    
    // Constructor
    public User(String name) {
        this.name = name;
    }
        
    // Getters & Setters using refractor

    /**
     * @return the user
     */
    public String getUser() {
        return name;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.name = user;
    }
    
    
}
