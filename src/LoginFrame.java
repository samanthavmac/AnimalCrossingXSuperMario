/* CULMINATING - OBJECTS & CLASSES
 * LOGIN FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates a frame that
 * displays the log in page. Users can enter their
 * username and password. If the account does not exist,
 * a new account will be created.
 * 
 * MAJOR SKILLS: GUI widgets, appending to 
 * and reading a text file, for loops,
 * try-catch, if-else statements.
 * 
 * ADDED FEATURES: Users can save data to their account
 * and continue from where they left off.
 * 
 * AREAS OF CONCERN: Sample account
 * Username: Sam
 * Password: password
 */

//https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java

// Import statements
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

//Create GUI frame that displays login text fields
public class LoginFrame extends JFrame implements ActionListener {
	// Create String to hold user input
	public static String getName;
	public static String getPass;
	// Store current module number
	public static int currentLevel;
	
	// Stores number of lines in text file
	public static long numLines = 0;
	
	// Create an array that stores all login data
	public static User[] userArray;
	
	
	public LoginFrame() {
		// Create main panel
		JLabel mainPanel = new JLabel(new ImageIcon("images/loginBackground.png"));
		
		// Add instructions
		JLabel loginInstructions = new JLabel("Sign in to an existing account or create a new one.");
		
		// Add username label
		JLabel username = new JLabel("Username");
		username.setBounds(255,210, 100, 15);
		username.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		// Add text field to enter name
		JTextField enterName = new JTextField();
		enterName.setBounds(255,230, 290, 35);
		
		// Add password label
		JLabel password = new JLabel("Password");
		password.setBounds(255,280, 100, 15);
		password.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		// Add text field to enter password
		JTextField enterPass = new JTextField();
		enterPass.setBounds(255,300,290,35);
		
		// Add button to log in
		JButton submit = new JButton(new ImageIcon("images/nextLoginButton.png"));
		submit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0 ));
		submit.setBounds(384,350,32,32);
		// Add button function
		submit.addActionListener(e -> {
			// Play sound effect
			TestApplication.playMusic("sounds/click.wav");
			
			// Get user input
			getName = enterName.getText();
			getPass = enterPass.getText();
			
			// Read names of previous users
			readNames();
			// Sort array by highest level in ascending order
			Arrays.sort(userArray, Comparator.comparing(User::getLevel));			
			
			// Check if user already exists
			for (int i = userArray.length - 1; i > -1; i--) {
				if (userArray[i].getName().equalsIgnoreCase(getName)) {
					// Check if password is correct
					if (userArray[i].getPassword().equals(getPass)) {
						// Set current module number
						currentLevel = userArray[i].getLevel();
						//Close frame
						dispose();
						// Open home fram
						new HomeFrame();
						break; // End to prevent duplicates
					}
					// If password is incorrect
					else {
						// Style text field
						enterPass.setText("");
						enterPass.setBorder(new LineBorder(Color.red,1));
						break; // End to prevent duplicates
					}
				}
				// If by last name, user does not already exist
				// add them to text file
				else if (i == 0) {
					// Check that they enter a password
					if (enterPass.getText().equals("")) {
						enterPass.setText("Invalid password!");
						break; // End to prevent duplicates
					}
					else {
						// Add new character details
						appendUsingBufferedWriter("data/login.txt", getName + "," + getPass + ",1", 1);
						// Set current module to 1
						currentLevel = 1;
						//Close frame
						dispose();
						// Open home fram
						new HomeFrame();
						break; // End to prevent duplicates
					}
				}
			}
				
		});
		
		// Add widgets
		mainPanel.add(loginInstructions);
		mainPanel.add(username);
		mainPanel.add(enterName);
		mainPanel.add(password);
		mainPanel.add(enterPass);
		mainPanel.add(submit);
		
		// Add main panel
		add(mainPanel);
		
		// FRAME CREATION
		setTitle("SamOverflow"); // sets title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes app
		setSize(800, 440); //sets the x-dimension and y-dimension of the this
		setVisible(true); 
		setLayout(null);
		setResizable(false);
	}
	
	public static int findNumOfLines() {
		try(LineNumberReader lineNumberReader =
			    new LineNumberReader(new FileReader(new File("data/login.txt")))) {
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
		userArray = new User[findNumOfLines()];
		
		try {
			// Read txt using Scanner
			Scanner inputFile = new Scanner(new File("data/login.txt"));
			// Delimit to read each line of the txt file
			inputFile.useDelimiter(",|\r\n");
			// Use a for loop to read and assign the value of each field
			// of each laptop object
			for(int index = 0; index < userArray.length; index++) {
				String name = inputFile.next();
				String password = inputFile.next();
				int level = inputFile.nextInt();
				// Create a Leaderboard object in the leaderboardArray
				// with the above values passed as parameters
				userArray[index] = new User(name, password, level);
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
	
	// Required to run GUI frame
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
