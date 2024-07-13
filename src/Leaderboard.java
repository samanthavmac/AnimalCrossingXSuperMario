/* LEADERBOARD CLASS
 * ANIMAL CROSSING APPLICATION
 * Samantha Mac
 * May 28, 2023
 * ICS3U1-05 Mrs. Biswas
 * 
 * DESCRIPTION: This file is a blueprint for
 * creating one of five Leaderboard objects, which
 * will be displayed on the leaderboard. Includes the
 * player's name, score, and level
 * 
 * MAJOR SKILLS: Setters and getters, constructors
 * 
 * 
 * ADDED FEATURES: Display leaderboard (advanced)
 * 
 * AREAS OF CONCERN: None.
 */

public class Leaderboard {
	// FIELDS
	private int lifetimeScore;
	private int playerLevel;
	private String name;
	
	// CONSTRUCTOR
	public Leaderboard(String name, int lifetimeScore, int playerLevel) {
		super();
		this.lifetimeScore = lifetimeScore;
		this.playerLevel = playerLevel;
		this.name = name;
	}
	
	// SETTERS AND GETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLifetimeScore() {
		return lifetimeScore;
	}

	public void setLifetimeScore(int lifetimeScore) {
		this.lifetimeScore = lifetimeScore;
	}
	
	public int getPlayerLevel() {
		return playerLevel;
	}

	public void setLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}
}
