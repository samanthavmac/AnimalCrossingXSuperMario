/* RULES FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file displays the rules of the game.
 * 
 * MAJOR SKILLS: Methods, if statements,
 * for loops, styling GUI JFrame
 * 
 * 
 * ADDED FEATURES: Homepage (basic)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENTS
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

// Public class that creates JFrame
public class RulesFrame extends JFrame implements ActionListener {	
	// Create instances of HighscoreFrame
	public RulesFrame() {
		// Create JLabel with background image
		JLabel backgroundImage = new JLabel(new ImageIcon("images/rulesBackground.png"));
		// Set size
		backgroundImage.setBounds(0,0,1024,560);		
		// Add background image to frame
		add(backgroundImage);
		
		// Display exit button
		JButton exitButton = new JButton(new ImageIcon("images/exitButton.png"));
		exitButton.setBounds(45, 33, 45, 45);
		// Button function
		exitButton.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			this.dispose();
			// return to menu
			new MenuFrame();
		});
		// Add button image to frame
		backgroundImage.add(exitButton);
		
		// FRAME STYLINGSs
		setTitle("Game Rules"); // sets title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,501);
		setLayout(null);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
