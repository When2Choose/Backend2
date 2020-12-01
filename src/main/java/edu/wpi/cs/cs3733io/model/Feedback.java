package edu.wpi.cs.cs3733io.model;

public class Feedback {
	public final String userName;
	public final String description;
	public final String uuidChoice;
	public final int alternativeIndex;

	public Feedback(String userName, String description, String uuidChoice, int uuidAlternative) {
		this.userName = userName;
		this.description = description;
		this.uuidChoice = uuidChoice;
		this.alternativeIndex = uuidAlternative;
	}

	public String getUserName() {
		return userName;
	}

	public String getDescription() {
		return description;
	}

	public String getUuidChoice() {
		return uuidChoice;
	}

	public int getUuidAlternative() {
		return alternativeIndex;
	}

	public String toString() {
		return "{" + "\"username: \"" + "\"" + userName + "\"" + "\"decsription: \"" + "\"" + description + "\""
				+ "\"uuidChoice: \"" + "\"" + uuidChoice + "\"" + "\"uuidAlternative: \"" + "\"" + Integer.toString(alternativeIndex)
				+ "\"" + "}";
	}

}
