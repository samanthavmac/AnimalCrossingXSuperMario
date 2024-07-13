/* LEVEL FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates the game map
 * based on the format indicated in corresponding
 * text files.
 * 
 * MAJOR SKILLS: Methods, if statements,
 * reading text files, styling GUI JFrames,
 * reading keystrokes
 * 
 * 
 * ADDED FEATURES: Different levels (basic)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENTS
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;

// Public class that creates GUI JFrame
public class LevelFrame extends JFrame implements KeyListener {
	// This stores number of total points needed for next level
	public static int goal = 0;
	// Map of level 1 that matches dimensions of Level1.txt file
	public static JLabel[][] boardArray = new JLabel[20][25];
	// Create panel
	public static JLabel lvlPanel;
	// Create panel that holds buttons for menu bar
	public static JPanel menuBar;
	// Create button to restart game
	public static JButton restartGame;
	// Create button to exit current game
	public static JButton exitGame;
	// Create button to disable music
	public static JButton mute;
	// Create label that displays score
	public static JLabel displayScore;
	// Pass image from Icon class and keys used to move him
	public static Character player;
	
	// Create constructor that requires parameter
	// indicating the level
	public LevelFrame(int level) {
		setSize(25*boardArray[0].length + 15 , 25*boardArray.length + 35);
		// length of row (horizontal)	// length of columns (vertical)
		setLayout(null);
		setResizable(false);
		
		// Reset character score with every new level
		Character.score = 0;	
		// Load level map based on curretn level
		loadLevel(level);
		// Create panel and insert specific icons
		createLvlPanel();
		
		// Read keyboard input from user
		setupKeyBinders();
		lvlPanel.addKeyListener(this);
		
		// FRAME STYLINGS
		setVisible(true);
		this.setTitle("Level " + level); // sets title
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//

	}
	
	// Read level input and load correct map
	private void loadLevel(int level) {
		// Create Scanner object to get the file
		try {
			// use scanner to analyze text file
			Scanner inputFile = new Scanner(new File("data/level" + level + ".txt"));
			for (int row = 0; row < boardArray.length; row++) {
				// Read text file with game map
				// Input corresponding terrain blocks based on character
				char[] lineArray = inputFile.next().toCharArray();
				// Read each character in text files
				for (int col = 0; col < lineArray.length; col++) {
					if (lineArray[col] == 'B') {
						boardArray[row][col] = new JLabel(Icons.WALL);
					}
					else if (lineArray[col] == 'G') {
						boardArray[row][col] = new JLabel(Icons.GROUND);
					}
					else if (lineArray[col] == 'C') {
						boardArray[row][col] = new JLabel(Icons.COIN);
					}
					else if (lineArray[col] == 'D') {
						boardArray[row][col] = new JLabel(Icons.DOOR);
					}
					// Area character can move freely in
					else {
						boardArray[row][col] = new JLabel();
					}
				}
			} inputFile.close(); // close files
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	// Create level panel and game map
	private void createLvlPanel() {
		// Create main panel with corresponding background
		if (ChooseThemeFrame.themeChoice == 1) {
			lvlPanel = new JLabel(new ImageIcon("images/theme1.png"));
		}
		else if (ChooseThemeFrame.themeChoice == 2) {
			lvlPanel = new JLabel(new ImageIcon("images/theme2.png"));
		}
		else {
			lvlPanel = new JLabel(new ImageIcon("images/theme3.png"));
		}
		
		// Create new player
		// that responds to keyboard input
		player = new Character(Icons.characterArray[1], new String[] {"a", "d", " "});
		
		// Display exit button
		JButton exitButton = new JButton(new ImageIcon("images/exitButton.png"));
		exitButton.setBounds(45, 33, 45, 45);
		// Button function
		exitButton.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Close frame
			this.dispose();
			// Return to menus
			new MenuFrame();
		});

		
		// Display score icon
		JLabel scoreIcon = new JLabel(new ImageIcon("images/coin.png"));
		scoreIcon.setBounds(500, 0, 100, 100);
		// Display current score
		displayScore = new JLabel("0");
		displayScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  18));
		displayScore.setBounds(570, 0, 100, 100);
				
		// Set icon based on level txt file
		for (int row = 0; row < boardArray.length; row++) {
			for (int col = 0; col < boardArray[0].length; col++) {
				// Add labels to the panel
				// Set bounds for each of the squares/tiles
				if (boardArray[row][col].getIcon() == Icons.WALL) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.GROUND) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.COIN) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
					goal++;
				}
				else if (boardArray[row][col].getIcon() == Icons.DOOR) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}
			}
		}
		
		// Add panel to frame
		add(lvlPanel);
		// Set size of game board
		lvlPanel.setBounds(0, 0, 25*boardArray[0].length, 25*boardArray.length);
		
		// Add widgets
		lvlPanel.add(exitButton);
		lvlPanel.add(scoreIcon);
		lvlPanel.add(displayScore);

		// Set size and position of character
		player.setBounds(25, 425, 25, 25);
		lvlPanel.add(player);
		
		// Action when window closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Evoke if player reaches level goal
	public static void nextLevel() {		
		try {
			// Read txt using Scanner
			Scanner inputFile = new Scanner(new File("data/highscores.txt"));
			// Delimit to read each line of the txt file
			inputFile.useDelimiter(",|\r\n");
			
			// Read new names and scores in text file
			MenuFrame.readNames();
			// Sort from lowest to highest scores
			Arrays.sort(MenuFrame.leaderboardArray, Comparator.comparing(Leaderboard::getLifetimeScore));
			
			// Read leaderboard from highest to lowest
			for(int index = MenuFrame.leaderboardArray.length-1; index > -1; index--) {
				// Check which name in data matches current player name
				if (MenuFrame.leaderboardArray[index].getName().equalsIgnoreCase(MenuFrame.appendText)) {
					// Update level
					MenuFrame.currentLevel++;
					// Update text file with new level
					MenuFrame.appendUsingBufferedWriter("data/highscores.txt", MenuFrame.leaderboardArray[index].getName() + "," 
					+ MenuFrame.leaderboardArray[index].getLifetimeScore() + "," + MenuFrame.currentLevel, 1);
					// Close current level 
					ChooseCharacterFrame.levelFrame.dispose();
					// Load new level
					ChooseCharacterFrame.levelFrame = new LevelFrame(MenuFrame.currentLevel);
					// Exit loop
					break;
				}
			}
			
			// Close file
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			// Print if txt file not found
			System.out.println("File error");
		}
	}

	// Read keystrokes
	private void setupKeyBinders() {
		// Maps keystrokes and assign to string 
		InputMap inputMap;
		// Map strings to actions
		ActionMap actionMap;
		
		// Get keystrokes from level panel
		inputMap = lvlPanel.getInputMap();
		// Get strings from level panel
		actionMap = lvlPanel.getActionMap();
		
		// If user enters "a", assign "left" string
		inputMap.put(KeyStroke.getKeyStroke(player.getKey()[0].toCharArray()[0]), "left");
		// Action corresponds to string
		actionMap.put("left", new KeyAction("left"));
		
		// If user enters "d", assign "right" string
		inputMap.put(KeyStroke.getKeyStroke(player.getKey()[1].toCharArray()[0]), "right");
		actionMap.put("right", new KeyAction("right"));
		
		// If user enters "space", assign "jump" string
		inputMap.put(KeyStroke.getKeyStroke(player.getKey()[2].toCharArray()[0]), "jump");
		actionMap.put("jump", new KeyAction("jump"));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Read keyboard input
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			// Set horizontal displacement to 0 (at first)
			player.setdX(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
