package edu.wpi.cs.cs3733io.http;

public class UserLoginRequest {

	String name;
	String password;
	

	//@formatter:off 
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	

	//@formatter:on

	public String toString() {
		return "Creating Choice with (name: " + name + ") (password: " + password
				+ "Alternatives: ";
	}

	public UserLoginRequest(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public UserLoginRequest() {
	}
	
}
