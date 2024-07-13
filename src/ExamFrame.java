/* CULMINATING - OBJECTS & CLASSES
 * EXAM FRAME
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file creates a new frame
 * for each question. There is a mixture of multiple
 * choice and short answer questions. Final score is
 * totalled at the end.
 * 
 * MAJOR SKILLS: Reading a text file, creating
 * objects, GUI widgets, for loops, if-else statements
 * 
 * ADDED FEATURES: Displays the correct answer after
 * each question, displays final score at the end.
 * 
 * AREAS OF CONCERN: None.
 */

// Import statements
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;

// Create GUI frame that exam questions
public class ExamFrame extends JFrame implements ActionListener {
	// Create frame and panel to display question
	public static JFrame questionFrame;
	public static JPanel questionPanel;
	// Create array with questions
	public static Questions questionArray[];
	// Create label that displays question number
	public static JLabel questionNum;
	// Create an accummulator that tracks question #
	public static int accum = 0;
	public static boolean[] scoreArray = new boolean[10];
	// Create a JLabel that displays answer
	public static JLabel answer;
	// Create String that stores user input
	public static String userInput;
	
	// Create a frame that allows user to start the exam
	ExamFrame() {
		// Create main panel
		// Add box layout
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0,0,800,600);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setAlignmentY(CENTER_ALIGNMENT);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// stack rows vertically
		
		// Add logo
		JLabel logo  = new JLabel(new ImageIcon("images/loginLogo.png"));
		logo.setAlignmentX(CENTER_ALIGNMENT);
		logo.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));
		mainPanel.add(logo);
		
		// Add instructions
		JLabel examInstructions = new JLabel("The exam contains 10 questions. Good luck!");
		examInstructions.setAlignmentX(CENTER_ALIGNMENT);
		examInstructions.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		examInstructions.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0 ));
		mainPanel.add(examInstructions);

		// Read questions.txt and add to array
		readQuestions();
		
		// GENERATE QUESTIONS
		// One question will appear at a time on the frame
		JButton startExam = new JButton(new ImageIcon("images/startExamButton.png"));
		startExam.setAlignmentX(CENTER_ALIGNMENT);
		startExam.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0 ));
		// Button function
		startExam.addActionListener(e -> {
			// Only open module if level is unlocked
			newQuestion();			
			// Set main frame to be not visible
			setVisible(false);
		});	
		
		// Add button to frame
		mainPanel.add(startExam);
		
		// Add a back button
		JButton backButton = new JButton("Back to Home");
		backButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		// Add function to button
		backButton.addActionListener(e -> {
			// close frame
			dispose();
			// Open home frame
			new HomeFrame();
		});
		// Add to panel
		mainPanel.add(backButton);

		// Add widgets to frame
		add(mainPanel);
		
		// FRAME CREATION
		setTitle("Final Exam"); // sets title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes app
		setSize(800, 600); //sets the x-dimension and y-dimension of the this
		setLayout(null);
		setVisible(true); 
		setResizable(false);
	}

	// Reads questions from text file
	public static void readQuestions() {
		questionArray = new Questions[10];
		
		try {
			// Read txt using Scanner
			Scanner inputFile = new Scanner(new File("data/questions.txt"));
			// Delimit to read each line of the txt file
			inputFile.useDelimiter(",|\r\n");
			// Use a for loop to read and assign the value of each field
			// of each laptop object
			for(int index = 0; index < questionArray.length; index++) {
				int num = inputFile.nextInt();
				String question = inputFile.next();
				int type = inputFile.nextInt();
				String answer = inputFile.next();
				String option1 = inputFile.next();
				String option2 = inputFile.next();
				String option3 = inputFile.next();
				String option4 = inputFile.next();

				// Create a Question object in the questionArray
				// with the above values passed as parameters
				questionArray[index] = new Questions(num, question, type, answer, option1, option2, option3, option4);				
			}
			// Close file
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			// Print if txt file not found
			System.out.println("File error");
		}
	}
	
	// Method that creates new question
	public static void newQuestion() {	
		// Create a new frame
		questionFrame = new JFrame();
		
		// Create holder panel
		JPanel holder = new JPanel();
		holder.setBounds(0,0,800,600);
		// Set background
		holder.setBackground(Color.WHITE);

		
		// Create a new panel containing question graphic
		questionPanel = new JPanel();
		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
		questionPanel.setBackground(Color.WHITE);
		questionPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		// Add module image
		JLabel moduleImage = new JLabel(new ImageIcon("images/question" + accum + ".png"));
		moduleImage.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		moduleImage.setAlignmentX(CENTER_ALIGNMENT);
		// Add to panel
		questionPanel.add(moduleImage);
		
		// Add a submit button
		JButton submit = new JButton(new ImageIcon("images/submitButton.png"));
		// Remove border
		submit.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		// Display input fields based on type of question
		// If a multiple choice question
		if (questionArray[accum].getType() == 1) {
			// Create panel to hold buttons
		    JPanel buttonPanel = new JPanel();
		    buttonPanel.setBackground(Color.WHITE);
		    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		    buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		    // Set alignment
		    buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		    	
		    // Add buttons to panel
		    // Create array of toggle buttons
		    JRadioButton[] optionButton = new JRadioButton[4];
		    
		    // Set text of radio buttons
		    optionButton[0] = new JRadioButton(questionArray[accum].getOption1());
		    optionButton[1] = new JRadioButton(questionArray[accum].getOption2());
		    optionButton[2] = new JRadioButton(questionArray[accum].getOption3());
		    optionButton[3] = new JRadioButton(questionArray[accum].getOption4());
		   
		    // Use a loop to add each button
		    for (int i = 0; i < 4; i++) {
		    	 // Set font style
			    optionButton[i].setFont(new Font("Monospaced", Font.PLAIN, 14));
		    	// Add each button to panel
		        buttonPanel.add(optionButton[i]);
		    }
		    
		    // Add button panel tp main panel
		    questionPanel.add(buttonPanel);
		    
			// Add function to submit button
			submit.addActionListener(e -> {
				// Hide submit button from frame
				submit.setVisible(false);
				// Retrieve answer from user
				for (int i = 0; i < 4; i++) {
					// Check which button is selected
					if (optionButton[i].isSelected()) {
						// Store answer as string
						userInput = optionButton[i].getText();
						break;
					}
				}
				
				// Invoke method to check answer
				checkAnswer();
			});			    	
		 }
		
		// If a text field question
		if (questionArray[accum].getType() == 2) {
			
			// Create panel with flow layout
			JPanel fieldHolder = new JPanel();
			fieldHolder.setLayout(new FlowLayout());
			fieldHolder.setBackground(Color.WHITE);
			
			// Create text field for user input
			JTextField inputAnswer = new JTextField(25);
			// Add to panel
			fieldHolder.add(inputAnswer);
			questionPanel.add(fieldHolder);
			
			// Add function to submit button
			submit.addActionListener(e -> {
				// Hide submit button from frame
				submit.setVisible(false);
				
				// Get answer form user
				userInput = inputAnswer.getText(); 

				// Invoke method to check answer
				checkAnswer();
			});			
		}
		
	    // Set alignment
		submit.setAlignmentX(CENTER_ALIGNMENT);
		// Add widget to panel
		questionPanel.add(submit);
		
		// Add panels to frame/larger panel
		holder.add(questionPanel);
		questionFrame.add(holder);
		
		// Style frame
		questionFrame.setBackground(Color.WHITE);
		questionFrame.setLayout(null);
		questionFrame.setTitle("Question " + (accum+1));
		questionFrame.setSize(800, 600); //sets the x-dimension and y-dimension of the this
		questionFrame.setResizable(false);
		questionFrame.setVisible(true);
	}
	
	// Check is user input matches answer key
	public static void checkAnswer() {
		// Display correct answer
		if (userInput.equals(questionArray[accum].getAnswer())) {
			// If answer is correct
			answer = new JLabel("Correct! The answer is: " + questionArray[accum].getAnswer());
			// Store answer as correct in an array
			scoreArray[accum] = true;
		}
		else {
			// Else answer is wrong
			answer = new JLabel("Incorrect. The answer is: " + questionArray[accum].getAnswer());
		}
		
		// Add answer to frame
		questionPanel.add(answer);
		// Style answer label
		answer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		answer.setAlignmentX(CENTER_ALIGNMENT);
		answer.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		
		// Add a next button
		JButton next = new JButton(new ImageIcon("images/nextButton.png"));
		// Remove border
		next.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		// Add button function
		next.addActionListener(event -> {
			// Close current frame
			questionFrame.dispose();
			// Increase accumulator
			accum++;
			// Check if not last question
			if (accum < 10) {
				// Display next question
				newQuestion();
			}
			else {
				// Invoke method to display final score
				displayScore();
			}
		});	
	    // Set alignment
		next.setAlignmentX(CENTER_ALIGNMENT);
		// Add button to panel
		questionPanel.add(next);
	}
	
	public static void displayScore() {
		// Create new JFrame
		JFrame scoreFrame = new JFrame();
		
		// Create new background holder
		JLabel scorePanel = new JLabel(new ImageIcon("images/white.png"));
		scorePanel.setBounds(0,0,800,600);
		scorePanel.setBackground(Color.WHITE);
		
		// Add a back button
		JButton backButton = new JButton(new ImageIcon("images/backButton.png"));
		backButton.setBounds(50,40,50,10);
		backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// Add function to button
		backButton.addActionListener(e -> {
			// close frame
			scoreFrame.dispose();
			// Open home frame
			new HomeFrame();
		});
		// Add to panel
		scorePanel.add(backButton);
				
		// Header
		JLabel examResults = new JLabel("Exam Results");
		examResults.setBounds(315,120,800,70);
		examResults.setFont(new Font("Monospaced", Font.BOLD, 26)); 
		scorePanel.add(examResults);
		
		// Retrieve results from score array
		for (int i = 0; i < 10; i++) {	
			// Display question number
			questionNum = new JLabel(new ImageIcon("images/score" + (i+1) + ".png"));
			questionNum.setBounds(133 + (i*54), 200, 48, 20);
			scorePanel.add(questionNum);

			// If answer is correct
			if (scoreArray[i] == true) {
				// Create an image icon
				JLabel correct = new JLabel(new ImageIcon("images/correctIcon.png"));
				correct.setBounds(133 + (i*54), 236, 48, 50);
				// Add to frame
				scorePanel.add(correct);
			}
			// If answer is wrong
			else {
				// Create an image icon
				JLabel incorrect = new JLabel(new ImageIcon("images/incorrectIcon.png"));
				incorrect.setBounds(133 + (i*54), 236, 48, 50);

				// Add to frame
				scorePanel.add(incorrect);
			}
		}
		
		// Add panel to frame
		scoreFrame.add(scorePanel);

		// Style frame
		scoreFrame.setBackground(Color.WHITE);
		scoreFrame.setTitle("Exam Results");
		scoreFrame.setLayout(null);
		scoreFrame.setSize(800, 600); //sets the x-dimension and y-dimension of the this
		scoreFrame.setResizable(false);
		scoreFrame.setVisible(true);
	}
	
	// Allows GUI frame to run
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
