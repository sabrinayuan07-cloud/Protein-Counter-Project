package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import org.json.JSONWriter;

import model.Event;
import model.EventLog;
import model.Food;
import model.FoodWithQty;
import model.InvalidDoubleException;
import model.MealPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents GUI class
public class ProteinCounterGUI extends JFrame implements WindowListener, WindowFocusListener, WindowStateListener {
    private JPanel panelStepOne;
    private JPanel mainContainer;
    private JButton btnCreateNewPlan;
    private JButton btnLoadFile;
    private JPanel panelStepTwo;
    private JTextField name;
    private JTextField proteinGoal;
    private JTextField quantityEaten;
    private JButton btnShowAllFoods;
    private JButton btnShowHighProteinFoods;
    private JButton btnAddFood;
    private JButton saveButton;
    private JList<Food> lstFoodOptions;
    private JList<FoodWithQty> lstFoodEaten;
    private DefaultListModel<Food> storeFoodOptions = new DefaultListModel<>();
    private DefaultListModel<FoodWithQty> storeFoodEaten = new DefaultListModel<>();
    private JPanel quantityPanel;
    private JPanel panelNameAndGoal;
    private JPanel panelFoodToAdd;
    private JPanel panelFoodEatenAndSave;
    private MealPlan mealPlan;
    private CardLayout cardLayout;

    // Constructs the GUI class with its first frame
    public ProteinCounterGUI() {
        super("Protein Couter Application");
        setSize(900, 600);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpMainContainer();
        setupPanelOne();
        setupPanelTwo();
        addWindowListener(this);
        addWindowFocusListener(this);
        addWindowStateListener(this);
        setVisible(true);
    }

    // EFFECTS: sets up the first frame that user sees
    public void setupPanelOne() {
        panelStepOne = new JPanel();
        panelStepOne.setBackground(Color.CYAN);
        panelStepOne.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        btnCreateNewPlan = new JButton("Create new meal plan");
        btnLoadFile = new JButton("Load file");
        buttonPanel.add(btnCreateNewPlan);
        buttonPanel.add(btnLoadFile);
        displayImage();
        JPanel panelMenuOptions = new JPanel(new GridBagLayout());
        panelMenuOptions.add(buttonPanel);
        panelStepOne.add(panelMenuOptions);
        setupButtonActionCreateNewPlan();
        setupButtonActionLoadFile();
        mainContainer.add(panelStepOne, "Welcome to the protein counter application");
    }

    private void setUpMainContainer() {
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        this.add(mainContainer);
    }

    // EFFECTS: sets up the 2nd frame to be able to add a food and view food eaten
    public void setupPanelTwo() {
        instantiateLists();
        panelStepTwo = new JPanel();
        panelStepTwo.setBackground(Color.GREEN);
        panelStepTwo.setLayout(new BorderLayout());
        setupNameAndProteinPanel();
        setupFoodToAddPanel();
        panelStepTwo.add(quantityPanel, BorderLayout.SOUTH);
        setupFoodEatenAndSavePanel();
        setupButtonActions();
        setupSaveButton();

        mainContainer.add(panelStepTwo, "Protein Counter Application");
    }

    // MODIFIES: instantiates list of food options
    private void instantiateLists() {
        storeFoodOptions.addElement(new Food("Chicken", 31));
        storeFoodOptions.addElement(new Food("Eggs", 13));
        storeFoodOptions.addElement(new Food("Milk", 3.3));
        storeFoodOptions.addElement(new Food("Salmon", 22));
        storeFoodOptions.addElement(new Food("Beef", 26));
        storeFoodOptions.addElement(new Food("Shrimp", 20));
        storeFoodOptions.addElement(new Food("Tofu", 8));
        storeFoodOptions.addElement(new Food("Oats", 11));
        storeFoodOptions.addElement(new Food("Pasta", 5));
        storeFoodOptions.addElement(new Food("Broccoli", 2.8));
        storeFoodOptions.addElement(new Food("Avocado", 2));
        storeFoodOptions.addElement(new Food("Banana", 1.1));
        storeFoodOptions.addElement(new Food("Bacon", 37));

        lstFoodOptions = new JList<>(storeFoodOptions);
    }

    // REQUIRES: user to click on load file button
    // MODIFIES: this
    // EFFECTS: sets up frame #2 and makes it visible
    private void setupButtonActionLoadFile() {
        btnLoadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainContainer, "Protein Counter Application");
                panelStepTwo.setVisible(true);
                loadFile();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads meal plan from file
    private void loadFile() {
        try {
            ProteinCounterApp proteinApp = new ProteinCounterApp(this.mealPlan);
            this.mealPlan = proteinApp.getMealPlanFromFile();
            name.setText(mealPlan.getName());
            proteinGoal.setText(String.valueOf(mealPlan.getProteinGoal()));
            
            // add food eaten from file onto list displayed:
            ArrayList<Food> foodsEaten = new ArrayList<>();
            foodsEaten = mealPlan.getAllFoodEaten();
            double quantity;

            for (Food f : foodsEaten) {
                quantity = mealPlan.getFoodQuantity(f);
                storeFoodEaten.addElement(new FoodWithQty(f, quantity));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // REQUIRES: user to click on create new plan button
    // EFFECTS: creates new meal plan
    private void setupButtonActionCreateNewPlan() {
        btnCreateNewPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainContainer, "Protein Counter Application");

                mealPlan = new MealPlan("", 0);
                panelStepTwo.setVisible(true);
            }
        });
    }

    // EFFECTS: adds name and protein goal labels and text fields for user to input
    private void setupNameAndProteinPanel() {
        panelNameAndGoal = new JPanel(new GridLayout(2, 2));
        panelNameAndGoal.setBackground(Color.PINK);

        JLabel labelName = new JLabel("Name: ");
        labelName.setBounds(10, 20, 80, 25);
        panelNameAndGoal.add(labelName);

        name = new JTextField(20);
        name.setBounds(100, 20, 165, 25);
        panelNameAndGoal.add(name);

        JLabel labelProteinGoal = new JLabel("Protein goal in grams: ");
        labelProteinGoal.setBounds(10, 20, 80, 25);
        panelNameAndGoal.add(labelProteinGoal);

        proteinGoal = new JTextField(20);
        proteinGoal.setBounds(100, 20, 165, 25);
        panelNameAndGoal.add(proteinGoal);

        panelStepTwo.add(panelNameAndGoal, BorderLayout.NORTH);

    }

    // MODIFIES: this
    // EFFECTS: sets up the panel to add food
    private void setupFoodToAddPanel() {
        panelFoodToAdd = new JPanel(new BorderLayout());
        panelFoodToAdd.setBackground(Color.BLUE);
        panelFoodToAdd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // add buttons panel on top of food options list
        btnShowAllFoods = new JButton("Show all foods");
        btnShowHighProteinFoods = new JButton("Only show high protein foods");

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(btnShowAllFoods);
        btnPanel.add(btnShowHighProteinFoods);
        panelFoodToAdd.add(btnPanel, BorderLayout.NORTH);

        lstFoodOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollFoodOptions = new JScrollPane(lstFoodOptions);
        panelFoodToAdd.add(scrollFoodOptions, BorderLayout.CENTER);

        // add quantity panel to bottom of list of foods
        quantityPanel = new JPanel(new GridLayout(1, 2));

        JLabel quantityLabel = new JLabel("Quantity eaten in grams: ");
        quantityEaten = new JTextField(10);

        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityEaten);

        btnAddFood = new JButton("Add food selected");
        panelFoodToAdd.add(btnAddFood, BorderLayout.PAGE_END);

        panelStepTwo.add(panelFoodToAdd);
    }

    // EFFECTS: adds panel that contains food eaten list and save button
    private void setupFoodEatenAndSavePanel() {
        panelFoodEatenAndSave = new JPanel(new BorderLayout());
        panelFoodEatenAndSave.setBackground(Color.MAGENTA);
        panelFoodEatenAndSave.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

        JLabel foodEatenLabel = new JLabel("Food eaten", JLabel.CENTER);
        foodEatenLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panelFoodEatenAndSave.add(foodEatenLabel, BorderLayout.CENTER);

        lstFoodEaten = new JList<>(storeFoodEaten);

        panelStepTwo.add(panelFoodEatenAndSave, BorderLayout.EAST);

        panelFoodEatenAndSave.add(lstFoodEaten, BorderLayout.SOUTH);

    }

    // EFFECTS: calls all methods to set up buttons needed on frame 2
    private void setupButtonActions() {
        setupButtonActionForShowAllFoods();
        setupButtonActionForHighProteinFoods();
        setupButtonActionAddFood();
    }

    // REQUIRES: user to click on show all foods button
    // MODIFIES: this
    // EFFECTS: shows all food options
    private void setupButtonActionForShowAllFoods() {
        btnShowAllFoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lstFoodOptions.setModel(storeFoodOptions);
            }
        });
    }

    // REQUIRES: user to click on only show high protein foods button
    // MODIFIES: this
    // EFFECTS: only shows high protein foods 
    private void setupButtonActionForHighProteinFoods() {
        btnShowHighProteinFoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<Food> highProteinList = new DefaultListModel<>();

                for (int i = 0; i < storeFoodOptions.getSize(); i++) {
                    Food food = storeFoodOptions.getElementAt(i);

                    if (food.getProteinCountPerHundredGrams() >= 15) {
                        highProteinList.addElement(food); // calls toString()
                    }
                }
                lstFoodOptions.setModel(highProteinList);
            }
        });
    }

    // REQUIRES: user to click on add food button
    // MODIFIES: this
    // EFFECTS: calls handleAddFood() method
    private void setupButtonActionAddFood() {
        btnAddFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddFood();
            }
        });
    }

    // REQUIRES: user must have selected a food item and input grams eaten to add food
    // MODIFIES: this
    // EFFECTS: adds food into food eaten list
    private void handleAddFood() {
        double quantity = 0;
        try {
            // System.out.println(quantityEaten.getText());
            quantity = handleGrams(quantityEaten.getText());
        } catch (InvalidDoubleException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        if (lstFoodOptions.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(panelStepTwo, "You must select a food item", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Food selectedFood = lstFoodOptions.getSelectedValue();
        storeFoodEaten.addElement(new FoodWithQty(selectedFood, quantity));
        mealPlan.addFood(selectedFood, quantity);
        JOptionPane.showMessageDialog(panelStepTwo, "Food successfully added", "Food added",
                JOptionPane.INFORMATION_MESSAGE);
        quantityEaten.setText("");
    }

    // REQUIRES: user must input a valid number before adding a food item
    // EFFECTS: 
    private double handleGrams(String quantity) throws InvalidDoubleException {
        if (quantity == null) {
            JOptionPane.showMessageDialog(panelStepTwo, "Please enter a valid number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDoubleException();
        }

        double grams;
        try {
            grams = Double.parseDouble(quantity);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panelStepTwo, "Please enter a valid number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDoubleException();
        }

        if (grams <= 0) {
            JOptionPane.showMessageDialog(panelStepTwo, "Enter a quantity higher than 0 grams", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDoubleException();
        }
        return grams;
    }

    // MODIFIES: this
    // EFFECTS: adds save button to panel
    private void setupSaveButton() {
        saveButton = new JButton("Save");
        panelFoodEatenAndSave.add(saveButton, BorderLayout.NORTH);
        setupButtonActionSave();
    }

    // REQUIRES: user clicks on save button
    // EFFECTS: calls save method and shows message that meal plan is saved
    private void setupButtonActionSave() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMealPlanToFile();
                JOptionPane.showMessageDialog(panelStepTwo, "Meal plan saved", "Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // EFFECTS: saves meal plan to file
    private void saveMealPlanToFile() {
        try {
            if (name.getText() == null) {
                mealPlan.setName(" ");
                if (proteinGoal.getText() == null) {
                    mealPlan.setProteinGoal(0);
                }
            } else {
                mealPlan.setName(name.getText());
                if (proteinGoal.getText() == null) {
                    mealPlan.setProteinGoal(0);
                } else {
                    try {
                        mealPlan.setProteinGoal(Integer.parseInt(proteinGoal.getText()));
                    } catch (NumberFormatException e) {
                        mealPlan.setProteinGoal(0);
                    }
                }
            }
            ProteinCounterApp proteinApp = new ProteinCounterApp(this.mealPlan);
            proteinApp.saveMealPlan();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: displays image on frame 1
    private void displayImage() {
        try {
            String imagePath = "./ProteinTracker.jpg";
            ImageIcon image = new ImageIcon(imagePath);
            JLabel imageLabel = new JLabel(image);

            panelStepOne.add(imageLabel, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // SOURCE: FROM ORACLE'S WINDOW TUTORIAL
    @Override
    public void windowStateChanged(WindowEvent e) {
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Iterator<Event> events = EventLog.getInstance().iterator();
        while (events.hasNext()) {
            Event event = events.next();
            System.out.println(event);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
