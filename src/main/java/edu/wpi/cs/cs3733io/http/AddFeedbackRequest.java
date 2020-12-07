package edu.wpi.cs.cs3733io.http;

public class AddFeedbackRequest {

	String user;
	String feedbackText;
	String choiceId;
	int alternativeIndex;

	/**
	 * Add Feedback Request.
	 * 
	 * @param user             String.
	 * @param feedbackText     String.
	 * @param choiceId         String.
	 * @param alternativeIndex Integer.
	 */
	public AddFeedbackRequest(String user, String feedbackText, String choiceId, int alternativeIndex) {
		this.user = user;
		this.feedbackText = feedbackText;
		this.choiceId = choiceId;
		this.alternativeIndex = alternativeIndex;
	}

	public AddFeedbackRequest() {

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

	public int getAlternativeIndex() {
		return alternativeIndex;
	}

	public void setAlternativeIndex(int alternativeIndex) {
		this.alternativeIndex = alternativeIndex;
	}

	/**
	 * Converts Feedback to JSON String Format.
	 */
	public String toString() {
		return "{" + "\"username: \"" + "\"" + user + "\"" + "\"decsription: \"" + "\"" + feedbackText + "\""
				+ "\"uuidChoice: \"" + "\"" + choiceId + "\"" + "\"uuidAlternative: \"" + "\""
				+ Integer.toString(alternativeIndex) + "\"" + "}";
	}

}
