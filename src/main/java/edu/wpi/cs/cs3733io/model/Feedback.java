package edu.wpi.cs.cs3733io.model;

public class Feedback {
	public final String userName;
	public final String description;
	public final String uuidChoice;
	public final String uuidAlternative;

	public Feedback(String userName, String description, String uuidChoice, String uuidAlternative) {
		this.userName = userName;
		this.description = description;
		this.uuidChoice = uuidChoice;
		this.uuidAlternative = uuidAlternative;
	}

	public String getUserName() {
		return userName;
	}

	public String toString() {
		
		
		
		return "{" + "\"username: \"" + "\"" + userName + "\"" +  
				"\"decsription: \"" + "\"" + description + "\"" +  
				"\"uuidChoice: \"" + "\"" + uuidChoice + "\"" +  
				"\"uuidAlternative: \"" + "\"" + uuidAlternative + "\"" +  "}";
		
	}

}
