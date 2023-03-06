import model.Expense;
import model.ExpenseTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTrackerTest {
    ExpenseTracker testTracker;

    @BeforeEach
    public void runBefore() {
        testTracker = new ExpenseTracker();
    }

    @Test
    void addExpenseTest() {
        // EFFECTS: adds a new expense to the list of expenses
        testTracker.addExpense(new Expense(5, "coffee", Expense.Category.FOOD));
        assertEquals(1, testTracker.getExpenses().size());

        testTracker.addExpense(new Expense(3, "tea", Expense.Category.FOOD));
        assertEquals(2, testTracker.getExpenses().size());
    }

    @Test
    void totalExpenseTest() {
        // EFFECTS: get the total amount spent (in dollars)
        testTracker.addExpense(new Expense(5, "coffee", Expense.Category.FOOD));
        testTracker.addExpense(new Expense(100, "textbook", Expense.Category.SCHOOL));
        assertEquals(105, testTracker.totalExpense());
    }

    @Test
    void totalExpenseInCategoryTest() {
        //get the total amount spent within a category (in dollars)
        testTracker.addExpense(new Expense(5, "coffee", Expense.Category.FOOD));
        testTracker.addExpense(new Expense(3, "tea", Expense.Category.FOOD));
        testTracker.addExpense(new Expense(100, "textbook", Expense.Category.SCHOOL));
        assertEquals(8, testTracker.totalExpenseInCategory(Expense.Category.FOOD));
        assertEquals(100, testTracker.totalExpenseInCategory(Expense.Category.SCHOOL));
    }

    @Test
    void titlesOfExpenseTest() {
        testTracker.addExpense(new Expense(5, "coffee", Expense.Category.FOOD));
        testTracker.addExpense(new Expense(3, "tea", Expense.Category.FOOD));
        testTracker.addExpense(new Expense(100, "textbook", Expense.Category.SCHOOL));
        assertEquals("coffee\ntea\n", testTracker.titlesOfExpense(Expense.Category.FOOD));
        assertEquals("textbook\n", testTracker.titlesOfExpense(Expense.Category.SCHOOL));
    }
}
