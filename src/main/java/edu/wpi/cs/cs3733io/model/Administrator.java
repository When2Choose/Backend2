package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class Administrator {
	public final String name;
	String passweord;
	public final UUID uuidChoice;
	
	public Administrator(String name, UUID uuidChoice) {
		this.name = name;
		this.uuidChoice = uuidChoice;
	}

	public String getName() {
		return name;
	}

	public UUID getUuidChoice() {
		return uuidChoice;
	} 
	
	public boolean requestDeleteAllChoices(int nDaysOld) {
		return false;
	}
}
