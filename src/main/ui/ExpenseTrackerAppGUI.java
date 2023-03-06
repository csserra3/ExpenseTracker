package ui;

import model.Event;
import model.EventLog;
import model.Expense;
import model.ExpenseTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

// A Graphical User Interface for the Expense Tracker App.
public class ExpenseTrackerAppGUI {

    private static JPanel panel;
    private static JPanel panel2;
    private static JFrame frame;

    private static JLabel expenseLabel;
    private static JTextField expenseText;
    private static JLabel titleLabel;
    private static JTextField titleText;
    private static JSpinner categorySpinner;
    private static JLabel categoryLabel;
    private static JLabel imageLabel;

    private static JButton saveButton;
    private static JButton loadButton;
    private static JButton enterButton;
    private static JButton totalButton;
    private static JLabel displayMessage;
    private static final int frameWidth = 380;
    private static final int frameHeight = 600;

    private static ExpenseTracker expenseTracker;
    private static PersistenceManager persistenceManager;

    // MODIFIES: this
    // EFFECTS: Create GUI and show it
    public static void startApp() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(frameWidth, frameHeight);
        printLogOnClose();
        panel.setLayout(null);
        panel2 = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setLayout(new BorderLayout());
        frame.setLayout(new GridLayout(2, 1));
        expenseTracker = new ExpenseTracker();
        persistenceManager = new PersistenceManager();
        addButtons();
        addNewLabels();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: prints the log upon closing the App
    private static void printLogOnClose() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Iterator<Event> eventIterator = EventLog.getInstance().iterator();
                while (eventIterator.hasNext()) {
                    System.out.println(eventIterator.next());
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
            }
        });
    }

    // EFFECTS: Add expense and title labels and fields to GUI
    private static void addNewLabels() {
        expenseLabel = new JLabel("Expense");
        expenseLabel.setBounds(30, 20, 100, 30);
        panel.add(expenseLabel);
        expenseText = new JTextField(20);
        expenseText.setBounds(150, 20, 165, 30);
        panel.add(expenseText);

        titleLabel = new JLabel("Expense Title");
        titleLabel.setBounds(30, 60, 100, 30);
        panel.add(titleLabel);
        titleText = new JTextField(20);
        titleText.setBounds(150, 60, 165, 30);
        panel.add(titleText);

        addCategorySpinner();

        // display default message
        displayMessage = new JLabel("Welcome to Expense Tracker App.");
        displayMessage.setBounds(30, 270, 380, 50);
        panel2.add(displayMessage);
    }

    // EFFECTS: Add the category spinner and label to GUI
    private static void addCategorySpinner() {
        String[] categoryStrings = {
                Expense.Category.FOOD.name(),
                Expense.Category.RECREATION.name(),
                Expense.Category.SCHOOL.name(),
                Expense.Category.TRANSPORTATION.name(),
                Expense.Category.MISC.name(),
        };

        categorySpinner = new JSpinner(new SpinnerListModel(categoryStrings));
        categorySpinner.setBounds(150, 100, 165, 30);
        panel.add(categorySpinner);

        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(30, 100, 100, 30);
        panel.add(categoryLabel);
    }

    // EFFECTS: add all four buttons to GUI
    private static void addButtons() {
        addEnterButton();
        addTotalButton();
        addSaveButton();
        addLoadButton();
    }

    // MODIFIES: this, ExpenseTrackerApp
    // EFFECTS: Add enter button to GUI
    private static void addEnterButton() {
        enterButton = new JButton("Enter an expense to tracker");
        enterButton.setBounds(30, 150, 300, 25);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterButtonAction();
            }
        });
        panel.add(enterButton);
    }

    // MODIFIES: this, ExpenseTrackerApp
    // EFFECTS: Add total button to GUI
    private static void addTotalButton() {
        totalButton = new JButton("View total in selected Category");
        totalButton.setBounds(30, 180, 300, 25);
        panel.add(totalButton);

        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalButtonAction();
            }
        });
        panel.add(totalButton);
    }

    // EFFECTS: Add save button to GUI
    private static void addSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setBounds(30, 210, 300, 25);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonAction();
            }
        });
        panel.add(saveButton);
    }

    // EFFECTS: Add load button to GUI
    private static void addLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(30, 240, 300, 25);
        panel.add(loadButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadButtonAction();
            }
        });
        panel.add(loadButton);
    }

    // EFFECTS: Add expense to tracker and change display message to say it was added.
    private static void enterButtonAction() {
        Expense newExpense = new Expense(
                Integer.parseInt(expenseText.getText()),
                titleText.getText(),
                categoryToEnum()
        );
        expenseTracker.addExpense(newExpense);
        displayMessage.setText("Expense successfully added to " + categorySpinner.getValue().toString());
    }

    // EFFECTS: Display the total expenses in a selected category.
    private static void totalButtonAction() {
        Expense.Category c = categoryToEnum();
        displayMessage.setText("Your total expenses in this category is: $"
                + expenseTracker.totalExpenseInCategory(c));
        displayImage();
    }

    // EFFECTS: Save current tracker app expenses.
    private static void saveButtonAction() {
        persistenceManager.saveExpenses(expenseTracker);
        displayMessage.setText("Saved!");
    }

    // EFFECTS: Load previous tracker history.
    private static void loadButtonAction() {
        expenseTracker = persistenceManager.loadExpenses();
        displayMessage.setText("Loaded last saved history.");
    }

    // EFFECTS: Converts the category spinner item string to an enum
    private static Expense.Category categoryToEnum() {
        String category = categorySpinner.getValue().toString();
        return Expense.Category.valueOf(category);
    }

    //EFFECTS: display an image (of a stack of money) on the panel.
    private static void displayImage() {
        imageLabel = new JLabel();
        imageLabel.setBounds(50, 300, 200, 200);

        ImageIcon icon = new ImageIcon("./data/moneystack.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        imageLabel.setIcon(scaledIcon);

        panel2.add(imageLabel);
    }
}
