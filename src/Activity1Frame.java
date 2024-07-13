/* CULMINATING - OBJECTS & CLASSES
 * ACTIVITY 1 FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates the activity
 * for module #1. It teaches users about the differences
 * between states and behaviours of an object.
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
public class Activity1Frame extends JFrame implements ActionListener {
	// Create an array of labels
	JLabel[] fieldLabel = new JLabel[5];
	JLabel[] behaviourLabel = new JLabel[5];
	
	Activity1Frame() {
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
			new ModuleFrame(0);
		});
		// Add to panel
		mainPanel.add(backButton);
		
		// Add header
		JLabel header = new JLabel(new ImageIcon("images/header1.png"));
		header.setBorder(BorderFactory.createEmptyBorder(40, 50, 30, 0));
		header.setAlignmentX(LEFT_ALIGNMENT);
		
		// Create two-column table
		JPanel table = new JPanel();
		table.setBackground(Color.WHITE);
		table.setAlignmentX(LEFT_ALIGNMENT);
		table.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 0));
		table.setLayout(new BoxLayout(table, BoxLayout.X_AXIS));
		
		// Create left column
		JPanel leftCol = new JPanel();
		leftCol.setBackground(Color.WHITE);
		leftCol.setAlignmentX(LEFT_ALIGNMENT);
		leftCol.setAlignmentY(TOP_ALIGNMENT);
		leftCol.setLayout(new BoxLayout(leftCol, BoxLayout.Y_AXIS));
		table.add(leftCol);
		// Add a subheader
		JLabel fieldSubheader = new JLabel("Exploring Fields");
		fieldSubheader.setFont(new Font("Monospaced", Font.BOLD, 16)); 
		fieldSubheader.setAlignmentX(LEFT_ALIGNMENT);
		fieldSubheader.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		leftCol.add(fieldSubheader);

		// Use a for loop to automate style
		for (int i = 0; i < 5; i++) {
			// Create blank JLabel
			fieldLabel[i] = new JLabel();
			fieldLabel[i].setFont(new Font("Monospaced", Font.PLAIN, 14)); 
			fieldLabel[i].setAlignmentX(LEFT_ALIGNMENT);
			// Set to be not visible
			fieldLabel[i].setVisible(false);
			// Add to col
			leftCol.add(fieldLabel[i]);
		}		
		
		// Set text of each label
		fieldLabel[0].setText("private boolean stylish = true;");
		fieldLabel[1].setText("private String emotion = \"angry\";");
		fieldLabel[2].setText("private boolean intelligent = true;");
		fieldLabel[3].setText("private boolean romantic = true;");
		fieldLabel[4].setText("private String colour = \"blue\";");
		
		// Create right column
		JPanel rightCol = new JPanel();
		rightCol.setBackground(Color.WHITE);
		rightCol.setAlignmentX(LEFT_ALIGNMENT);
		rightCol.setAlignmentY(TOP_ALIGNMENT);
		rightCol.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		rightCol.setLayout(new BoxLayout(rightCol, BoxLayout.Y_AXIS));
		table.add(rightCol);
		
		// Add a subheader
		JLabel behaviourSubheader = new JLabel("Exploring Behaviours");
		behaviourSubheader.setFont(new Font("Monospaced", Font.BOLD, 16)); 
		behaviourSubheader.setAlignmentX(LEFT_ALIGNMENT);
		behaviourSubheader.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		rightCol.add(behaviourSubheader);
		
		// Use a for loop to automate style
		for (int i = 0; i < 5; i++) {
			// Create blank JLabel
			behaviourLabel[i] = new JLabel();
			behaviourLabel[i].setFont(new Font("Monospaced", Font.PLAIN, 14)); 
			behaviourLabel[i].setAlignmentX(LEFT_ALIGNMENT);
			// Set to be not visible
			behaviourLabel[i].setVisible(false);
			// Add to col
			rightCol.add(behaviourLabel[i]);
		}		
		
		// Set text of each label
		behaviourLabel[0].setText("canFly();");
		behaviourLabel[1].setText("enjoyingMusic();");
		behaviourLabel[2].setText("canPaint();");
		behaviourLabel[3].setText("canLift();");
		behaviourLabel[4].setText("partying();");
		
		// Add subheader 1
		JLabel subheader1 = new JLabel("Select your fields:");
		subheader1.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 0));
		subheader1.setFont(new Font("Dialog", Font.PLAIN, 16));
		subheader1.setAlignmentX(LEFT_ALIGNMENT);

		// Create field button row
		JPanel fieldRow = new JPanel();
		fieldRow.setBackground(Color.WHITE);
		fieldRow.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 0));
		fieldRow.setLayout(new BoxLayout(fieldRow, BoxLayout.X_AXIS));// stack rows vertically
		fieldRow.setAlignmentX(LEFT_ALIGNMENT);

		// Create array of JButtons for field buttons
		JButton[] field = new JButton[5];
		
		// Use loop to automate styles
		for (int i = 0; i < 5; i++) {
			// Set button image
			field[i] = new JButton(new ImageIcon("images/field" + (i+1) + ".png"));
			// Add margins
			field[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
			// Add button to panel
			fieldRow.add(field[i]);
		}

		// Add function to each button
		// Button 1
		field[0].addActionListener(e -> {
			// Set label visible
			fieldLabel[0].setVisible(true);
		});
		// Button 2
		field[1].addActionListener(e -> {
			// Set label visible
			fieldLabel[1].setVisible(true);
		});
		// Button 3
		field[2].addActionListener(e -> {
			// Set label visible
			fieldLabel[2].setVisible(true);
		});
		// Button 4
		field[3].addActionListener(e -> {
			// Set label visible
			fieldLabel[3].setVisible(true);
		});
		// Button 5
		field[4].addActionListener(e -> {
			// Set label visible
			fieldLabel[4].setVisible(true);
		});
		
		// Add subheader 2
		JLabel subheader2 = new JLabel("Select your behaviours:");
		subheader2.setAlignmentX(LEFT_ALIGNMENT);
		subheader2.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		subheader2.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		// Create behaviour button row
		JPanel behaviourRow = new JPanel();
		behaviourRow.setBackground(Color.WHITE);
		behaviourRow.setAlignmentX(LEFT_ALIGNMENT);
		behaviourRow.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 0));
		behaviourRow.setLayout(new BoxLayout(behaviourRow, BoxLayout.X_AXIS));
		
		// Create array of JButtons for behaviour buttons
		JButton[] behaviour = new JButton[5];
		
		// Use loop to automate styles
		for (int i = 0; i < 5; i++) {
			// Set button image
			behaviour[i] = new JButton(new ImageIcon("images/behaviour" + (i+1) + ".png"));
			// Add margins
			behaviour[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

			// Add button to panel
			behaviourRow.add(behaviour[i]);
		}
		
		// Add function to each button
		// Button 1
		behaviour[0].addActionListener(e -> {
			// Set label visible
			behaviourLabel[0].setVisible(true);
		});
		// Button 2
		behaviour[1].addActionListener(e -> {
			// Set label visible
			behaviourLabel[1].setVisible(true);
		});
		// Button 3
		behaviour[2].addActionListener(e -> {
			// Set label visible
			behaviourLabel[2].setVisible(true);
		});
		// Button 4
		behaviour[3].addActionListener(e -> {
			// Set label visible
			behaviourLabel[3].setVisible(true);
		});
		// Button 5
		behaviour[4].addActionListener(e -> {
			// Set label visible
			behaviourLabel[4].setVisible(true);
		});
		
		// Add a next button
		JButton nextModule = new JButton(new ImageIcon("images/nextModule.png"));
		nextModule.setBorder(BorderFactory.createEmptyBorder(7, 50, 15, 0));
		// Add function to button
		nextModule.addActionListener(e -> {
			// Close current frame
			dispose();
			// Update current level
			LoginFrame.currentLevel++;
			// Update text file with new level
			LoginFrame.appendUsingBufferedWriter("data/login.txt", LoginFrame.getName + "," + LoginFrame.getPass + ",2", 1);
			// Open next module
			new ModuleFrame(1);
		});
		
		// Add widgets
		mainPanel.add(header);
		mainPanel.add(subheader1);
		mainPanel.add(fieldRow);
		mainPanel.add(subheader2);
		mainPanel.add(behaviourRow);
		mainPanel.add(table);
		mainPanel.add(nextModule);
		
		// Add main panel
		add(mainPanel);

		// FRAME CREATION
		setTitle("Module 1 Activity"); // sets title
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
