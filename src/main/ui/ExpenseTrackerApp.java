package ui;

import model.Expense;
import model.ExpenseTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Expense Tracker APP User Interface
public class ExpenseTrackerApp {
    private static final String JSON_STORE = "./data/expensetracker.json";
    ExpenseTracker expenseTracker;
    private final Scanner input = new Scanner(System.in).useDelimiter("\n");
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    public ExpenseTrackerApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        String command = null;
        boolean cont;
        expenseTracker = new ExpenseTracker();
        System.out.println("Welcome to ExpenseTrackerApp.");
        do {
            cont = processCommand();
        } while (cont);
        System.out.println("Goodbye!");
        input.close();
    }

    // EFFECTS: add expense to the ExpenseTracker
    private void addExpenseToTracker() {
        System.out.println("Enter the amount, title, and category of your expense.");
    }

    // EFFECTS: get the total amount spent (in dollars)
    private void totalExpense() {
        System.out.println("Enter the amount, title, and category of your expense.");
    }

    @SuppressWarnings("methodlength")
    private boolean processCommand() {
        System.out.println("Press 'a' to add expense to tracker. Press 'v' to view total expenses, "
                + "'c' to view expenses spent in a category, 't' to view titles within a category. "
                + "Press 's' to save expenses, and 'l' to load past expenses. Press any other"
                + " key to quit.");
        String command = input.next();
        switch (command) {
            case "a": {
                createAndAddExpense();
                return true;
            }
            case "v": {
                System.out.println("Your total is: $" + expenseTracker.totalExpense());
                return true;
            }
            case "c": {
                Expense.Category c = selectCategory();
                System.out.println("Your total expenses in this category is: $"
                        + expenseTracker.totalExpenseInCategory(c));
                return true;
            }
            case "t": {
                Expense.Category c = selectCategory();
                System.out.println("The titles of your expenses are: \n"
                        + expenseTracker.titlesOfExpense(c));
                return true;
            }
            case "s": {
                saveExpenses();
                return true;
            }
            case "l": {
                loadExpenses();
                return true;
            }
            default:
                return false;
        }
    }

    private void saveExpenses() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseTracker);
            jsonWriter.close();
            System.out.println("Saved expenses to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadExpenses() {
        try {
            expenseTracker = jsonReader.read();
            System.out.println("Loaded expenses from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Creates and adds expense to tracker
    private void createAndAddExpense() {
        Expense.Category category = selectCategory();
        System.out.println("Enter the amount spent in dollars.");
        int amount = input.nextInt();
        System.out.println("Enter the title of your expense.");
        String title = input.next();
        System.out.println("Enter the description of your expense.");
        String description = input.next();
        Expense expense = new Expense(amount, title, category);
        expense.setDescription(description);
        expenseTracker.addExpense(expense);
        System.out.println("Expense added to tracker.");

    }

    // EFFECTS: selects the category chosen by user
    private Expense.Category selectCategory() {
        System.out.println("Select your category: \n 'f' -> Food \n 'r' -> Recreation \n 's' -> School \n "
                + "'t' -> Transportation \n Press any other key for Misc.");
        String command = input.next();
        switch (command) {
            case "f":
                return Expense.Category.FOOD;
            case "r":
                return Expense.Category.RECREATION;
            case "s":
                return Expense.Category.SCHOOL;
            case "t":
                return Expense.Category.TRANSPORTATION;
            default:
                return Expense.Category.MISC;
        }
    }
}
