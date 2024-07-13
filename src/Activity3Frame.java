/* CULMINATING - OBJECTS & CLASSES
 * ACTIVITY 3 FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates the activity
 * for module #3. It teaches users about how to access
 * and modify the states of an object.
 * 
 * MAJOR SKILLS: GUI widgets, arrays of widgets,
 * for loops, if statements, appending to text file
 * 
 * ANSWERS:
 * 1. duo.isSunglasses(); or duo.getSunglasses();
 * 2. duo.setColour("yellow");
 * 3. duo.setFancy(true);
 * 
 * ADDED FEATURES: None.
 * 
 * AREAS OF CONCERN: None.
 */

// Import statements
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

//Create GUI frame that displays activity
public class Activity3Frame extends JFrame implements ActionListener {
	// Create array of JLabels
	JLabel[] duoState = new JLabel[4];
	
	Activity3Frame() {
		// Create main panel
		// Add box layout for vertical stack
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// stack rows vertically
		
		// Add a back button
		JButton backButton = new JButton(new ImageIcon("images/backButton.png"));
		backButton.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 0));
		// Add function to button
		backButton.addActionListener(e -> {
			// close frame
			dispose();
			// Open home frame
			new ModuleFrame(2);
		});
		// Add to panel
		mainPanel.add(backButton);
		
		// Add header
		JLabel header = new JLabel(new ImageIcon("images/header3.png"));
		header.setBorder(BorderFactory.createEmptyBorder(40, 50, 30, 0));
		header.setAlignmentX(LEFT_ALIGNMENT);
		
		// Create row of duo icons
		JPanel duoRow = new JPanel();
		duoRow.setLayout(new BoxLayout(duoRow, BoxLayout.X_AXIS));
		duoRow.setBorder(BorderFactory.createEmptyBorder(0, 50, 40, 0));
		duoRow.setBackground(Color.WHITE);
		duoRow.setAlignmentX(LEFT_ALIGNMENT);
		// Create for loop to generate images of duo
		for (int i = 0; i < 4; i++) {
			// Set image icon
			duoState[i] = new JLabel(new ImageIcon("images/state" + (i+1) + ".png"));
			// Add margin
			duoState[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			// Make hidden
			duoState[i].setVisible(false);
			// Add to row
			duoRow.add(duoState[i]);
		}
		
		// Create panel that prompts user and allows for input
		JPanel inputPanel = new JPanel();
		inputPanel.setAlignmentX(LEFT_ALIGNMENT);
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));// stack rows vertically
		
		// Show first Duo state
		duoState[0].setVisible(true);
		
		// Create panel with horizontal flow layout
		JPanel inputRow = new JPanel();
		inputRow.setAlignmentX(LEFT_ALIGNMENT);
		inputRow.setBackground(Color.WHITE);
		inputRow.setLayout(new FlowLayout());
		
		// Add label with instruction
		JLabel instruction = new JLabel("Invoke a getter method (in dot notation) that will access the sunglasses state of the Duo object:");
		instruction.setAlignmentX(LEFT_ALIGNMENT);
		instruction.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 0));
		// Create text field for user input
		JTextField inputAnswer = new JTextField(25);
		inputAnswer.setBorder(new LineBorder(new Color(87, 189, 75),1));
		inputAnswer.setAlignmentX(LEFT_ALIGNMENT);
		// Create a submit button
		JButton submit = new JButton(new ImageIcon("images/nextButton.png"));
		submit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// Add button function
		submit.addActionListener(e -> {
			// SOLUTION TO QUESTION 1
			// Get user input and if correct...
			if (inputAnswer.getText().equals("duo.isSunglasses();") || inputAnswer.getText().equals("duo.getSunglasses();")) {
				// Show next Duo state
				duoState[1].setVisible(true);
				// Ask next question
				instruction.setText("Invoke a setter method (in dot notation) that will turn Duo from \"green\" to \"yellow\":");
				// Reset text field
				inputAnswer.setText("");
				inputAnswer.setBorder(new LineBorder(new Color(87, 189, 75),1));
				
				// SOLUTION TO QUESTION 2
				// Add new function to button
				submit.addActionListener(f -> {
					// Get user input and if correct...
					if (inputAnswer.getText().equals("duo.setColour(\"yellow\");")) {
						// Show next Duo state
						duoState[2].setVisible(true);
						// Ask next question
						instruction.setText("Invoke a setter method (in dot notation) that will set the \"fancy\" field from false to true:");
						// Reset text field
						inputAnswer.setText("");
						inputAnswer.setBorder(new LineBorder(new Color(87, 189, 75),1));
						
						// SOLUTION TO QUESTION 3
						// Add new function to button
						submit.addActionListener(g -> {
							// Get user input and if correct...
							if (inputAnswer.getText().equals("duo.setFancy(true);")) {
								// Show next Duo state
								duoState[3].setVisible(true);
								// Hide elements
								instruction.setVisible(false);
								inputAnswer.setVisible(false);
								submit.setVisible(false);
							}
							// If answer is incorrect
							else {
								inputAnswer.setText("");
								inputAnswer.setBorder(new LineBorder(Color.red,1));
							}
						});
					}
					// If answer is incorrect
					else {
						inputAnswer.setText("");
						inputAnswer.setBorder(new LineBorder(Color.red,1));
					}
				});
			}
			// If answer is incorrect
			else {
				inputAnswer.setText("");
				inputAnswer.setBorder(new LineBorder(Color.red,1));
			}

		});
		
		// Add a next button
		JButton nextModule = new JButton(new ImageIcon("images/nextModule.png"));
		nextModule.setBorder(BorderFactory.createEmptyBorder(20, 50, 250, 0));
		nextModule.setAlignmentX(LEFT_ALIGNMENT);
		// Add function to button
		nextModule.addActionListener(e -> {
			// Close current frame
			dispose();
			// Update current level
			LoginFrame.currentLevel = 5;
			// Update text file with new level
			LoginFrame.appendUsingBufferedWriter("data/login.txt", LoginFrame.getName + "," + LoginFrame.getPass + ",5", 1);
			// Open next module
			new ModuleFrame(3);
		});
		
		// Add widgets
		inputPanel.add(instruction);
		inputPanel.add(inputRow);
		inputRow.add(inputAnswer);
		inputRow.add(submit);
		inputPanel.add(nextModule);

		// Add widgets to panel
		mainPanel.add(header);
		mainPanel.add(duoRow);
		mainPanel.add(inputPanel);
				
		// Add main panel
		add(mainPanel);

		// FRAME CREATION
		setTitle("Module 3 Activity"); // sets title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes app
		setSize(800, 700); //sets the x-dimension and y-dimension of the this
		setVisible(true); 
		setResizable(false);
		
	}
	
	// Allows GUI frame to run
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
