package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

public class Choice {
	public final UUID uuid = new UUID(64,64);
	public final int memberCount;
	public final String description;
	public final LinkedList<String> alternativeNames;
	LinkedList<Alternative> alternatives;
	boolean isCompleted;
	String dateCompleted;
	

	//@formatter:off 
//	public int getId() { return id; }
//	public void setId(int i) { this.id = i; }
//	
//	public int getMemberCount() { return memberCount; }
//	public void setmemberCount(int mcnt) { this.memberCount = mcnt; }
//	
//	public String getDescription() { return description; }
//	public void setDescription(String description) { this.description = description; }
//	
//	public LinkedList<String> getAlternatives() { return alternatives; }
//	public void setAlternatives(LinkedList<String> alternatives) { this.alternatives = alternatives; } 
	
	public boolean getIsCompleted() {return isCompleted; }
	public void setIsCompleted(boolean flag) { this.isCompleted = flag; }
	
	public String getDateCompleted() { return dateCompleted; }
	public void setDateCompleted(String dateCompleted) { this.dateCompleted = dateCompleted; }
	//@formatter:on 


	/**
	 * A Choice that people can access. 
	 * 
	 * @param memberCount
	 * @param description
	 * @param alternativeNames
	 */
	public Choice(int memberCount, String description, LinkedList<String> alternativeNames) {
		this.memberCount = memberCount;
		this.description = description;
		this.alternativeNames = alternativeNames;
		this.dateCompleted = "Not Complete";
		this.isCompleted = false;
	}

	/**
	 * Converts a choice to a string including all fields. 
	 */
	public String toString() {
		return "[ID:" + uuid.toString() + "] [Member Count:" + Integer.toString(memberCount) + "] [Alternatives:"
				+ alternativeNames +  "] [DateCompleted:" + dateCompleted + " Description:"
				+ description + "]";
	}
	
	/**
	 * Creates a list of alternatives that correspond to 
	 */
	public void createAlternatives() {
		int index = 0;
		for(String name : alternativeNames) {
			alternatives.addLast(new Alternative(name, index));
			index++;
		}
	}
}
