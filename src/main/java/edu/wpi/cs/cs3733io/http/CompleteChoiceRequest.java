package edu.wpi.cs.cs3733io.http;

import java.util.Date;

public class CompleteChoiceRequest {

	public final String dateString;
	public final Date date;
	String choiceId;
	int alternativeIndex;

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

	public CompleteChoiceRequest() {
		date = new Date();
		dateString = date.toString();
	}

	public CompleteChoiceRequest(String choiceId, int alternativeIndex) {
		this.choiceId = choiceId;
		this.alternativeIndex = alternativeIndex;
		date = new Date();
		dateString = date.toString();
	}

}
