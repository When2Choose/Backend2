package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class Administrator {
	public final String name;
	public final String password;
	public final UUID uuidChoice;
	
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
	
	public String getPassword() {
		return password;
	}

	public boolean requestDeleteAllChoices(int nDaysOld) {
		return false;
	}
}
