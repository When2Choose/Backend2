package edu.wpi.cs.cs3733io.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

public class Choice {
	public final UUID uuid;
	public final String uuidString;
	public final int memberCount;
	public final String description;

	public final String[] alternativeNames;
	LinkedList<Alternative> alternatives;

	boolean isCompleted;
	String dateCreated;
	String dateCompleted;

	public String getUuidString() {
		return uuidString;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public String getDescription() {
		return description;
	}

	public LinkedList<Alternative> getAlternatives() {
		return alternatives;
	}

	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean flag) {
		this.isCompleted = flag;
	}

	public String getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Choice.
	 *
	 * @param memberCount      Integer.
	 * @param description      String.
	 * @param alternativeNames Array.
	 */
	public Choice(int memberCount, String description, String[] alternativeNames) {
		uuid = UUID.randomUUID();
		uuidString = uuid.toString();
		this.memberCount = memberCount;
		this.description = description;
		this.alternativeNames = alternativeNames;
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sDF.setTimeZone(TimeZone.getTimeZone("EST"));
		Date date = new Date();
		this.dateCreated = sDF.format(date);
		this.dateCompleted = "Not Complete";
		this.isCompleted = false;
		alternatives = new LinkedList<>();
	}

	/**
	 * Choice.
	 * 
	 * @param uuidString       String.
	 * @param memberCount      Integer.
	 * @param description      String.
	 * @param dateCompleted    String.
	 * @param dateCreated      String.
	 * @param isCompleted      Boolean.
	 * @param alternativeNames Array.
	 */
	public Choice(String uuidString, int memberCount, String description, String dateCompleted, String dateCreated,
			boolean isCompleted, String[] alternativeNames) {
		this.uuidString = uuidString;
		uuid = UUID.fromString(uuidString);
		this.memberCount = memberCount;
		this.description = description;
		this.alternativeNames = alternativeNames;
		this.dateCreated = dateCreated;
		this.dateCompleted = dateCompleted;
		this.isCompleted = isCompleted;
	}

	/**
	 * Converts a choice to a string without alternatives.
	 * 
	 */
	public String toString(Alternative alternative) {

		return "{" + "\"ID\" : \"" + uuidString + "\"," + "\"MemberCount\" :" + memberCount + "," + "\"DateCreated\" :"
				+ "\"" + dateCreated + "\"," + "\"IsCompleted\" :" + "\"" + Boolean.toString(true) + "\","
				+ "\"DateCompleted\" :" + "\"" + dateCompleted + "\"," + "\"Alternative\" :" + alternative.toJSON()
				+ "," + "\"Description\" :\"" + description + "\"}";

	}

	/**
	 * Converts a choice to a string including all fields.
	 */
	public String toString(LinkedList<Alternative> alternatives) {

		String alts = "[";
		for (Alternative alternative : alternatives) {
			if (alts.equals("[")) {
				alts = alts + alternative.toJSON();
			} else {
				alts = alts + ", " + alternative.toJSON();
			}
		}
		alts = alts + "]";

		return "{" + "\"ID\" : \"" + uuidString + "\"," + "\"MemberCount\" :" + memberCount + ","
				+ " \"Alternatives\" :" + alts + "," + "\"DateCompleted\" :" + "\"" + dateCompleted + "\","
				+ "\"Description\" :\"" + description + "\"}";

	}

	/**
	 * Converts Choice to JSON String Format.
	 * 
	 * @return String in JSON Format
	 */
	String toJSON() {
		return String.format("{\"ID\": \"%s\", \"DateCreated\": \"%s\", \"isCompleted\": %s, \"description\": \"%s\"}",
				uuidString, dateCreated, isCompleted, description);
	}

	/**
	 * Converts a Choice to a String for Generating Administrator Report.
	 * 
	 * @param choices List<Choice>
	 * @return Returns a String .
	 */
	public static String toStringForGeneratingReport(List<Choice> choices) {
		String choicesJSON = "[";
		for (Choice choice : choices) {
			if (choicesJSON.equals("[")) {
				choicesJSON = choicesJSON + choice.toJSON();
			} else {
				choicesJSON = choicesJSON + ", " + choice.toJSON();
			}
		}
		choicesJSON += "]";

		return "{" + " \"Choices\" :" + choicesJSON + "}";
	}

	/**
	 * Creates a list of alternatives.
	 */
	public void createAlternatives() {
		for (int i = 0; i < 5; i++) {
			alternatives.add(new Alternative(alternativeNames[i], i, this.uuidString));
		}
	}

	/**
	 * Complete the choice.
	 *
	 * @param date
	 */
	public void competed(String date) {
		dateCompleted = date;
		isCompleted = true;
	}
}
