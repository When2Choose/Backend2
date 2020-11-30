package edu.wpi.cs.cs3733io.http;

public class ApproveAlternativeResponse {
	public String response;
	public int statusCode;  // HTTP status code.
	public String error;
	
	public ApproveAlternativeResponse (String value, int statusCode) {
		this.response = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ApproveAlternativeResponse (int statusCode, String errorMessage) {
		this.response = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
}
