package edu.wpi.cs.cs3733io.model;


public class Administrator {
	public final String name;
	public final String password;
	
	public Administrator(String name, String password) {
		this.name = name;		
		this.password =password;

	}


	public String getName() {
		return name;
	}


	public String getPassword() {
		return password;
	}


	public boolean requestDeleteAllChoices(int nDaysOld) {
		return false;
	}
}
