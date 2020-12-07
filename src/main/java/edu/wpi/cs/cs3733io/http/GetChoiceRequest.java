package edu.wpi.cs.cs3733io.http;

public class GetChoiceRequest {
	String uuidString;

	public String getUuidString() {
		return uuidString;
	}

	public void setUuidString(String uuidString) {
		this.uuidString = uuidString;
	}

	/**
	 * Converts Get Choice Request to JSON String Format.
	 */
	public String toString() {
		return uuidString;
	}

	/**
	 * Generate Choice Request
	 * 
	 * @param uuidString String.
	 */
	public GetChoiceRequest(String uuidString) {
		this.uuidString = uuidString;
	}

	public GetChoiceRequest() {
	}

}
