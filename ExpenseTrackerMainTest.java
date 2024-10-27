/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Assignment2;

import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Josh Ivan Padua
 */
public class ExpenseTrackerMainTest {
    private static User testUser;
    private static DBManager db;
    private static ExpenseManager eM;
    
    @BeforeClass
    public static void setUpClass() {
        db = new DBManager();
        eM = new ExpenseManager();
        testUser = new User("testing");
        Map<String, Integer> users = UserData.loadUsers();
        UserData.checkUser(testUser, users);
        UserData.saveUsers(users);
    }
    
    @AfterClass
    public static void tearDownClass() {
        db.closeConnections();
        db = null;
        eM = null;
        testUser = null;
    }
    
    @Before
    public void setUp() {
        eM.loadUserExpenses(testUser);
    }
    
    @After
    public void tearDown() {
        ArrayList<Expense> expenses = eM.getExpenses(testUser);
        ExpenseData.saveExpenses(testUser, new ArrayList<>());
    }

    /**
     * Test of main method, of class ExpenseTrackerMain.
     */
    @Test
    public void testExistingUser() {
        System.out.println("Testing Existing User");
        Map<String, Integer> users = UserData.loadUsers();
        assertTrue("User should be in database", users.containsKey(testUser.getUser()));
    }
    
    @Test
    public void testEmptyExpensesForNewUser() {
        System.out.println("Adding a new user with no expenses");
        User trialUser = new User("tester");
        eM.loadUserExpenses(trialUser);
        ArrayList<Expense> expenses = eM.getExpenses(trialUser);
        assertTrue("New user should have no expenses", expenses.isEmpty());
    }
    
    @Test
    public void testAddExpense() {
        System.out.println("Testing Add Expense");
        Expense expense = new Expense("Transport", "Gas", 150.22);
        eM.addExpense(testUser, expense);
        eM.saveUserExpenses(testUser);
        ArrayList<Expense> expenses = eM.getExpenses(testUser);
        assertTrue("Expense should be added", expenses.contains(expense));
    }
    
    @Test
    public void testRemoveExpense() {
        System.out.println("Testing Remove Expense");
        Expense expense = new Expense("Transport", "Gas", 150.22);
        eM.addExpense(testUser, expense);
        eM.saveUserExpenses(testUser);
        ArrayList<Expense> expenses = eM.getExpenses(testUser);
        assertTrue("Expense should be added", expenses.contains(expense));
        eM.removeExpense(testUser, expense);
        eM.saveUserExpenses(testUser);
        ArrayList<Expense> newExpenses = eM.getExpenses(testUser);
        assertFalse("Expense should be removed", expenses.contains(expense));
    }
    
    @Test
    public void testLoadUserExpenses() {
        System.out.println("Testing Loading User Expenses");
        Expense test1 = new Expense("Transport", "Motorcycle", 14999.98);
        Expense test2 = new Expense("Rent", "Apartment", 522.50);
        eM.addExpense(testUser, test1);
        eM.addExpense(testUser, test2);
        eM.saveUserExpenses(testUser);
        eM.loadUserExpenses(testUser);
        ArrayList<Expense> expenses = eM.getExpenses(testUser);
        assertEquals("Expenses match saved expenses", 2, expenses.size());
    }
}
