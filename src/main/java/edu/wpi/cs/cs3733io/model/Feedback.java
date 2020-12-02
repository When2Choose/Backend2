package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;

public class Feedback {
	public final String userName;
	public final String description;
	public final String uuidChoice;
	public final int alternativeIndex;

	public Feedback(String userName, String description, String uuidChoice, int uuidAlternative) {
		this.userName = userName;
		this.description = description;
		this.uuidChoice = uuidChoice;
		this.alternativeIndex = uuidAlternative;
	}

	public String getUserName() {
		return userName;
	}

	public String getDescription() {
		return description;
	}

	public String getUuidChoice() {
		return uuidChoice;
	}

	public int getAlternativeIndex() {
		return alternativeIndex;
	}

	public String toString() {
		return "{" + "\"username: \"" + "\"" + userName + "\"" + "\"decsription: \"" + "\"" + description + "\""
				+ "\"uuidChoice: \"" + "\"" + uuidChoice + "\"" + "\"uuidAlternative: \"" + "\""
				+ Integer.toString(alternativeIndex) + "\"" + "}";
	}

	public String toString(LinkedList<Feedback> allFeedback) {

		return "{" + "\"Feedback\" : " + feedbackJSON(allFeedback) + "," + "\"ChoiceId\"  : " + "\"" + uuidChoice
				+ "\"," + "\"AlternativeIndex\" : " + "\"" + Integer.toString(alternativeIndex) + "\"}";

	}

	public String feedbackJSON(LinkedList<Feedback> allFeedback) {
		String feedback = "[";
		for (Feedback a : allFeedback) {
			if (feedback.equals("[")) {
				feedback = feedback + "[\"User\":" + "\"" + a.getUserName() + "\"," + "\"description\":" + "\""
						+ a.getDescription() + "\"]";
			} else {
				feedback = feedback + ", " + "[\"User\":" + "\"" + a.getUserName() + "\"," + "\"description\":" + "\""
						+ a.getDescription() + "\" ]";
			}
		}
		feedback = feedback + "]";
		return feedback;
	}

}
