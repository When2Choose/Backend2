package edu.wpi.cs.cs3733io.http;

public class DeleteChoicesRequest {
	double days;

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	public DeleteChoicesRequest() {

	}

	public DeleteChoicesRequest(double days) {
		this.days = days;
	}

	public String successMessage() {
		return "Successful";
	}

}
