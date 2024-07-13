/* CHOOSE CHARACTER FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file where users can select
 * which character they would like to be.
 * 
 * MAJOR SKILLS: Styling GUI JFrame and widgets
 * 
 * ADDED FEATURES: Select character (basic)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENTS
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Public class that creates GUI JFrame
public class ChooseCharacterFrame extends JFrame implements ActionListener {
	// Create new level frame
	public static LevelFrame levelFrame;
	// Store which character user picks
	public static int playerChoice;
	// Create buttons to select players
	public static JButton chooseCute;
	public static JButton chooseFun;
	public static JButton chooseSilly;
	
	public ChooseCharacterFrame() {
		// Create JLabel with background image
		JLabel backgroundImage = new JLabel(new ImageIcon("images/characterBackground.png"));
		// Set size
		backgroundImage.setBounds(0,0,1024,560);
		
		// BUTTONS
		// Set image of button
		chooseCute = new JButton(new ImageIcon("images/cuteButtonImg.png"));
		chooseCute.setBounds(361,241,88,127);
		// Select this character when clicked
		chooseCute.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Update user input
			playerChoice = 1;
			this.dispose();
			// Reset character icon previously chosen
			Icons.resetChar(playerChoice);
			// Load game
			levelFrame = new LevelFrame(MenuFrame.currentLevel);
			// Reset score
			Character.score = 0;
		});

		// Set image of button
		chooseFun = new JButton(new ImageIcon("images/funButtonImg.png"));
		chooseFun.setBounds(468,241,88,127);
		// Select this character when clicked
		chooseFun.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Update user input
			playerChoice = 2;
			this.dispose();
			// Reset character icon previously chosen
			Icons.resetChar(playerChoice);
			// Load game
			levelFrame = new LevelFrame(MenuFrame.currentLevel);
			// Reset score
			Character.score = 0;
		});
		
		// Set image of button
		chooseSilly = new JButton(new ImageIcon("images/sillyButtonImg.png"));
		chooseSilly.setBounds(574,241,88,127);
		// Select this character when clicked
		chooseSilly.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Update user input
			playerChoice = 3;
			this.dispose();
			// Reset character icon previously chosen
			Icons.resetChar(playerChoice);
			// Load game
			levelFrame = new LevelFrame(MenuFrame.currentLevel);
			// Reset score
			Character.score = 0;

		});
		
		// Add widgets to main frame
		add(backgroundImage);
		backgroundImage.add(chooseCute);
		backgroundImage.add(chooseFun);
		backgroundImage.add(chooseSilly);
		
		// FRAME STYLINGES
		setTitle("Choose Your Player"); // sets title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,560);
		setLayout(null);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
