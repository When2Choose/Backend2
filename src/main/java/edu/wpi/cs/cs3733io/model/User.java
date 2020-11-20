package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class User {
	public final String name;
	String password;
	public final UUID uuidChoice;
	
	
	public User(String name, UUID uuidChoice) {
		this.name = name;
		this.uuidChoice = uuidChoice;
	}
	
	public User(String name) {
		uuidChoice =  UUID.randomUUID();
		this.name = name;
	}
	
	public User(String name, String password) {
		uuidChoice =  UUID.randomUUID();
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String password,  UUID uuidChoice) {
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
	
	

}
