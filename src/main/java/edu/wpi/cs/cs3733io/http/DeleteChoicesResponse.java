package edu.wpi.cs.cs3733io.http;

public class DeleteChoicesResponse {
	public String response;
	public int statusCode;  // HTTP status code.
	public String error;
	
	public DeleteChoicesResponse (String value, int statusCode) {
		this.response = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteChoicesResponse (int statusCode, String errorMessage) {
		this.response = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
}
