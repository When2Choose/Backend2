package edu.wpi.cs.cs3733io.demo.http;

public class CreateChoiceResponse {

	public String response;
	public int statusCode;  // HTTP status code.
	public String error;
	
	public CreateChoiceResponse (String value, int statusCode) {
		this.response = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public CreateChoiceResponse (int statusCode, String errorMessage) {
		this.response = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
}
