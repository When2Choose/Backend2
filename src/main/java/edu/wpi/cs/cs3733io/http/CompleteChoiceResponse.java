package edu.wpi.cs.cs3733io.http;

public class CompleteChoiceResponse {
	public String response;
	public int statusCode;  // HTTP status code.
	public String error;
	
	public CompleteChoiceResponse (String value, int statusCode) {
		this.response = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public CompleteChoiceResponse (int statusCode, String errorMessage) {
		this.response = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
}