package edu.wpi.cs.cs3733io.http;

public class DeleteChoicesRequest {
	int days;

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public DeleteChoicesRequest() {

	}

	public DeleteChoicesRequest(int days) {
		this.days = days;
	}

	public String successMessage() {
		return "Successful";
	}

}
