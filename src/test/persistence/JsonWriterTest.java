package persistence;

import model.Expense;
import model.ExpenseTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    private JsonWriter testWriter;
    private ExpenseTracker et;

    @Test
    void testWriterFileDoesntExist() {
        try {
            et = new ExpenseTracker();
            testWriter = new JsonWriter("./data/fileWeirdName\0.json");
            testWriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterFileEmpty() {
        try {
            et = new ExpenseTracker();
            testWriter = new JsonWriter("./data/testWriterEmptyExpenses.json");
            testWriter.open();
            testWriter.write(et);
            testWriter.close();

            JsonReader testReader = new JsonReader("./data/testWriterEmptyExpenses.json");
            et = testReader.read();
            assertEquals(0, et.numExpenses());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFilePass() {
        try {
            et = new ExpenseTracker();
            et.addExpense(new Expense(20, "movies", Expense.Category.RECREATION));
            testWriter = new JsonWriter("./data/testWriterGeneralExpenses.json");
            testWriter.open();
            testWriter.write(et);
            testWriter.close();

            JsonReader testReader = new JsonReader("./data/testWriterGeneralExpenses.json");
            et = testReader.read();
            List<Expense> expenses = et.getExpenses();
            assertEquals(1, expenses.size());
            checkExpense(20, "movies", Expense.Category.RECREATION, expenses.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
