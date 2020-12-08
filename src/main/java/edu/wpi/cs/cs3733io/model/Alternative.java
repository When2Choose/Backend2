package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;
import java.util.UUID;

import com.google.gson.*;

public class Alternative {

	public final UUID alternativeUUID;
	public final String choiceUUID;
	public final String name;
	boolean isChosen;
	public final int index;
	LinkedList<String> approvers;
	LinkedList<String> disapprovers;
	LinkedList<Feedback> feedback;

	public Alternative(String alternativeUUID, String choiceUUID, int index, String name, boolean isChosen) {
		this.alternativeUUID = UUID.fromString(alternativeUUID);
		this.choiceUUID = choiceUUID;
		this.index = index;
		this.name = name;
		this.isChosen = isChosen;
		approvers = new LinkedList<String>();
		disapprovers = new LinkedList<String>();
		feedback = new LinkedList<Feedback>();
	}

	public LinkedList<String> getApprovers() {
		return approvers;
	}

	public void setApprovers(LinkedList<String> approvers) {
		this.approvers = approvers;
	}

	public LinkedList<String> getDisapprovers() {
		return disapprovers;
	}

	public void setDisapprovers(LinkedList<String> disapprovers) {
		this.disapprovers = disapprovers;
	}

	public boolean getIsChosen() {
		return isChosen;
	}

	public void setIsChosen(boolean isChosen) {
		this.isChosen = isChosen;
	}

	/**
	 * Alternative.
	 * 
	 * @param name       String.
	 * @param index      Integer.
	 * @param choiceUuid String.
	 */
	public Alternative(String name, int index, String choiceUuid) {
		this.alternativeUUID = UUID.randomUUID();
		this.choiceUUID = choiceUuid;
		this.index = index;
		this.name = name;
		this.isChosen = false;
		approvers = new LinkedList<String>();
		disapprovers = new LinkedList<String>();
		feedback = new LinkedList<Feedback>();
	}

	/**
	 * Converts Approvers to JSON String Format.
	 */
	String toJSON() {
		return String.format(
				"{\"alternativeUUID\": \"%s\", \"choiceUUID\": \"%s\", \"index\": %d, \"description\": \"%s\", "
						+ "\"Approvers\": %s, \"Disapprovers\": %s ,\"isChosen\": %d, \"Feedback\":"
						+ Feedback.feedbackJSON(this.feedback) + "}",
				alternativeUUID, choiceUUID, index, name, approverJSON(), dispproverJSON(), isChosen ? 1 : 0);
	}

	/**
	 * Sets the Approvers name List.
	 * 
	 * @param approvers LinkedList<Approver>
	 */
	public void setApproverNames(LinkedList<Approver> approvers) {
		for (Approver a : approvers) {
			this.approvers.add(a.getUserName());
		}
	}

	/**
	 * Sets the Disapprover name List.
	 * 
	 * @param disapprovers LinkedList<Disapprover>
	 */
	public void setDisapproverNames(LinkedList<Disapprover> disapprovers) {
		for (Disapprover d : disapprovers) {
			this.disapprovers.add(d.getUserName());
		}
	}

	/**
	 * Converts Approvers to JSON String Format.
	 */
	private String approverJSON() {
		if (approvers == null || approvers.isEmpty()) {
			return "[]";
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

	/**
	 * Converts Disapprovers to JSON String Format.
	 */
	private String dispproverJSON() {
		if (disapprovers == null || disapprovers.isEmpty()) {
			return "[]";
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

	/**
	 * Sets the Feedback List.
	 * 
	 * @param alternativeFeedback LinkedList<Feedback>
	 */
	public void setFeedback(LinkedList<Feedback> alternativeFeedback) {
		for (Feedback f : alternativeFeedback) {
			this.feedback.add(f);
		}
	}


	/**
	 * Converts Alternative with List of Feedback to JSON String Format.
	 * 
	 * @param allFeedback LinkedList<Feedback>
	 */
	public String feedbackJSON(LinkedList<Feedback> allFeedback) {
		if (allFeedback == null || allFeedback.isEmpty()) {
			return "[]";
		}
		String feedback = "[";
		for (Feedback a : allFeedback) {
			if (feedback.equals("[")) {
				feedback = feedback + "{\"User\":" + "\"" + a.getUserName() + "\"," + "\"description\":" + "\""
						+ a.getDescription() + "\"}";
			} else {
				feedback = feedback + ", " + "{\"User\":" + "\"" + a.getUserName() + "\"," + "\"description\":" + "\""
						+ a.getDescription() + "\" }";
			}
		}
		feedback = feedback + "]";
		return feedback;
	}
}
