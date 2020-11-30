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

	public Disapprover(int alternativeIndex, String choiceId, String userName) {
		this.alternativeIndex = alternativeIndex;
		this.choiceUuid = choiceId;
		this.userName = userName;
	}

	public String toString() {

		return "{" + "\"Username\" : " + "\"" + userName + "\"" + "\"ChoiceId\"  : " + "\"" + choiceUuid + "\""
				+ "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + "\"" + "}";

	}

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

		return "{" + "\"Disapprovers\" : " + "\"" + disapprovers + ",\"" + "\"ChoiceId\"  : " + "\"" + choiceUuid + ",\""
				+ "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + ",\"" + "\"NumberOfDisapprovers\"  : "
				+ "\"" + Integer.toString(disapproverNames.size()) + "\"" + "}";

	}
}
