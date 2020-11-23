package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

public class Alternative {

	public final UUID alternativeUUID;
	public final String choiceUUID;
	public final String name;
	public boolean isChosen;
	LinkedList<String> approvers;
	LinkedList<String> disapprovers;

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

}
