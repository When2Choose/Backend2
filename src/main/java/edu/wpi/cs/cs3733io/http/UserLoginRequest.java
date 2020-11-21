package edu.wpi.cs.cs3733io.http;

public class UserLoginRequest {
	String name;
	String choiceId;
	

	//@formatter:off 
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getChoiceId() { return choiceId; }
	
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}	

	//@formatter:on

	public String toString() {
		return " Creating Choice with (name: " + name + ") (choiceId: " + choiceId + ") ";
	}

	public UserLoginRequest(String name, String uuidChoiceString) {
		this.name = name;
		this.choiceId = uuidChoiceString;
	}

	public UserLoginRequest() {
	}
}
