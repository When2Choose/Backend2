package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class Feedback {
	public final String userName;
	public final String description;
	public final UUID uuidChoice; 
	public final UUID uuidAlternative;
	
	public Feedback(String userName, String description, UUID uuidChoice, UUID uuidAlternative) {
		this.userName = userName;
		this.description = description;
		this.uuidChoice = uuidChoice;
		this.uuidAlternative = uuidAlternative;
	}

	public String getUserName() {
		return userName;
	}	

}
