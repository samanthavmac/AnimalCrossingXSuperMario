/* MENU FRAME
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This is homepage where users can enter
 * their name and access the leaderboard.
 * 
 * MAJOR SKILLS: Methods, for loop, if statements,
 * reading and appending text from/to text file,
 * reading number of lines in text file,
 * creating Java Swing widgets
 * 
 * ADDED FEATURES: Homepage (basic), 
 * storing initials and rembering 
 * exiting user's data (advanced)
 * 
 * AREAS OF CONCERN: None.
 */

// IMPORT STATEMENTS
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

//SOURCES
/* https://howtodoinjava.com/java/io/count-file-lines/#:~:text=
 * Counting%20Lines%20using%20Stream%20of%20Lines,-The%20Files.
 * &text=lines()%20method%20can%20be,closed%20by%20closing%20the%20stream.
 */
//https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java

// Public class that creates a GUI JFrame
public class MenuFrame extends JFrame implements ActionListener {
	// Create Scanner object
	public static Scanner sc = new Scanner(System.in);	

	// Create String to hold user input
	public static String appendText;
	// Store current level of each player
	public static int currentLevel = 1;
	
	// Stores all leaderboard stats in array
	public static Leaderboard[] leaderboardArray;
	// Stores number of lines in text file
	public static long numLines = 0;

	// Create new instance of class
	public MenuFrame() {
		// Create JLabel with background image
		JLabel backgroundImage = new JLabel(new ImageIcon("images/homeBackground.png"));
		// Set size
		backgroundImage.setBounds(0,0,1024,560);

		// Create JTextField
		JTextField inputName = new JTextField();
		inputName.setBackground(new Color(254, 239, 186));
		inputName.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  18));
		inputName.setBounds(504,225,285,42);
		// Create button for user to submit name
		JButton getButton = new JButton(new ImageIcon("images/submitButton.png"));
		getButton.setBackground(new Color(140, 103, 45));
		getButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		getButton.setBounds(772,220,100,50);
		// Add function to button
		getButton.addActionListener(e -> {
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Get text from input field
			appendText = inputName.getText(); 
			// Read names from text file
			readNames();
						
			// Check if user already exists
			for (int i = leaderboardArray.length-1; i > -1; i--) {
				if (leaderboardArray[i].getName().equalsIgnoreCase(appendText)) {
					// Check what existing player level is
					currentLevel = leaderboardArray[i].getPlayerLevel();
					break; // End to prevent duplicates
				}
				// If by last name, user does not already exist
				// add them to text file
				else if (i == leaderboardArray.length - 1) {
					// Add new character details
					appendUsingBufferedWriter("data/highscores.txt", appendText + ",0,1", 1);
					// Start from first level
					currentLevel = 1;
					break; // End to prevent duplicates
				}
			}
				
			// Move to next frame to choose character
			// and store name
			new ChooseThemeFrame();
			// Exit current frame
			this.dispose();
		});
			
		// Create button to view leaderboard
		JButton leaderboardButton = new JButton(new ImageIcon("images/leaderboardButton.png"));
		leaderboardButton.setOpaque(false);
		leaderboardButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		leaderboardButton.setBounds(489,290,184,52);
		// Add function to button
		leaderboardButton.addActionListener(e1 -> {	
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Read names from text file
			readNames();
			// Open leaderboard frame
			new HighscoreFrame();
			// Close current frame
			this.dispose();
		});
		
		// Create button to view rules
		JButton rulesButton = new JButton(new ImageIcon("images/rulesButton.png"));
		rulesButton.setOpaque(false);
		rulesButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		rulesButton.setBounds(650,290,184,52);
		// Add function to button
		rulesButton.addActionListener(e1 -> {	
			// Play button sound
			AnimalCrossingApplication.playMusic("sounds/button.wav");
			// Open rules frame
			new RulesFrame();
			// Close current frame
			this.dispose();
		});

		// Add widgets to main frame
		add(backgroundImage);
		backgroundImage.add(inputName);
		backgroundImage.add(getButton);
		backgroundImage.add(leaderboardButton);
		backgroundImage.add(rulesButton);

		
		// FRAME STYLINGS
		setTitle("Home"); // sets title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,560);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setVisible(true);
	}

	/* https://howtodoinjava.com/java/io/count-file-lines/#:~:text=
	 * Counting%20Lines%20using%20Stream%20of%20Lines,-The%20Files.
	 * &text=lines()%20method%20can%20be,closed%20by%20closing%20the%20stream.
	 */
	// Find num of lines of text file
	public static int findNumOfLines() {
		try(LineNumberReader lineNumberReader =
			    new LineNumberReader(new FileReader(new File("data/highscores.txt")))) {
			    //Skip to last line
			  	lineNumberReader.skip(Long.MAX_VALUE);
			  	numLines = lineNumberReader.getLineNumber();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		int newNum = (int)numLines; // convert long to int
		// Return int of number of lines
		return newNum;
	}
	
	// Read text file of initials and store in leaderboard array
	public static void readNames() {
		leaderboardArray = new Leaderboard[findNumOfLines()];
		
		try {
			// Read txt using Scanner
			Scanner inputFile = new Scanner(new File("data/highscores.txt"));
			// Delimit to read each line of the txt file
			inputFile.useDelimiter(",|\r\n");
			// Use a for loop to read and assign the value of each field
			// of each laptop object
			for(int index = 0; index < leaderboardArray.length; index++) {
				String name = inputFile.next();
				int lifetimeScore = inputFile.nextInt();
				int playerLevel = inputFile.nextInt();
				// Create a Leaderboard object in the leaderboardArray
				// with the above values passed as parameters
				leaderboardArray[index] = new Leaderboard(name, lifetimeScore, playerLevel);
			}
			// Close file
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			// Print if txt file not found
			System.out.println("File error");
		}
	}
	
	//https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
	// Add new line to text file
	public static void appendUsingBufferedWriter(String filePath, String appendText, int noOfLines) {
		// Create file pathway to read
		File file = new File(filePath);
		// Create file writer to edit text file
		FileWriter fr = null;
		
		try {
			fr = new FileWriter(file, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Create buffered writer to add a new line
		BufferedWriter br = new BufferedWriter(fr);
		try {
			// Create new line
			// Add corresponding text
			br.write("\r\n" + appendText);
			// Close writers
			br.close();
			fr.close();
		} catch (IOException e2) {
			e2.printStackTrace(); }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
