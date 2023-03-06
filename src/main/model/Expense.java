package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Creates a new expense.
public class Expense implements Writable {
    int amount;
    String title;
    String date;
    String description;
    Category category;

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("title", title);
        json.put("date", date);
        json.put("description", description);
        return json;
    }

    public enum Category {
        FOOD,
        RECREATION,
        SCHOOL,
        TRANSPORTATION,
        MISC
    }

    // REQUIRES: amount of expense >= 0, title of expense which is not an empty string, and a category type
    // EFFECTS: amount is set to amount; title is set to title; category is set to category; description is added;
    //          date is set to the date of when expense was added.
    public Expense(int amount, String title, Category category) {
        this.amount = amount;
        this.title = title;
        this.date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.description = "";
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
