package edu.wpi.cs.cs3733io.http;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreateChoiceRequest {
	int memberCount;
	String description;
	String[] alternativeNames;
	

	//@formatter:off 
	public int getMemberCount() { return memberCount; }
	public void setMemberCount(int mcnt) { this.memberCount = mcnt; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public String[] getAlternativeNames() { return alternativeNames; }
	public void setAlternativeNames(String[] alternatives) { this.alternativeNames = alternatives; } 
	//@formatter:on

	public String toString() {
		return "Creating Choice with (Member Count: " + Integer.toString(memberCount) + ") (Description: " + description
				+ "Alternatives: ";
	}

	public CreateChoiceRequest(int memberCount, String description, String[] alternatives) {
		this.memberCount = memberCount;
		this.description = description;
		this.alternativeNames = alternatives;
	}

	public CreateChoiceRequest() {
	}

}
