/* CULMINATING - OBJECTS & CLASSES
 * MODULE FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file displays the
 * module for each lesson (4 in total). Modules
 * 1 to 3 have a matching activity.
 * 
 * MAJOR SKILLS: For loop, GUI widgets,
 * if-else statements
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

// Create GUI frame that displays list of modules
public class ModuleFrame extends JFrame implements ActionListener {	
	// Button leading to activity
	JButton activity;
	
	ModuleFrame(int num) {
		// Create main panel
		// Add box layout
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// stack rows vertically
		
		// SCROLL PANE
		// Adds scroll bar
		JScrollPane scrPane = new JScrollPane(mainPanel);
		
		// Add a back button
		JButton backButton = new JButton(new ImageIcon("images/backButton.png"));
		backButton.setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 0));
		// Add function to button
		backButton.addActionListener(e -> {
			// close frame
			dispose();
			// Open home frame
			new HomeFrame();
		});
		// Add to panel
		mainPanel.add(backButton);

		// Add module image
		JLabel module = new JLabel(new ImageIcon("images/lesson" + Integer.toString(num+1) + ".png"));
		// Add to scrPane
		mainPanel.add(module);
		
		// Add button to activity
		if (num != 3) {
			activity = new JButton(new ImageIcon("images/activityButton.png"));
		}
		// If last module, go to exam
		else {
			activity = new JButton(new ImageIcon("images/examButton.png"));
		}
		// Add margins
		activity.setBorder(BorderFactory.createEmptyBorder(10, 80, 30, 0));
		// Add button function
		activity.addActionListener(e -> {
			// Open correct activity frame
			// If on module 1
			if (num == 0) {
				new Activity1Frame();
			}
			// If on module 2
			else if (num == 1) {
				new Activity2Frame();
			}
			// If on module 3
			else if (num == 2) {
				new Activity3Frame();
			}
			// If on module 4
			else {
				new ExamFrame();
			}
			// Exit current frame
			this.dispose();
		});
		// Add to frame
		mainPanel.add(activity);		

		// Add widgets to frame
		add(scrPane);
		
		// FRAME CREATION
		setTitle("Module " + (num + 1)); // sets title
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
