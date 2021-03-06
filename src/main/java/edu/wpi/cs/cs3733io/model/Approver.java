package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;

public class Approver {

	String userName;
	public final String choiceUuid;
	public final int alternativeIndex;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getChoiceUuid() {
		return choiceUuid;
	}

	public int getAlternativeIndex() {
		return alternativeIndex;
	}

	/**
	 * Approver.
	 * 
	 * @param alternativeIndex Integer.
	 * @param choiceId         String.
	 * @param userName         String.
	 */
	public Approver(int alternativeIndex, String choiceId, String userName) {
		this.alternativeIndex = alternativeIndex;
		this.choiceUuid = choiceId;
		this.userName = userName;
	}

	/**
	 * Converts Approver to JSON String Format.
	 */
	public String toString() {

		return "{" + "\"Username\" : " + "\"" + userName + "\"" + "\"ChoiceId\"  : " + "\"" + choiceUuid + "\""
				+ "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + "\"" + "}";

	}

	/**
	 * Converts Approver with List of names to JSON String Format.
	 * 
	 * @param approverNames LinkedList<Approver>.
	 */
	public String toString(LinkedList<Approver> approverNames) {

		String approvers = "[";
		for (Approver a : approverNames) {
			if (approvers.equals("[")) {
				approvers = approvers + "\"" + a.getUserName() + "\"";
			} else {
				approvers = approvers + ", " + "\"" + a.getUserName() + "\"";
			}
		}
		approvers = approvers + "]";

		return "{" + "\"Approvers\" : " + "\"" + approvers + ",\"" + "\"ChoiceId\"  : " + "\"" + choiceUuid + ",\""
				+ "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + ",\""
				+ "\"NumberOfApprovers\"  : " + "\"" + Integer.toString(approverNames.size()) + "\"" + "}";

	}
}
