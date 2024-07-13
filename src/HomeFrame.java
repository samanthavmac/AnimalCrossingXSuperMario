/* CULMINATING - OBJECTS & CLASSES
 * HOME FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates a frame that
 * displays the home page. Users can view the status
 * of their modules, take the exam, and view the
 * leaderboard
 * 
 * MAJOR SKILLS: GUI widgets, for loops,
 * arrays of GUI widgets
 * 
 * ADDED FEATURES: Added a leaderboard.
 * 
 * AREAS OF CONCERN: None.
 */

// Import statements
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.*;

// Create GUI frame that displays list of modules
public class HomeFrame extends JFrame implements ActionListener {	
	// Testing boolean
	boolean unlocked = true;
	
	// Create integer to track current module #
	public static int i;
	
	// Create an array to track the top scores
	public static User[] leaderboardArray = new User[5];
	
	// BUTTONS
	// Create an array of buttons that will be
	// modified in a for loop
    JButton[] moduleButton = new JButton[4];
	
	HomeFrame() {
		// Create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// stack rows vertically
		
		// HEADER
		// Add banner image
		JLabel homeBanner = new JLabel(new ImageIcon("images/homeBanner.png"));
		
		// Add subheader
		JLabel subHeader = new JLabel("Your journey begins here, " + LoginFrame.getName);
		// Style text
		subHeader.setFont(new Font("Monospaced", Font.PLAIN, 20)); 
		subHeader.setBorder(BorderFactory.createEmptyBorder(50, 80, 0, 0 ));
		
		// Add widgets
		mainPanel.add(homeBanner);
		mainPanel.add(subHeader);

		// Use a for loop to style buttons
		for (i = 0; i < 4; i++) {
			// Use if-statements to determine which button image to display
			// 1. If level is completed
			if (i+1 < LoginFrame.currentLevel) {
				moduleButton[i] = new JButton(new ImageIcon("images/button" + (i+1) + "Complete.png"));
			}
			
			// 2. If level is unlocked
			else if (i+1 == LoginFrame.currentLevel) {
				moduleButton[i] = new JButton(new ImageIcon("images/button" + (i+1) + "Current.png"));
			}
			
			// 3. If level is locked
			else {
				moduleButton[i] = new JButton(new ImageIcon("images/button" + (i+1) + "Lock.png"));	
			}
			

			// Remove border and original button styles
			moduleButton[i].setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 0 ));
		}
		
		// Add unique function to each button
		moduleButton[0].addActionListener(e -> {
			// Play sound effect
			TestApplication.playMusic("sounds/click.wav");
			// Open module
			new ModuleFrame(0);
			// Close current frame
			dispose();
		});	
		
		// Only open module if level is unlocked
		if (1 < LoginFrame.currentLevel) {
			moduleButton[1].addActionListener(e -> {
				// Play sound effect
				TestApplication.playMusic("sounds/click.wav");
				// Open module
				new ModuleFrame(1);
				// Close current frame
				dispose();
			});	
		}
		
		if (2 < LoginFrame.currentLevel) {
			moduleButton[2].addActionListener(e -> {
				// Play sound effect
				TestApplication.playMusic("sounds/click.wav");
				// Open module
				new ModuleFrame(2);
				// Close current frame
				dispose();
			});	
		}
		
		if (3 < LoginFrame.currentLevel) {
			moduleButton[3].addActionListener(e -> {
				// Play sound effect
				TestApplication.playMusic("sounds/click.wav");
				// Open module
				new ModuleFrame(3);
				// Update level since no activity
				LoginFrame.currentLevel = 5;
				// Append new data
				LoginFrame.appendUsingBufferedWriter("data/login.txt", LoginFrame.getName + "," + LoginFrame.getPass + ",5", 1);
				dispose();
			});
		}
		
		// Add buttons to frame
		mainPanel.add(moduleButton[0]);
		mainPanel.add(moduleButton[1]);
		mainPanel.add(moduleButton[2]);
		mainPanel.add(moduleButton[3]);

		
		// Create button to skip to final exam
		JButton examButton = new JButton(new ImageIcon("images/finalExamButton.png"));
		examButton.setBorder(BorderFactory.createEmptyBorder(50, 80, 70, 0 ));
		// Add function to button
		examButton.addActionListener(e -> {
			// Play sound effect
			TestApplication.playMusic("sounds/click.wav");
			// OOpen excam
			new ExamFrame();
			// Close current frame
			dispose();
		});	
		// Add button to panel
		mainPanel.add(examButton);
		
		// Create panel to display the leaderboard
		JPanel leaderboardPanel = new JPanel();
		leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
		leaderboardPanel.setBackground(Color.WHITE);
		leaderboardPanel.setBorder(BorderFactory.createEmptyBorder(0, 80, 80, 0 ));
		mainPanel.add(leaderboardPanel);
		
		// Add header
		JLabel leaderboardHeader = new JLabel("Compete with your Friends");
		leaderboardHeader.setFont(new Font("Monospaced", Font.BOLD, 24)); 
		leaderboardHeader.setAlignmentX(LEFT_ALIGNMENT);
		leaderboardPanel.add(leaderboardHeader);
		
		
		// Invoke method to get top players
		getTop();
		
		// Use for loop to display the top
		// 5 scores
		for (int i = 0; i < 5; i++) {
			// Create a new panel hold each row
			JPanel userRow = new JPanel();
			userRow.setLayout(new BoxLayout(userRow, BoxLayout.X_AXIS));
			userRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0 ));
			userRow.setBackground(Color.WHITE);
			userRow.setAlignmentX(LEFT_ALIGNMENT);
			leaderboardPanel.add(userRow);
			
			// Display leage badge
			JLabel badge = new JLabel(new ImageIcon("images/league" + leaderboardArray[i].getLevel() + ".png"));
			
			// Display current score/level
			JLabel topScore = new JLabel(Integer.toString(leaderboardArray[i].getLevel() * 100) + " pts");
			topScore.setFont(new Font("Monospaced", Font.BOLD, 18)); 
			topScore.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20 ));
			
			// Display player name
			JLabel topName = new JLabel(leaderboardArray[i].getName());
			topName.setFont(new Font("Monospaced", Font.PLAIN, 18)); 
			
			// Check if the name is the current users name
			if (LoginFrame.getName.equalsIgnoreCase(leaderboardArray[i].getName())) {
				// Set text to "You"
				topName.setText("You");
			}
			
			// Check if user completed all levels
			if (leaderboardArray[i].getLevel() > 4) {
				// Change score and badge
				badge = new JLabel(new ImageIcon("images/league4.png"));
				topScore.setText("400 pts");
			}
			
			// Add widgets
			userRow.add(badge);
			userRow.add(topScore);
			userRow.add(topName);
		}
		
		// SCROLL PANE
		// Adds scroll bar
		JScrollPane scrPane = new JScrollPane(mainPanel);

		// Add widgets to frame
		add(scrPane);
		
		// FRAME CREATION
		setTitle("SamOverflow"); // sets title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes app
		setSize(800, 1600); //sets the x-dimension and y-dimension of the this
		setVisible(true); 
		setResizable(false);
	}

	
	// Determine top 5 scores
	public static void getTop() {
		// Input fake values into leaderboard
		for (int i = 0; i < 5; i++) {
			leaderboardArray[i] = new User("", "", 0);
		}
		
		// Read text file with all updated player info
		LoginFrame.readNames();
		// Sort array by highest scores in ascending order
		Arrays.sort(LoginFrame.userArray, Comparator.comparing(User::getLevel));
						
		// For loop that assigns one User object to 
		// each element of User[] arrays
		for (int i = 0; i < 5; i++) {
			// Start inputting elements from highest scores to lowest scores
			for (int k = LoginFrame.userArray.length-1; k > -1; k--) {
				// Check if current score being read is greater than any of the scores
				// currently in the high score leaderboard
				if (LoginFrame.userArray[k].getLevel() >= leaderboardArray[i].getLevel()) {
					for (int test = 0; test < 5; test++) {
						// Check for duplicates of a player's name in leaderboard
						if (leaderboardArray[test].getName().equalsIgnoreCase(LoginFrame.userArray[k].getName())) {
							// Exit loop if there are duplicates
							break;
						}
						// If there are no duplicates
						else if (test == 4) {
							// Add new highscore to array
							leaderboardArray[i] =  LoginFrame.userArray[k];
							break;
						}
					}

				}
			}
		}

	}
	
	// Allows GUI frame to run
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
