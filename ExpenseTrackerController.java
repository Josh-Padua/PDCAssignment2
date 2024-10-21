/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestingChanges;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

/**
 *
 * @author Josh Ivan Padua
 */
public class ExpenseTrackerController {
    private Panel panel;
    private DBManager db;
    
    public ExpenseTrackerController(Panel panel, DBManager db) {
        this.panel = panel;
        this.db = db;
        
        this.panel.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = panel.userInput.getText();
                User user = new User(name);
                Map<String, Integer> userMap = UserData.loadUsers();
                UserData.checkUser(user, userMap);
                ExpenseManager eM = new ExpenseManager();
                ExpenseTracker eT = new ExpenseTracker(eM, user);
                panel.menuPanel();
            }
        });
    }
}
