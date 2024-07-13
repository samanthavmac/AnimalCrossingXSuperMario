/* CULMINATING - OBJECTS & CLASSES
 * QUESTIOSN CLASS
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This is a class file that
 * creates a new Question object for each
 * question in the exam. It contains the question,
 * answer, and type of question.
 * 
 * MAJOR SKILLS: Constructor, fields, setters
 * and getters
 * 
 * ADDED FEATURES: None.
 * 
 * AREAS OF CONCERN: None.
 */

public class Questions {
	// FIELDS
	private int num;
	private String question;
	private int type;
	private String answer;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	// CONSTRUCTOR
	public Questions(int num, String question, int type, String answer, String option1, String option2,
			String option3, String option4) {
		super();
		this.num = num;
		this.question = question;
		this.type = type;
		this.answer = answer;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
	
	// SETTERS AND GETTERS
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	@Override
	public String toString() {
		return "Questions [num=" + num + ", question=" + question + ", type=" + type + ", answer=" + answer
				+ ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4
				+ "]";
	}
	
	// TO STRING
	
	
}
