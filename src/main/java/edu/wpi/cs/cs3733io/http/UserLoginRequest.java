package edu.wpi.cs.cs3733io.http;

public class UserLoginRequest {
	String name;
	String choiceId;
	String password;

	

	//@formatter:off 
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getChoiceId() { return choiceId; }
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}	
		public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	//@formatter:on



	public String toString() {
		return " Creating Choice with (name: " + name + ") (choiceId: " + choiceId + ") ";
	}

	public UserLoginRequest(String name, String choiceId) {
		this.name = name;
		this.choiceId = choiceId;
	}

	public UserLoginRequest() {
	}
}
