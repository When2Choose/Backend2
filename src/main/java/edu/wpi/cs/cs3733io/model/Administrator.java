package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class Administrator {
	public final String name;
	String password;
	public final UUID uuidChoice;
	
	public Administrator(String name, UUID uuidChoice) {
		this.name = name;
		this.uuidChoice = uuidChoice;
	}
	
	public Administrator(String name) {
		uuidChoice =  UUID.randomUUID();
		this.name = name;
	}
	
	public Administrator(String name, String password) {
		uuidChoice =  UUID.randomUUID();
		this.name = name;
		this.password = password;
	}
	
	public Administrator(String name, String password,  UUID uuidChoice) {
		this.uuidChoice = uuidChoice;
		this.name = name;
		this.password = password;
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
