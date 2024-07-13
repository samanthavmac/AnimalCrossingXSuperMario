/* CULMINATING - OBJECTS & CLASSES
 * ACTIVITY 2 FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates the activity
 * for module #2. It teaches users about the syntax
 * for class constructors. Users fill in the blanks.
 * 
 * MAJOR SKILLS: GUI widgets, arrays of widgets,
 * for loops, if statements, appending to text file
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
public class Activity2Frame extends JFrame implements ActionListener {
	// Create an array of input fields
	public static JTextField[] inputAnswer = new JTextField[4];
	
	Activity2Frame() {
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
			new ModuleFrame(1);
		});
		// Add to panel
		mainPanel.add(backButton);
		
		// Add header
		JLabel header = new JLabel(new ImageIcon("images/header2.png"));
		header.setBorder(BorderFactory.createEmptyBorder(40, 50, 30, 0));
		header.setAlignmentX(LEFT_ALIGNMENT);
				
		// Add blank code
		JLabel blankCode = new JLabel(new ImageIcon("images/blankCode.png"));
		blankCode.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 0));
		blankCode.setAlignmentX(LEFT_ALIGNMENT);
		
		// Add answer key
		JLabel answerKey = new JLabel(new ImageIcon("images/answerKey.png"));
		answerKey.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 0));
		answerKey.setAlignmentX(LEFT_ALIGNMENT);
		// Hide answers
		answerKey.setVisible(false);
		
		// Create row of text field
		JPanel inputRow = new JPanel();
		inputRow.setLayout(new FlowLayout());
		inputRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		inputRow.setAlignmentX(LEFT_ALIGNMENT);
		inputRow.setBackground(Color.WHITE);
	
		// Use a for loop for styling
		for (int i = 0; i < 4; i++) {
			// Add number circle
			JLabel numCircle = new JLabel(new ImageIcon("images/circle" + (i+1) + ".png"));
			numCircle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 6));
			numCircle.setAlignmentX(LEFT_ALIGNMENT);
	
			// Add text input field
			inputAnswer[i] = new JTextField(8);
			inputAnswer[i].setBorder(new LineBorder(Color.GRAY,1));
			inputAnswer[i].setAlignmentX(LEFT_ALIGNMENT);
	
			// Add widgets
			inputRow.add(numCircle);
			inputRow.add(inputAnswer[i]);
		}
		
		// Add a submit button
		JButton submit = new JButton(new ImageIcon("images/submitButton.png"));
		submit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		submit.setAlignmentX(LEFT_ALIGNMENT);
		inputRow.add(submit);
		// Add function to button
		submit.addActionListener(e -> {
			// Check which answers are correct
			// Blank 1
			if (inputAnswer[0].getText().equals("DuoClass")) {
				// Change border to green
				inputAnswer[0].setBorder(new LineBorder(new Color(87, 189, 75),1));
			}
			else {
				// Change border to red
				inputAnswer[0].setBorder(new LineBorder(Color.RED,1));
			}
	
			// Blank 2
			if (inputAnswer[1].getText().equals("name")) {
				// Change border to green
				inputAnswer[1].setBorder(new LineBorder(new Color(87, 189, 75),1));
			}
			else {
				// Change border to red
				inputAnswer[1].setBorder(new LineBorder(Color.RED,1));
			}
			
			// Blank 3
			if (inputAnswer[2].getText().equals("boolean")) {
				// Change border to green
				inputAnswer[2].setBorder(new LineBorder(new Color(87, 189, 75),1));
			}
			else {
				// Change border to red
				inputAnswer[2].setBorder(new LineBorder(Color.RED,1));
			}
			
			// Blank 4
			if (inputAnswer[3].getText().equals("toString")) {
				// Change border to green
				inputAnswer[3].setBorder(new LineBorder(new Color(87, 189, 75),1));
			}
			else {
				// Change border to red
				inputAnswer[3].setBorder(new LineBorder(Color.RED,1));
			}
			
			// Hide button
			submit.setVisible(false);
			//Hide blank code
			blankCode.setVisible(false);
			// Show answer key
			answerKey.setVisible(true);
			
		});
		
		// Add a next button
		JButton nextModule = new JButton(new ImageIcon("images/nextModule.png"));
		nextModule.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 0));
		// Add function to button
		nextModule.addActionListener(e -> {
			// Close current frame
			dispose();
			// Update current level
			LoginFrame.currentLevel++;
			// Update text file with new level
			LoginFrame.appendUsingBufferedWriter("data/login.txt", LoginFrame.getName + "," + LoginFrame.getPass + ",3", 1);
			// Open next module
			new ModuleFrame(2);
		});
		
		// Add widgets to main panel
		mainPanel.add(header);
		mainPanel.add(blankCode);
		mainPanel.add(answerKey);
		mainPanel.add(inputRow);
		mainPanel.add(nextModule);
		
		// SCROLL PANE
		// Adds scroll bar
		JScrollPane scrPane = new JScrollPane(mainPanel);
	
		// Add mainpanel to frame
		add(scrPane);
	
		// FRAME CREATION
		setTitle("Module 2 Activity"); // sets title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes app
		setSize(800, 1600); //sets the x-dimension and y-dimension of the this
		setVisible(true); 
		setResizable(false);
	
	}
	
	// Allows GUI frame to run
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}