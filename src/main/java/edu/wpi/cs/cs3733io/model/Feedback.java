package edu.wpi.cs.cs3733io.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;

public class Feedback {
	public final String userName;
	public final String description;
	public final String uuidChoice;
	public final int alternativeIndex;
	public final String dateCreated;

	/**
	 * Feedback.
	 * 
	 * @param userName        String.
	 * @param description     String.
	 * @param uuidChoice      String.
	 * @param uuidAlternative Integer.
	 */
	public Feedback(String userName, String description, String uuidChoice, int uuidAlternative) {
		this.userName = userName;
		this.description = description;
		this.uuidChoice = uuidChoice;
		this.alternativeIndex = uuidAlternative;
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sDF.setTimeZone(TimeZone.getTimeZone("EST"));
		Date date = new Date();
		this.dateCreated = sDF.format(date);
	}


	/**
	 * Feedback.
	 * 
	 * @param userName        String.
	 * @param description     String.
	 * @param uuidChoice      String.
	 * @param uuidAlternative Integer.
	 * @param date            String.
	 */
	public Feedback(String userName, String description, String uuidChoice, int uuidAlternative, String date) {
		this.userName = userName;
		this.description = description;
		this.uuidChoice = uuidChoice;
		this.alternativeIndex = uuidAlternative;
		this.dateCreated = date;
	}

	public String getDateCreated() {
		return dateCreated;
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

	/**
	 * Converts Feedback with List of Feedback to JSON String Format.
	 * 
	 */
	public String toString() {
		return "{" + "\"username\":" + "\"" + userName + "\"," + "\"description\":" + "\"" + description + "\","
				+ "\"uuidChoice\" :" + "\"" + uuidChoice + "\"," + "\"uuidAlternative\":" + "\"" + alternativeIndex
				+ "\", \"dateCreated\": \"" + dateCreated + "\"}";
	}

	/**
	 * Converts Feedback with List of Feedback to JSON String Format.
	 * 
	 * @param allFeedback LinkedList<Feedback>
	 * @return String of Feedback.
	 */
	public String toString(LinkedList<Feedback> allFeedback) {
		return "{" + "\"Feedback\" : " + feedbackJSON(allFeedback) + "," + "\"ChoiceId\"  : " + "\"" + uuidChoice
				+ "\"," + "\"AlternativeIndex\" : \"" + alternativeIndex + "\", \"dateCreated\": \"" + dateCreated
				+ "\"}";
	}

  /**
	 * Converts Feedback with List of Feedback to JSON Format.
	 * 
	 * @param allFeedback LinkedList<Feedback>
	 * @return String of feedbackJSON.
	 */
	public static String feedbackJSON(LinkedList<Feedback> allFeedback) {
		String json = "[";
		for (Feedback feedback : allFeedback) {
			if (json.equals("[")) {
				json = json + feedback.toString();
			} else {
				json = json + ", " + feedback.toString();
			}
		}
		json = json + "]";
		return json;
	}

}
