package edu.wpi.cs.cs3733io.demo.http;

import java.util.List;

public class CreateChoiceRequest {
	int memberCount;
	String description;
	List<String> alternatives;

	//@formatter:off 
	public int getMemberCount() { return memberCount; }
	public void setmemberCount(int memberCount) { this.memberCount = memberCount; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public List<String> getAlternatives() { return alternatives; }
	public void setAlternatives(List<String> alternatives) { this.alternatives = alternatives; } 
	//@formatter:on

	public String toString() {
		return "Creating Choice with (Member Count: " + Integer.toString(memberCount) + ") (Description: " + description
				+ "Alternatives: ";
	}

	public CreateChoiceRequest(int memberCount, String description, List<String> alternatives) {
		this.memberCount = memberCount;
		this.description = description;
		this.alternatives = alternatives;
	}

	public CreateChoiceRequest() {
	}

}
