package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

public class Choice {
	public final UUID uuid;
	String uuidString;
	public final int memberCount;
	public final String description;
	public final LinkedList<String> alternativeNames;
	LinkedList<Alternative> alternatives;
	boolean isCompleted;
	String dateCompleted;

	//@formatter:off 
	public String getUuidString() { return uuidString; }
	public void getUuidString(String uuidString) { this.uuidString = uuidString; }
	
	public int getMemberCount() { return memberCount; }
	
	public String getDescription() { return description; }
	
	public LinkedList<String> getAlternatives() { return alternativeNames; }
	
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
		uuid = UUID.randomUUID();
		uuidString = uuid.toString();
		this.memberCount = memberCount;
		this.description = description;
		this.alternativeNames = alternativeNames;
		this.dateCompleted = "Not Complete";
		this.isCompleted = false;
	}

	public Choice(String uuidString, int memberCount, String description, String dateCompleted, boolean isCompleted,
			LinkedList<String> alternativeNames) {
		this.uuidString = uuidString;
		uuid = UUID.fromString(uuidString);
		this.memberCount = memberCount;
		this.description = description;
		this.alternativeNames = alternativeNames;
		this.dateCompleted = dateCompleted;
		this.isCompleted = isCompleted;
	}

	/**
	 * Converts a choice to a string including all fields.
	 */
	public String toString() {

		return "{" + "\"ID\" : \"" + uuid.toString() + "\"," + "\"Member Count\" :" + "\""
				+ Integer.toString(memberCount) + "\"," + " \"Alternatives\" :" + "\"" + alternativeNames + "\","
				+ "\"DateCompleted\" :" + "\"" + dateCompleted + "\"," + "\"Description\" :" + "\"" + description
				+ "\"}";
	}

	/**
	 * Creates a list of alternatives that correspond to
	 */
	public void createAlternatives() {
		int index = 0;
		for (String name : alternativeNames) {
			alternatives.addLast(new Alternative(name, index));
			index++;
		}
	}

	/**
	 * Complete the choice.
	 * 
	 * @param date
	 */
	public void competed(String date) {
		dateCompleted = date;
		isCompleted = true;
	}
}
