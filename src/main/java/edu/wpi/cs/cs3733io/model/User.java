package edu.wpi.cs.cs3733io.model;

public class User {
	public final String name;
	String password;
	public final String choiceId;

	public User(String name, String password, String choiceId) {
		this.choiceId = choiceId;
		this.name = name;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChoiceId() {
		return choiceId;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "{ \"ID\": " + "\"" + choiceId + "\"," + "\"name\": \"" + name + "\"," + "\"password\": \"" + password
				+ "\"" + "}";
	}

	public String toString(boolean isComplete) {

		return "{ \"ID\": " + "\"" + choiceId + "\"," + "\"name\": \"" + name + "\"," + "\"password\": \"" + password
				+ "\"," + "\"complete\": " + isComplete + "}";

	}

}
