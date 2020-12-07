package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;

public class Disapprover {

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
	 * Disapprover.
	 * 
	 * @param alternativeIndex Integer.
	 * @param choiceId         String.
	 * @param userName         String.
	 */
	public Disapprover(int alternativeIndex, String choiceId, String userName) {
		this.alternativeIndex = alternativeIndex;
		this.choiceUuid = choiceId;
		this.userName = userName;
	}

	/**
	 * Converts Disapprover to JSON String Format.
	 */
	public String toString() {

		return "{" + "\"Username\" : " + "\"" + userName + "\"" + "\"ChoiceId\"  : " + "\"" + choiceUuid + "\""
				+ "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + "\"" + "}";

	}

	/**
	 * Converts Disapprover with List of names to JSON String Format.
	 * 
	 * @param disapproverNames LinkedList<Disapprover>.
	 */
	public String toString(LinkedList<Disapprover> disapproverNames) {

		String disapprovers = "[";
		for (Disapprover a : disapproverNames) {
			if (disapprovers.equals("[")) {
				disapprovers = disapprovers + "\"" + a.getUserName() + "\"";
			} else {
				disapprovers = disapprovers + ", " + "\"" + a.getUserName() + "\"";
			}
		}
		disapprovers = disapprovers + "]";

		return "{" + "\"Disapprovers\" : " + "\"" + disapprovers + ",\"" + "\"ChoiceId\"  : " + "\"" + choiceUuid
				+ ",\"" + "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + ",\""
				+ "\"NumberOfDisapprovers\"  : " + "\"" + Integer.toString(disapproverNames.size()) + "\"" + "}";

	}
}
