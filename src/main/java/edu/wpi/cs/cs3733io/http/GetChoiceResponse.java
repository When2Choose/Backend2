package edu.wpi.cs.cs3733io.http;

public class GetChoiceResponse {

	public String response;
	public int statusCode;  // HTTP status code.
	public String error;
	
	public GetChoiceResponse(String response, int statusCode) {
		this.response = "" + response; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}

}
