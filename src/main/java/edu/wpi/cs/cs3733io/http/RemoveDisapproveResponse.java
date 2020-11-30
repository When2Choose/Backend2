package edu.wpi.cs.cs3733io.http;

public class RemoveDisapproveResponse {

	public String response;
	public int statusCode; // HTTP status code.
	public String error;

	public RemoveDisapproveResponse (String value, int statusCode) {
			this.response = "" + value; // doesn't matter since error
			this.statusCode = statusCode;
			this.error = "";
		}

	public RemoveDisapproveResponse (int statusCode, String errorMessage) {
			this.response = ""; // doesn't matter since error
			this.statusCode = statusCode;
			this.error = errorMessage;
		}
}
