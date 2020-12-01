package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

import com.google.gson.*;

public class Alternative {

	public final UUID alternativeUUID;
	public final String choiceUUID;
	public final String name;
	public boolean isChosen;
	public final int index;
	LinkedList<String> approvers;
	LinkedList<String> disapprovers;

	public Alternative(String alternativeUUID, String choiceUUID, int index, String name, boolean isChosen) {
		this.alternativeUUID = UUID.fromString(alternativeUUID);
		this.choiceUUID = choiceUUID;
		this.index = index;
		this.name = name;
		this.isChosen = isChosen;
	}

	//@formatter:off 
	public LinkedList<String> getApprovers() { return approvers; }
	public void setApprovers(LinkedList<String> approvers) { this.approvers = approvers; } 
	
	public LinkedList<String> getDisapprovers() { return disapprovers; }
	public void setDisapprovers(LinkedList<String> disapprovers) { this.approvers = disapprovers; } 
	//@formatter:on 

	public Alternative(String name, int index, String choiceUuid) {
		this.alternativeUUID = UUID.randomUUID();
		this.choiceUUID = choiceUuid;
		this.index = index;
		this.name = name;
		this.isChosen = false;
	}

	String toJSON() {
		return String.format(
				"{\"alternativeUUID\": \"%s\", \"choiceUUID\": \"%s\", \"index\": %d, \"description\": \"%s\", \"Approvers\": \"%s\", \"Disapprovers\": \"%s\" ,\"isChosen\": %d}",
				alternativeUUID, choiceUUID, index, name, approverJSON(), dispproverJSON(),isChosen ? 0 : 1);
	}

	public void setApproverNames(LinkedList<Approver> approvers) {
		for (Approver a : approvers) {
			this.approvers.add(a.getUserName());
		}
	}

	public void setDisapproverNames(LinkedList<Disapprover> disapprovers) {
		for (Disapprover d : disapprovers) {
			this.disapprovers.add(d.getUserName());
		}
	}

	private String approverJSON() {
		if (approvers == null || approvers.isEmpty()) {
			return "";
		}

		String approverJSON = "[";
		for (String a : this.approvers) {
			if (approverJSON.equals("[")) {
				approverJSON = approverJSON + "\"" + a + "\"";
			} else {
				approverJSON = approverJSON + ", " + "\"" + a + "\"";
			}
		}
		approverJSON = approverJSON + "]";
		return approverJSON;
	}

	private String dispproverJSON() {
		if (disapprovers == null || disapprovers.isEmpty()) {
			return "";
		}

		String disapproverJSON = "[";
		for (String d : this.disapprovers) {
			if (disapproverJSON.equals("[")) {
				disapproverJSON = disapproverJSON + "\"" + d + "\"";
			} else {
				disapproverJSON = disapproverJSON + ", " + "\"" + d + "\"";
			}
		}
		disapproverJSON = disapproverJSON + "]";
		return disapproverJSON;
	}
}
