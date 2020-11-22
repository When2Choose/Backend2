package edu.wpi.cs.cs3733io.http;

import java.util.LinkedList;
import java.util.List;

public class CreateChoiceRequest {
	int memberCount;
	String description;
	String[] alternatives;
	

	//@formatter:off 
	public int getMemberCount() { return memberCount; }
	public void setMemberCount(int mcnt) { this.memberCount = mcnt; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public String[] getAlternatives() { return alternatives; }
	public void setAlternatives(String[] alternatives) { this.alternatives = alternatives; } 
	//@formatter:on

	public String toString() {
		return "Creating Choice with (Member Count: " + Integer.toString(memberCount) + ") (Description: " + description
				+ "Alternatives: ";
	}

	public CreateChoiceRequest(int memberCount, String description, String[] alternatives) {
		this.memberCount = memberCount;
		this.description = description;
		this.alternatives = alternatives;
	}

	public CreateChoiceRequest() {
	}

}
