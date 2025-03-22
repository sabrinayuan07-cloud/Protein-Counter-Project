package ui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Represents a GUI
public class ProteinCounterGUI {
    // frame one
    private JFrame frameStepOne;
    private JButton btnCreateNewPlan;
    private JButton btnLoadFile;

    // frame two:
    private JFrame frameStepTwo;
    private JTextField name, proteinGoal, quantityEaten;
    private JButton btnShowAllFoods, btnShowHighProteinFoods, btnAddFood;
    private JList<String> displayFoodOptions, displayFoodEaten;
    private DefaultListModel<String> storeFoodOptions, storeFoodEaten;

    private JPanel panelOne, panelTwo, panelThree;

    // might not use:
    private ProteinCounterApp proteinCounterApp;

    public ProteinCounterGUI() {
        setupFrameOne();
        setupFrameTwo();
        frameStepOne.setVisible(true); 
    }

    public void setupFrameOne() {
        frameStepOne = new JFrame("Meal Plan Application");
        frameStepOne.setSize(400, 300);
        frameStepOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameStepOne.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        btnCreateNewPlan = new JButton("Create new meal plan");
        btnLoadFile = new JButton("Load file");
        buttonPanel.add(btnCreateNewPlan);
        buttonPanel.add(btnLoadFile);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(buttonPanel);

        frameStepOne.add(centerPanel, BorderLayout.CENTER);

        // if user clicks create new meal plan button, frame 1 is gone and frame 2 is displayed
        btnCreateNewPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameStepOne.setVisible(false);
                frameStepTwo.setVisible(true);
                setupFrameTwo();
            }
        });

        // if user clicks load file:

        // btnLoadFile.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // load file
        //     }
        // })

    }

    public void setupFrameTwo() {
        frameStepTwo = new JFrame("Creating new meal plan");
        frameStepTwo.setSize(400, 300);
        frameStepTwo.setLayout(new BorderLayout());
        // frameStepTwo.add(panel);

        // PANEL ONE CONTAINS NAME + PROTEIN GOAL
        panelOne.setLayout(null);
        panelOne = new JPanel(new GridLayout(1, 4));

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(10, 20, 80, 25);
        panelOne.add(labelName);

        name = new JTextField(20);
        name.setBounds(100, 20, 165, 25);
        panelOne.add(name);

        JLabel labelProteinGoal = new JLabel("Name");
        labelProteinGoal.setBounds(10, 20, 80, 25);
        panelOne.add(labelProteinGoal);

        proteinGoal = new JTextField("Protein Goal");
        proteinGoal.setBounds(100, 20, 165, 25);
        panelOne.add(proteinGoal);

        frameStepTwo.add(panelOne, BorderLayout.NORTH);

        // PANEL TWO CONTAINS FOOD OPTIONS + ADD FOOD BUTTON
        panelTwo.setLayout(null);
        panelTwo = new JPanel(new BorderLayout());

        btnShowAllFoods = new JButton("Show all foods");
        btnShowHighProteinFoods = new JButton("Only show high protein foods");

        btnAddFood = new JButton("Add food selected");


    }
}
