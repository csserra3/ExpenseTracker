package persistence;

import model.Expense;
import model.ExpenseTracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads expense tracker from JSON data stored in file
public class JsonReader {
    private String source;

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads expense tracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpenseTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseTracker(jsonObject);
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: parses expense tracker from JSON object and returns it
    private ExpenseTracker parseExpenseTracker(JSONObject jsonObject) {
        ExpenseTracker et = new ExpenseTracker();
        JSONArray foodExpenses = jsonObject.getJSONArray("Food");
        parseExpenses(et, foodExpenses, Expense.Category.FOOD);
        JSONArray recreationExpenses = jsonObject.getJSONArray("Recreation");
        parseExpenses(et, recreationExpenses, Expense.Category.RECREATION);
        JSONArray schoolExpenses = jsonObject.getJSONArray("School");
        parseExpenses(et, schoolExpenses, Expense.Category.SCHOOL);
        JSONArray transportationExpenses = jsonObject.getJSONArray("Transportation");
        parseExpenses(et, transportationExpenses, Expense.Category.TRANSPORTATION);
        JSONArray miscExpenses = jsonObject.getJSONArray("Misc");
        parseExpenses(et, miscExpenses, Expense.Category.MISC);
        return et;
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: et
    // EFFECTS: parses expenses from JSON object and adds them to expense tracker
    private void parseExpenses(ExpenseTracker et, JSONArray jasonArray, Expense.Category category) {

        for (Object json : jasonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(et, nextExpense, category);
        }
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: et
    // EFFECTS: parses expense from JSON object and adds it to expense tracker
    private void addExpense(ExpenseTracker et, JSONObject jsonObject, Expense.Category category) {
        int amount = jsonObject.getInt("amount");
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");
        String title = jsonObject.getString("title");
        Expense expense = new Expense(amount, title, category);
        expense.setDate(date);
        expense.setDescription(description);
        et.addExpense(expense);
    }
}
