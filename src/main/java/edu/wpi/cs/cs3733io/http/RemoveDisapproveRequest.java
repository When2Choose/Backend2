package edu.wpi.cs.cs3733io.http;

public class RemoveDisapproveRequest {

	String choiceId; // Choice UUID
	String user;
	int alternative; // Alternative Index

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getAlternative() {
		return alternative;
	}

	public void setAlternative(int alternativeIndex) {
		this.alternative = alternativeIndex;
	}

	public RemoveDisapproveRequest() {

	}

	/**
	 * Remove Disapprove Request.
	 * 
	 * @param choiceId    String.
	 * @param user        String.
	 * @param alternative Alternative.
	 */
	public RemoveDisapproveRequest(String choiceId, String user, int alternative) {
		this.choiceId = choiceId;
		this.alternative = alternative;
		this.user = user;
	}

	/**
	 * Converts Remove Disapprove Request to JSON String Format.
	 */
	public String toString() {
		return choiceId + " " + user + " " + Integer.toString(alternative);
	}
}