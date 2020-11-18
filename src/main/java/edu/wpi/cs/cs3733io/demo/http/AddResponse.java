package edu.wpi.cs.cs3733io.demo.http;

public class AddResponse {
	public String result;
	public int statusCode;  // HTTP status code.
	public String error;
	
	
	public AddResponse (double value, int statusCode) {
		this.result = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
}
