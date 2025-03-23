package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;

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
import javax.swing.border.Border;

import org.json.JSONWriter;

import model.Food;
import model.FoodWithQty;
import model.InvalidDoubleException;
import model.MealPlan;

// Represents a GUI
public class ProteinCounterGUI {
    // frame one
    private JFrame frameStepOne;
    private JButton btnCreateNewPlan;
    private JButton btnLoadFile;

    // frame two:
    private JFrame frameStepTwo;
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
    // private JPanel panelFood;
    private JPanel panelFoodToAdd;
    private JPanel panelFoodEatenAndSave;
    private MealPlan mealPlan;
    private JSONWriter jsonWriter;

    public ProteinCounterGUI() {
        setupFrameOne();
        setupFrameTwo();
        frameStepOne.setVisible(true);
    }

    public void setupFrameOne() {
        frameStepOne = new JFrame("Meal Plan Application");
        frameStepOne.setSize(900, 600);
        frameStepOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameStepOne.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        btnCreateNewPlan = new JButton("Create new meal plan");
        btnLoadFile = new JButton("Load file");
        buttonPanel.add(btnCreateNewPlan);
        buttonPanel.add(btnLoadFile);

        displayImage();
        JPanel panelMenuOptions = new JPanel(new GridBagLayout());
        panelMenuOptions.add(buttonPanel);
        frameStepOne.add(panelMenuOptions, BorderLayout.CENTER);

        setupButtonActionCreateNewPlan();
        setupButtonActionLoadFile();
    }

    public void setupFrameTwo() {
        instantiateLists();
        frameStepTwo = new JFrame("Creating new meal plan");
        frameStepTwo.setSize(900, 600);
        frameStepTwo.setLayout(new BorderLayout());
        setupNameAndProteinPanel();
        setupFoodToAddPanel();
        frameStepTwo.add(quantityPanel, BorderLayout.SOUTH);

        setupFoodEatenAndSavePanel();

        setupButtonActions();

        // setupSaveButton();

        // REQUIRES:
        // MODIFIES:
        // EFFECTS:

        // btnShowAllFoods.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // displayFoodOptions.setModel(storeFoodOptions);
        // }
        // });
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void instantiateLists() {
        // add food options list in centre of panel 2
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupButtonActionCreateNewPlan() {
        btnCreateNewPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mealPlan = new MealPlan("", 0);
                frameStepOne.setVisible(false);
                setupFrameTwo();
                frameStepTwo.setVisible(true);
            }
        });
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupNameAndProteinPanel() {
        panelNameAndGoal = new JPanel(new GridLayout(2, 2));
        panelNameAndGoal.setBackground(Color.PINK);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(10, 20, 80, 25);
        panelNameAndGoal.add(labelName);

        name = new JTextField(20);
        name.setBounds(100, 20, 165, 25);
        panelNameAndGoal.add(name);

        JLabel labelProteinGoal = new JLabel("Protein Goal");
        labelProteinGoal.setBounds(10, 20, 80, 25);
        panelNameAndGoal.add(labelProteinGoal);

        proteinGoal = new JTextField(20);
        proteinGoal.setBounds(100, 20, 165, 25);
        panelNameAndGoal.add(proteinGoal);

        frameStepTwo.add(panelNameAndGoal, BorderLayout.NORTH);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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

        frameStepTwo.add(panelFoodToAdd, BorderLayout.WEST);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupFoodEatenAndSavePanel() {
        panelFoodEatenAndSave = new JPanel(new BorderLayout());
        panelFoodEatenAndSave.setBackground(Color.MAGENTA);
        panelFoodEatenAndSave.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JLabel foodEatenLabel = new JLabel("Food eaten", JLabel.CENTER);
        foodEatenLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panelFoodEatenAndSave.add(foodEatenLabel, BorderLayout.NORTH);

        lstFoodEaten = new JList<>(storeFoodEaten);
        frameStepTwo.add(panelFoodEatenAndSave, BorderLayout.EAST);
        panelFoodEatenAndSave.add(lstFoodEaten, BorderLayout.CENTER);
    }

    private void setupButtonActions() {
        setupButtonActionForShowAllFoods();
        setupButtonActionForHighProteinFoods();
        setupButtonActionAddFood();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupButtonActionForShowAllFoods() {
        btnShowAllFoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lstFoodOptions.setModel(storeFoodOptions);
            }
        });
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupButtonActionAddFood() {
        btnAddFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddFood();
            }
        });
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupButtonActionLoadFile() {
        btnLoadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // load file
            }
        });
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void handleAddFood() {
        double quantity = 0;
        try {
            System.out.println(quantityEaten.getText());
            quantity = handleGrams(quantityEaten.getText());
        } catch (InvalidDoubleException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        if (lstFoodOptions.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(frameStepTwo, "You must select a food item", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Food selectedFood = lstFoodOptions.getSelectedValue();
        storeFoodEaten.addElement(new FoodWithQty(selectedFood, quantity));
        mealPlan.addFood(selectedFood, quantity);
        JOptionPane.showMessageDialog(frameStepTwo, "Food successfully added", "Food added",
                JOptionPane.INFORMATION_MESSAGE);

        // each time we add a food, display in food eaten list
        quantityEaten.setText("");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private double handleGrams(String quantity) throws InvalidDoubleException {
        if (quantity == null) {
            JOptionPane.showMessageDialog(frameStepTwo, "Please enter a valid number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDoubleException();
        }

        double grams;
        try {
            grams = Double.parseDouble(quantity);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frameStepTwo, "Please enter a valid number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDoubleException();
        }

        if (grams <= 0) {
            JOptionPane.showMessageDialog(frameStepTwo, "Enter a quantity higher than 0 grams", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDoubleException();
        }
        return grams;
    }

    private void setupSaveButton() {
        saveButton = new JButton("Save");
        panelFoodEatenAndSave.add(saveButton, BorderLayout.NORTH);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setupButtonActionSave() {
        btnShowAllFoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saveMealPlanToFile();
            }
        });
    }

    // private void saveMealPlanToFile() {
    //     try {
    //         jsonWriter.open();
    //         jsonWriter.write(mealPlan);
    //         jsonWriter.close(); // Close the writer
    //         JOptionPane.showMessageDialog(null, "Meal plan saved successfully");
    //     } catch (FileNotFoundException e) {
    //         JOptionPane.showMessageDialog(null, "Unable to save meal plan.");
    //     }
    // }

    private void displayImage() {
        try {
            String imagePath = "./ProteinTracker.jpg";
            // JsonReader reader = new JsonReader("./data/testReaderEmptyMealPlan.json");
            ImageIcon image = new ImageIcon(imagePath);
            JLabel imageLabel = new JLabel(image);

            frameStepOne.add(imageLabel, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
