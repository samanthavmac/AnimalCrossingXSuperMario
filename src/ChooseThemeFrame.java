/* CHOOSE THEME FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file where users can select
 * which theme they would like to play in.
 * 
 * MAJOR SKILLS: Styling GUI JFrame and widgets
 * 
 * ADDED FEATURES: Select theme (basic)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENT
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Public class that creates GUI JFrame
public class ChooseThemeFrame extends JFrame implements ActionListener {
	// Store which character user picks
	public static int themeChoice;
	// Create buttons to select players
	public static JButton choose1;
	public static JButton choose2;
	public static JButton choose3;
	
	// Create new instance of class
	public ChooseThemeFrame() {
		// Create JLabel with background image
		JLabel backgroundImage = new JLabel(new ImageIcon("images/themeBackground.png"));
		// Set size
		backgroundImage.setBounds(0,0,1024,560);
		
		// BUTTONS
		// Set image of button
		choose1 = new JButton(new ImageIcon("images/theme1Button.png"));
		choose1.setBounds(477,175,145,255);
		// Select this character when clicked
		choose1.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Update character input
			themeChoice = 1;
			this.dispose(); // close frame
			// Have user choose new character
			new ChooseCharacterFrame();
		});

		// Set image of button
		choose2 = new JButton(new ImageIcon("images/theme2Button.png"));
		choose2.setBounds(640,175,145,255);
		// Select this character when clicked
		choose2.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Update character input
			themeChoice = 2;
			this.dispose();
			// Have user choose new character
			new ChooseCharacterFrame();
		});
		
		// Set image of button
		choose3 = new JButton(new ImageIcon("images/theme3Button.png"));
		choose3.setBounds(803,175,145,255);
		// Select this character when clicked
		choose3.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Update character input
			themeChoice = 3;
			this.dispose();
			// Have user choose new character
			new ChooseCharacterFrame();

		});
		
		// Add widgets to main frame
		add(backgroundImage);
		backgroundImage.add(choose1);
		backgroundImage.add(choose2);
		backgroundImage.add(choose3);
		
		// FRAME STYLINGS
		setTitle("Choose Your Theme"); // sets title
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
