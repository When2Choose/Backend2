package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

public class Alternative {

	public final UUID uuid;
	String choiceUuid;
	public final String name;
	LinkedList<String> approvers;
	LinkedList<String> disapprovers;

	//@formatter:off 
	public LinkedList<String> getApprovers() { return approvers; }
	public void setApprovers(LinkedList<String> approvers) { this.approvers = approvers; } 
	
	public LinkedList<String> getDisapprovers() { return disapprovers; }
	public void setDisapprovers(LinkedList<String> disapprovers) { this.approvers = disapprovers; } 
	//@formatter:on 

	public Alternative(String name, String choiceUuid) {
		uuid = UUID.randomUUID();
		this.choiceUuid = choiceUuid;
		this.name = name;
	}

}
