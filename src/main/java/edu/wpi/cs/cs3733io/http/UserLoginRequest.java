package edu.wpi.cs.cs3733io.http;

public class UserLoginRequest {
	String name; // User name
	String choiceId; // Choice UUID
	String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * User Login Request
	 * 
	 * @param name     String.
	 * @param choiceId String.
	 * @param password String.
	 */
	public UserLoginRequest(String name, String choiceId, String password) {
		this.name = name;
		this.choiceId = choiceId;
		this.password = password;
	}

	/**
	 * Converts Login User Request to JSON String Format.
	 */
	public String toString() {
		return " User is: (name: " + name + ") (choiceId: " + choiceId + ") (password:" + password + ") ";
	}

	public UserLoginRequest() {
	}
}
