package ui;

import model.ExpenseTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Persistence Manager which saves and loads tracker history.
public class PersistenceManager {

    private static final String JSON_STORE = "./data/expensetracker.json";
    private final Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Creates a new Persistence Manager.
    public PersistenceManager() {
        input = new Scanner(System.in).useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: saves current expenses
    public void saveExpenses(ExpenseTracker expenseTracker) {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseTracker);
            jsonWriter.close();
            System.out.println("Saved expenses to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads previous expense history
    public ExpenseTracker loadExpenses() {
        try {
            ExpenseTracker expenseTracker = jsonReader.read();
            System.out.println("Loaded expenses from " + JSON_STORE);
            return expenseTracker;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return new ExpenseTracker();
    }
}
