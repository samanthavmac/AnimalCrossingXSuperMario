/* KEY ACTION
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file reads user input from
 * keyboard and assigns corresponding Character
 * actions.
 * 
 * MAJOR SKILLS: Methods, if statements,
 * creating classes for objects
 * 
 * ADDED FEATURES: Changing direction of 
 * character (basic)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENTS
import javax.swing.text.*;
import java.awt.event.*;

// Responsible for handling movement
// Responds to user input to call on
// different methods

public class KeyAction extends TextAction {
	private String key;
	
	public KeyAction(String key) {
		super(key);
		this.key = key;
	}
	
	// Reads keyboard input for movements
	@Override
	public void actionPerformed(ActionEvent e) {
		// Create instance of character class
		Character player = LevelFrame.player;
		
		// If "a" is selected / move left
		// No walls/bricks to player's left
		if (e.getActionCommand().equals(player.getKey()[0]) && 
				LevelFrame.boardArray[player.getRow()][player.getCol()].getIcon() != Icons.WALL 
				&& LevelFrame.boardArray[player.getRow()][player.getCol()].getIcon() != Icons.GROUND 
				&& LevelFrame.boardArray[player.getRow()][player.getCol()].getIcon() != Icons.DOOR) {
			player.moveLeft(Icons.characterArray);
		}
		// If "d" is selected / move right
		// No walls/bricks to player's rights
		else if (e.getActionCommand().equals(player.getKey()[1]) && 
				LevelFrame.boardArray[player.getRow()][player.getCol()+1].getIcon() != Icons.WALL 
				&& LevelFrame.boardArray[player.getRow()][player.getCol()+1].getIcon() != Icons.GROUND 
				&& LevelFrame.boardArray[player.getRow()][player.getCol()+1].getIcon() != Icons.DOOR &&
				player.jumping == false) {
			player.moveRight(Icons.characterArray);
		}
		// If "spacebar" is select / jump
		// Check if player is jumping off of ground
		else if (e.getActionCommand().equals(player.getKey()[2]) && 
				(LevelFrame.boardArray[player.getRow()+1][player.getCol()].getIcon() == Icons.WALL
				|| LevelFrame.boardArray[player.getRow()+1][player.getCol()].getIcon() == Icons.GROUND || 
				LevelFrame.boardArray[player.getRow()+1][player.getCol()].getIcon() == Icons.DOOR)) {
			// Evoke jumping methods
			player.setJumping(true);
			player.jump();
		}
	}
}
