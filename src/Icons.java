/* CHOOSE CHARACTER FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates public final 
 * ImageIcons that can be accessed throughout the program
 * 
 * MAJOR SKILLS: Creatign image icons, if statements
 * 
 * ADDED FEATURES: Select character and 
 * character direction (basic)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENT
import javax.swing.*;

// Public class
public class Icons {
	// Create icons fpr game map
	// Create ground
	public static final ImageIcon GROUND = new ImageIcon("images/grass.png");
	// Create wall
	public static final ImageIcon WALL = new ImageIcon("images/wall.png");
	// Create door
	public static final ImageIcon DOOR = new ImageIcon("images/door.png");
	// Create a coin
	public static final ImageIcon COIN = new ImageIcon("images/coin.png");
	// Create a Classic Isabelle character
	public static final ImageIcon CLASSICL = new ImageIcon("images/cuteIzzyL.png");
	public static final ImageIcon CLASSICR = new ImageIcon("images/cuteIzzyR.png");
	// Create a Party Isabelle character
	public static final ImageIcon FUNL = new ImageIcon("images/funIzzyL.png");
	public static final ImageIcon FUNR = new ImageIcon("images/funIzzyR.png");
	// Create a Silly Isabelle character
	public static final ImageIcon SILLYL = new ImageIcon("images/sillyIzzyL.png");
	public static final ImageIcon SILLYR = new ImageIcon("images/sillyIzzyR.png");

	
	public static ImageIcon[] characterArray = new ImageIcon[2];
	
	public static void resetChar(int playerChoice) {
		if (playerChoice == 1) {
			characterArray[0] = CLASSICR;
			characterArray[1] = CLASSICL;
		}
		else if (playerChoice == 2) {
			characterArray[0] = FUNL;
			characterArray[1] = FUNR;
		}
		else {
			characterArray[0] = SILLYL;
			characterArray[1] = SILLYR;
		}
	}
}
