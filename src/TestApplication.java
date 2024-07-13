/* CULMINATING - OBJECTS & CLASSES
 * APPLICATION FILE
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This is the starting file for
 * the application. It loads the login page.
 * 
 * MAJOR SKILLS: Methods, try-catch, if-statement
 * 
 * ADDED FEATURES: Background music
 * 
 * AREAS OF CONCERN: None.
 */

// https://www.youtube.com/watch?v=3q4f6I5zi2w&ab_channel=MaxO%27Didily

// Import statements
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.sound.sampled.*;

// Public class
public class TestApplication {	
	// Main method
	public static void main(String[] args) {
		// Open home frame
		new LoginFrame();
	}
	
	// https://www.youtube.com/watch?v=3q4f6I5zi2w&ab_channel=MaxO%27Didily
	// Pass file path to music as argument
	public static void playMusic(String location) {
		try {
			// Create File object containing music file address
			File musicPath = new File(location);
			
			// If music file is located
			if (musicPath.exists()) {
				// Input file path as audio source
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				// Create clip object
				Clip clip = AudioSystem.getClip();
				// Set audio of clip to file path
				clip.open(audioInput);
				// Start clip
				clip.start();
			}
			else {
				// If file not found
				System.out.println("Can not be located");
			}
		}
		catch (Exception e) {
			// If music can not be played
			System.out.println("Error");
		}
	}
}
