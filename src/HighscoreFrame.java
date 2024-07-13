/* HIGHSCORE FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file displays the top 5 scores.
 * 
 * MAJOR SKILLS: Methods, if statements,
 * for loops, styling GUI JFrame
 * 
 * 
 * ADDED FEATURES: Display leaderboard (advanced)
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
public class HighscoreFrame extends JFrame implements ActionListener {
	// Array to store highest scores
	static Leaderboard[] topScores;
	
	// Create instances of HighscoreFrame
	public HighscoreFrame() {
		// Create JLabel with background image
		JLabel backgroundImage = new JLabel(new ImageIcon("images/leaderboardBackground.png"));
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

		
		// Evoke highscore evaluation method
		getTop();
		
		// Use for loop to displayeach element
		// of topScores Leaderboard[] array
		for (int i = 0; i < 5; i++) {
			// Display player name
			JLabel topName = new JLabel(topScores[i].getName());
			topName.setBounds(300,183 + 59 * i,400,30);
			topName.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  20));
			backgroundImage.add(topName);
			
			// Display high score and level
			// get score
			String topScoreConvert = Integer.toString(topScores[i].getLifetimeScore()); // convert int to string
			// get level
			String levelConvert = Integer.toString(topScores[i].getPlayerLevel()); // convert int to string
			JLabel topScore = new JLabel(topScoreConvert + " L" + levelConvert);
			topScore.setBounds(680,183 + 59 * i,400,30);
			topScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  20));
			backgroundImage.add(topScore);
		}
		
		// FRAME STYLINGSs
		setTitle("Leaderboard"); // sets title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,560);
		setLayout(null);
		setResizable(false);
		setVisible(true);
	}
	
	// Determine top 5 scores
	public static void getTop() {
		// Create topScores leaderboard
		topScores = new Leaderboard[5];
		// Input fake values into leaderboard
		for (int i = 0; i < 5; i++) {
			topScores[i] = new Leaderboard("This could be you!", 0, 0);
		}
		
		// Read text file with all updated player info
		MenuFrame.readNames();
		// Sort array by highest scores in ascending order
		Arrays.sort(MenuFrame.leaderboardArray, Comparator.comparing(Leaderboard::getLifetimeScore));
						
		// For loop that assigns one Leaderboard object to 
		// each element of Leaderboard[] arrays
		for (int i = 0; i < 5; i++) {
			// Start inputting elements from highest scores to lowest scores
			for (int k = MenuFrame.leaderboardArray.length-1; k > -1; k--) {
				// Check if current score being read is greater than any of the scores
				// currently in the high score leaderboard
				if (MenuFrame.leaderboardArray[k].getLifetimeScore() >= topScores[i].getLifetimeScore()) {
					for (int test = 0; test < 5; test++) {
						// Check for duplicates of a player's name in leaderboard
						if (topScores[test].getName().equalsIgnoreCase(MenuFrame.leaderboardArray[k].getName())) {
							// Exit loop if there are duplicates
							break;
						}
						// If there are no duplicates
						else if (test == 4) {
							// Add new highscore to array
							topScores[i] =  MenuFrame.leaderboardArray[k];
							break;
						}
					}

				}
			}
		}

	}

	// Required to run GUI frame
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
