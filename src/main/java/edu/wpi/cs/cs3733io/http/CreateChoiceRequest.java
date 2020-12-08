package edu.wpi.cs.cs3733io.http;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreateChoiceRequest {
	int memberCount;
	String description;

	String[] alternatives;

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int mcnt) {
		this.memberCount = mcnt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(String[] alternatives) {
		this.alternatives = alternatives;
	}

	/**
	 * Converts Choice to JSON String Format.
	 */
	public String toString() {
		return "Creating Choice with (Member Count: " + memberCount + ") (Description: " + description
				+ "Alternatives: ";
	}

	/**
	 * Create Choice Request.
	 * 
	 * @param memberCount  Integer.
	 * @param description  String.
	 * @param alternatives Array.
	 */
	public CreateChoiceRequest(int memberCount, String description, String[] alternatives) {
		this.memberCount = memberCount;
		this.description = description;
		this.alternatives = alternatives;
	}

	public CreateChoiceRequest() {
	}

}
