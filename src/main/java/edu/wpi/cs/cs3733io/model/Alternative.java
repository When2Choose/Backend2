package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

import com.google.gson.*;


public class Alternative {

	public final UUID alternativeUUID;
	public final String choiceUUID;
	public final String name;
	public boolean isChosen;
	LinkedList<String> approvers;
	LinkedList<String> disapprovers;

	public Alternative(String alternativeUUID, String choiceUUID, String name, boolean isChosen) {
		this.alternativeUUID = UUID.fromString(alternativeUUID);
		this.choiceUUID = choiceUUID;
		this.name = name;
		this.isChosen = isChosen;
	}

	//@formatter:off 
	public LinkedList<String> getApprovers() { return approvers; }
	public void setApprovers(LinkedList<String> approvers) { this.approvers = approvers; } 
	
	public LinkedList<String> getDisapprovers() { return disapprovers; }
	public void setDisapprovers(LinkedList<String> disapprovers) { this.approvers = disapprovers; } 
	//@formatter:on 

	public Alternative(String name, String choiceUuid) {
		this.alternativeUUID = UUID.randomUUID();
		this.choiceUUID = choiceUuid;
		this.name = name;
		this.isChosen = false;
	}

	String toJSON() {
		return String.format("{\"alternativeUUID\": \"%s\", \"choiceUUID\": \"%s\", \"description\": \"%s\", \"isChosen\": %d}", alternativeUUID, choiceUUID, name, isChosen ? 0: 1);
	}
}
