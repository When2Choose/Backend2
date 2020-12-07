package edu.wpi.cs.cs3733io.model;

import java.util.UUID;

public class Administrator {
	public final String name;
	public final String password;
	public final UUID uuidChoice;

	/**
	 * Administrator.
	 * 
	 * @param name     String.
	 * @param password Password.
	 */
	public Administrator(String name, String password) {
		uuidChoice = UUID.randomUUID();
		this.name = name;
		this.password = password;
	}

	/**
	 * Administrator.
	 * 
	 * @param name       String.
	 * @param password   String.
	 * @param uuidChoice UUID.
	 */

	public Administrator(String name, String password, UUID uuidChoice) {
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

	/**
	 * Delete all choice
	 * 
	 * @param nDaysOld Double.
	 * @return Returns false;
	 */
	public boolean requestDeleteAllChoices(double nDaysOld) {
		return false;
	}
}
