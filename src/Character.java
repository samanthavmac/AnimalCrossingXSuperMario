/* ANIMAL CROSSING
 * CHARACTER CLASS
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file is a blueprint for
 * creating Character objects. Each character has
 * properties and utility methods (movements) 
 * triggered by user-input/keystrokes.
 * 
 * MAJOR SKILLS: Methods, for loop, if statement,
 * creating object classes, setters and getters,
 * reading and appending input from/to text file,
 * reading position on a GUI frame.
 * 
 * ADDED FEATURES: Update score (basic)
 * 
 * AREAS OF CONCERN: Some bugs with character jumping.
 * Character needs at least two units of horizontal space
 * in order to jump forwards/backwards.
 */

// IMPORT STATEMENTS
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;

// Public class
public class Character extends JLabel implements ActionListener {
	// FIELDS
	// This deals with the character facing one direction (static)
	private ImageIcon icon;
	// This deals with which key is being pressed
	private String[] key;
	// Controls how many units the character moves
	private int dX, dY;
	// How long character should jump for
	private Timer jumpTimer = new Timer(25, this);
	// When character is jumping
	private int jumpCounter;
	// Determines when character is jumping
	public static boolean jumping = false;
	// How long character should fall for
	private Timer fallTimer = new Timer(25, this);
	// When character is falling
	private int fallCounter;
	// Determines when character is falling
	public static boolean falling = false;
	// Track number of coins collected
	public static int score = 0;
	
	
	// Constructor for ImageIcon character (static image)
	public Character(ImageIcon icon, String[] key) {
		super();
		this.icon = icon; // Sets icon field
		this.key = key; // Sets key field
	}

	// SETTERS AND GETTERS
	
	public ImageIcon getIcon() {
		return icon;
	}


	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}


	public String[] getKey() {
		return key;
	}


	public void setKey(String[] key) {
		this.key = key;
	}


	public int getdX() {
		return dX;
	}


	public void setdX(int dX) {
		this.dX = dX;
	}


	public int getdY() {
		return dY;
	}


	public void setdY(int dY) {
		this.dY = dY;
	}


	public Timer getJumpTimer() {
		return jumpTimer;
	}


	public void setJumpTimer(Timer jumpTimer) {
		this.jumpTimer = jumpTimer;
	}


	public int getJumpCounter() {
		return jumpCounter;
	}


	public void setJumpCounter(int jumpCounter) {
		this.jumpCounter = jumpCounter;
	}


	public boolean isJumping() {
		return jumping;
	}


	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}


	public Timer getFallTimer() {
		return fallTimer;
	}


	public void setFallTime(Timer fallTimer) {
		this.fallTimer = fallTimer;
	}


	public int getFallCounter() {
		return fallCounter;
	}


	public void setFallCounter(int fallCounter) {
		this.fallCounter = fallCounter;
	}


	public boolean isFalling() {
		return falling;
	}


	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	// UTILITY METHODS
	// -------------------------------------------
	
	// Make character jump
	public void jump() {
		// Set boolean
		// Will help determine when to fall and appropriate
		// character movements
		jumping = true;
		// Track displacement while jumping
		jumpCounter = 0;
		jumpTimer.start();
		
		// Play jump sound
		AnimalCrossingApplication.playMusic("sounds/jump.wav");
	}
	// Make character fall
	public void fall() {
		// Will directly follow jumping
		falling = true;
		// Track displacement while falling
		fallCounter = 0;
		fallTimer.start();
	}
	//
	public void moveRight(ImageIcon[] CharacterArray) {
		// Change icon to face right
		setIcon(Icons.characterArray[1]);
		// x-coordinate to change 5 units at a times
		dX = 4;
		// Check if ground below is wall/ground
		// Will fall immediately to prevent from walking in air
		if (LevelFrame.boardArray[getRow()-1][getCol()].getIcon() != Icons.WALL &&
				LevelFrame.boardArray[getRow()-1][getCol()].getIcon() != Icons.GROUND 
				&& !jumping) {
			fall();
		}
		// Add change to the x change
		// Use dimensions of the character ie 25, 25
		setBounds(getX() + dX, getY(), 25, 25);
		// Check if any coins collected
		collectCoin();
	}
	//
	public void moveLeft(ImageIcon[] CharacterArray) {
		// Change icon to face left
		setIcon(Icons.characterArray[0]);
		// x-coordinate to change 5 units at a times
		dX = -4;
		// Check if ground below is wall/ground
		// Will fall immediately to prevent from walking in air
		if (LevelFrame.boardArray[getRow()-1][getCol()].getIcon() != Icons.WALL &&
				LevelFrame.boardArray[getRow()-1][getCol()].getIcon() != Icons.GROUND
				&& !jumping) {
			fall();
		}
		// Add change to the x change
		setBounds(getX() + dX, getY(), 25, 25);
		// Check if any coins collecteds
		collectCoin();
	}
	
	// Get position of character
	// 
	public int getRow() {
		// Divides current height of character by
		// height of game board to determine y value
		return getY()/25;
	}
	
	// Finds current col of character
	// in relation to the game board
	public int getCol() {
		return getX()/25;
	}
	
	// Finds current row of character
	// in relation to the game board	
	public void collectCoin() {
		// Store current coordinate as integers
		// will use to locate character on the baord
		int row = getRow();
		int col = getCol();
		if (LevelFrame.boardArray[row][col].getIcon() == Icons.COIN) {
			// remove the coin from the board when the character
			// collects it
			LevelFrame.boardArray[row][col].setIcon(null);
			// Increase score by one
			score++;
			// Play sound effect
			AnimalCrossingApplication.playMusic("sounds/coinSound.wav");
			// Check if new high score created
			// for particular user
			updateLifetimeScore();
		}
		
		// Display new score
		LevelFrame.displayScore.setText(Integer.toString(score));	
	}
	
	// Update high score
	public void updateLifetimeScore() {
		try {
			// Read txt using Scanner
			Scanner inputFile = new Scanner(new File("data/highscores.txt"));
			// Delimit to read each line of the txt file
			inputFile.useDelimiter(",|\r\n");
			
			// Re-read new names added to text file
			MenuFrame.readNames();
			// Sort from lowest to highest score
			Arrays.sort(MenuFrame.leaderboardArray, Comparator.comparing(Leaderboard::getLifetimeScore));

			// Start reading array from highest to lowest
			for (int k = MenuFrame.leaderboardArray.length-1; k > -1; k--) {
				// Check if current score beats any high scores and
				// if current user matches name of current element in array
				if (score < MenuFrame.leaderboardArray[k].getLifetimeScore() 
						&& MenuFrame.leaderboardArray[k].getName().equalsIgnoreCase(MenuFrame.appendText)) {
					// Exit the loop if no new high score made
					break;
				}
				// If new high score created for correspondign user
				// update text files
				else if (score > MenuFrame.leaderboardArray[k].getLifetimeScore() 
						&& MenuFrame.leaderboardArray[k].getName().equalsIgnoreCase(MenuFrame.appendText)) {
					MenuFrame.appendUsingBufferedWriter("data/highscores.txt", MenuFrame.leaderboardArray[k].getName() 
							+ "," + score + "," + MenuFrame.currentLevel, 1);
					// Exit loop
					break;
				}
			}
			
			// Stop reading file
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			// Print if txt file not found
			System.out.println("File error");
		}
	}

	// Checks for collisions with bricks, ceiling,
	// and where there is no ground
	@Override
	public void actionPerformed(ActionEvent e) {
		// CHeck if user is at portal and met level goal
		if (score == LevelFrame.goal && LevelFrame.boardArray[getRow()][getCol()+1].getIcon() == Icons.DOOR) {
			// reset level goal and current score
			LevelFrame.goal = 0;
			score = 0;
			// Load new level if user collects all points
			LevelFrame.nextLevel();
		}
		
		// If player is falling and there is ground beneath
		if (jumping && dY < 0 && (LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.WALL
				|| LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.GROUND)) {
			jumping = false;
			// Stop vertical movement
			dY = 0;
			jumpTimer.stop();
			// For player to jump, they must also fall
			fall();
			return;
		}
		
		if (falling && dY > 0) {
			// Get row above players
			int nextRow = getRow() + 1;
			// Check if ground above is a wall to stop player from jumping
			if (nextRow >= LevelFrame.boardArray.length ||
					(LevelFrame.boardArray[nextRow][getCol()].getIcon() == Icons.WALL || 
					LevelFrame.boardArray[nextRow][getCol()].getIcon() == Icons.GROUND)) {
				// For player to fall, they must have previously jumped
				falling = false;
				// Stop vertical movement
				dY = 0;
				fallTimer.stop();
				return;
			}
		}
		
		// When jumping boolean is true
		if (jumping) {
			// Check if ground is beneath
			if ((LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.WALL 
					|| LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.GROUND)
					&& dY > 0) {
				dY = 0;
			}	
			// Update jump counter with every vertical displacement
			jumpCounter++;
			// Keep moving up if player did not jump high enough
			if (jumpCounter <= 10) {
				dY = -5;
			}
			// Move down once reached max height
			else if (jumpCounter <= 20)
				dY = 5;
			else {
				jumping = false;
				// Stop vertical movement
				dY = 0;
				jumpTimer.stop();
				// Begin falls
				fall();
			}
		}
		
		// Reset everything so character stops jumping
		else if (falling) {
			fallCounter++;
			dY = 5;
			// Check if ground above to stop from jumping too high
			if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL ||
					LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GROUND) {
				falling = false;
				// Stop vertical movement
				dY = 0;
				fallTimer.stop();
			}
			else if (fallCounter >= 20) { // stop falling after reaching ground level
				falling = false;
				// Stop vertical movement
				dY = 0;
				fallTimer.stop();
			}
			
			setBounds(getX(), getY() + dY, 25, 25); // update character's position

			return; // exit method since character is falling
		}
		
		// If character is touching wall to its right and moving right
		if ((LevelFrame.boardArray[getRow()][getCol()+1].getIcon() == Icons.WALL ||
				LevelFrame.boardArray[getRow()][getCol()+1].getIcon() == Icons.GROUND || 
				LevelFrame.boardArray[getRow()][getCol()+1].getIcon() == Icons.DOOR) 
				&& dX > 0) {
			dX = 0;
		}
		
		// If character is touching wall to its left and moving left
		else if ((LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.WALL ||
				LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.GROUND || 
				LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.DOOR)
				&& dX < 0) {
			dX = 0;
		}
		
		// Checks if character is touching a ceiling and moving upwards
		else if ((LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL 
				|| LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GROUND)
				&& dY > 0) {
			dY = 0;
		}	
	
		// Character touching the ground and moving down
		else if ((LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.WALL 
				|| LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.GROUND)) {
			dY = 0;
		}
		
		// Check if any coins are collected during jumps and falls
		collectCoin();
		// Set any character positioning
		setBounds(getX() + dX, getY() + dY, 25, 25);
			
	}
}
