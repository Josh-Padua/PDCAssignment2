/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment2;

import TestingChanges.*;

/**
 *
 * @author Josh Ivan Padua
 */
public class ExpenseTrackerMain {
    public static void main(String[] args) {
        Panel panel = new Panel();
        panel.setVisible(true);
        DBManager dbManager = new DBManager();
        ExpenseTrackerController eTController = new ExpenseTrackerController(panel, dbManager);
    }
}
