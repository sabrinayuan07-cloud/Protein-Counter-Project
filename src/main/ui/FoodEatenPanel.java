package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

// Represents a panel in which all the food eaten is displayed
public class FoodEatenPanel extends JPanel {
    private JLabel foodEatenLabel;


    // Constructs a food eaten panel
	// effects: sets the background colour and draws the initial labels;
	//          updates this each time a food is added
    public FoodEatenPanel() {
        setBackground(new Color(180, 180, 180));
        foodEatenLabel = new JLabel("Here's all the food you have eaten so far: ");

    }

    // viewProgressButton.addActionListener(new ActionListener()) {
    //     public void actionPerformed(ActionEvent e) {
    //         showProteinProgressPage();
    //     }
    // }

    // Updates the food eaten panel
	// modifies: this
	// effects:  updates food eaten panel
	public void update() {
        
	}


}
