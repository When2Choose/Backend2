package edu.wpi.cs.cs3733io.http;

public class AllResponse {

	public String response;
	public int statusCode;
	public String error;

	/**
	 * Response for 200 codes.
	 * 
	 * @param value      String.
	 * @param statusCode Integer.
	 */
	public AllResponse(String value, int statusCode) {
		this.response = "" + value;
		this.statusCode = statusCode;
		this.error = "";
	}

	/**
	 * Response for 400 Codes.
	 * 
	 * @param statusCode   Integer.
	 * @param errorMessage String.
	 */
	public AllResponse(int statusCode, String errorMessage) {
		this.response = "";
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
}
