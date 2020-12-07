package edu.wpi.cs.cs3733io.http;

public class AllResponse {

	public String response;
	public int statusCode;
	public String error;

	public AllResponse(String value, int statusCode) {
		this.response = "" + value;
		this.statusCode = statusCode;
		this.error = "";
	}

	public AllResponse(int statusCode, String errorMessage) {
		this.response = "";
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
}
