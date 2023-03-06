package persistence;

import model.Expense;
import model.ExpenseTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    private JsonReader testReader;

    @Test
    void testReaderFileDoesntExist() {
        testReader = new JsonReader("./data/fileDoesntExist.json");
        try {
            ExpenseTracker et = testReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderFileEmpty() {
        testReader = new JsonReader("./data/testEmptyExpenses.json");
        try {
            ExpenseTracker et = testReader.read();
            assertEquals(0, et.numExpenses());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReaderFilePass() {
        testReader = new JsonReader("./data/testGeneralExpenses.json");
        try {
            ExpenseTracker et = testReader.read();
            List<Expense> expenses = et.getExpenses();
            assertEquals(2, expenses.size());
            checkExpense(5, "coffee", Expense.Category.FOOD, expenses.get(0));
            checkExpense(50, "textbooks", Expense.Category.SCHOOL, expenses.get(1));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
