package edu.wpi.cs.cs3733io.http;

public class RemoveDisapproveRequest {

	String choiceId;
	String user;
	int alternative;

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

	public RemoveDisapproveRequest(String choiceId, String user, int alternative) {
		this.choiceId = choiceId;
		this.alternative = alternative;
		this.user = user;
	}

	public String toString() {
		return choiceId + " " + user + " " + Integer.toString(alternative);
	}
}