/* CULMINATING - OBJECTS & CLASSES
 * USER CLASS
 * Samantha Mac
 * June 14, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This is a class file that
 * creates a new User object when a user logs
 * in or signs up. Stores their name, password,
 * and current level.
 * 
 * MAJOR SKILLS: Constructor, fields, setters
 * and getters
 * 
 * ADDED FEATURES: None.
 * 
 * AREAS OF CONCERN: None.
 */

public class User {
	// FIELDS
	private String name;
	private String password;
	private int level;
	
	// CONSTRUCTOR
	public User(String name, String password, int level) {
		super();
		this.name = name;
		this.password = password;
		this.level = level;
	}

	// SETTERS AND GETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// TO STRING
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", level=" + level + "]";
	}	

}
