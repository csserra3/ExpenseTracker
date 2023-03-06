package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;

// A tracker keeping track of expenses entered.
public class ExpenseTracker implements Writable {
    private final ArrayList<Expense> expenses;

    // constructs expense tracker with an empty list of expenses
    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
    }

    // REQUIRES: expense >= 0
    // MODIFIES: this
    // EFFECTS: adds a new expense to the list of expenses
    public void addExpense(Expense expense) {
        this.expenses.add(expense);
        EventLog.getInstance().logEvent(
                new Event("Added expense: $" + expense.getAmount() + ", " + expense.getTitle())
        );
    }

    // EFFECTS: get the total amount spent (in dollars)
    public int totalExpense() {
        int count = 0;
        for (Expense e : expenses) {
            count += e.amount;
        }
        return count;
    }

    // EFFECTS: get the total amount spent within a category (in dollars)
    public int totalExpenseInCategory(Expense.Category c) {
        int count = 0;
        for (Expense e : expenses) {
            if (e.category == c) {
                count += e.amount;
            }
        }
        return count;
    }

    // REQUIRES: category should not be empty
    // EFFECTS: see the titles of expenses in a category
    public String titlesOfExpense(Expense.Category c) {
        StringBuilder titles = new StringBuilder();
        for (Expense e : expenses) {
            if (e.category == c) {
                titles.append(e.title).append("\n");
            }
        }
        return titles.toString();
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    // EFFECTS: returns number of expenses in tracker
    public int numExpenses() {
        return expenses.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Food", expensesToJson(Expense.Category.FOOD));
        json.put("Recreation", expensesToJson(Expense.Category.RECREATION));
        json.put("School", expensesToJson(Expense.Category.SCHOOL));
        json.put("Transportation", expensesToJson(Expense.Category.TRANSPORTATION));
        json.put("Misc", expensesToJson(Expense.Category.MISC));
        return json;
    }

    // EFFECTS: returns expenses in this expense tracker as a JSON array
    private JSONArray expensesToJson(Expense.Category c) {
        JSONArray jsonArray = new JSONArray();
        for (Expense e : expenses) {
            if (c.equals(e.category)) {
                jsonArray.put(e.toJson());
            }
        }
        return jsonArray;
    }
}
