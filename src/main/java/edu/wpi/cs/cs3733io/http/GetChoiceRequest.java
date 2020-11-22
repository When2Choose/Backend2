package edu.wpi.cs.cs3733io.http;


public class GetChoiceRequest {
	String uuidString;
	
	public String getUuidString() { return uuidString; }
	public void setUuidString(String uuidString) { this.uuidString = uuidString; }
	
	public String toString() {
		return uuidString;
	}

	public GetChoiceRequest(String uuidString) {
		this.uuidString = uuidString;
	}

	public GetChoiceRequest() {
	}

}
