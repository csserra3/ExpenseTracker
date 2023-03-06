package persistence;

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(int amount, String title, Expense.Category category, Expense expense) {
        assertEquals(amount, expense.getAmount());
        assertEquals(title, expense.getTitle());
        assertEquals(category, expense.getCategory());
    }
}
