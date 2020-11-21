package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class User {
	public final String name;
	String password;
	public final UUID uuidChoice;
	String uuidChoiceString;

	
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
	
	public User(String name, String password,  String uuidChoiceString) {
		this.uuidChoiceString = uuidChoiceString;
		this.name = name;
		this.password = password;
		this.uuidChoice = UUID.randomUUID();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuidChoiceString() {
		return uuidChoiceString;
	}

	public void setUuidChoiceString(String uuidChoiceString) {
		this.uuidChoiceString = uuidChoiceString;
	}

	public String getName() {
		return name;
	}

	public UUID getUuidChoice() {
		return uuidChoice;
	} 
	
	public String toString() {
		return "[ID: " + uuidChoice.toString() + "] [name:" + name + "] [password:" + password + "]";
	}

}
