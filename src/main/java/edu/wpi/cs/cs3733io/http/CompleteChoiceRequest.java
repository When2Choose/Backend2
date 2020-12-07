package edu.wpi.cs.cs3733io.http;

import java.util.Date;

public class CompleteChoiceRequest {

	public final String dateString; // Create Date
	public final Date date; // Creation Date
	String choiceId; // Choice UUID
	int alternativeIndex; // Alternative Index

	public String getDateString() {
		return dateString;
	}

	public Date getDate() {
		return date;
	}

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

	public int getAlternativeInex() {
		return alternativeIndex;
	}

	public void setAlternativeIndex(int a) {
		this.alternativeIndex = a;
	}

	/**
	 * Complete Choice Request, Sets date and dateString.
	 */
	public CompleteChoiceRequest() {
		date = new Date();
		dateString = date.toString();
	}

	/**
	 * Complete Choice Request, Sets date and dateString.
	 * 
	 * @param choiceId         String.
	 * @param alternativeIndex Integer.
	 */
	public CompleteChoiceRequest(String choiceId, int alternativeIndex) {
		this.choiceId = choiceId;
		this.alternativeIndex = alternativeIndex;
		date = new Date();
		dateString = date.toString();
	}

}
