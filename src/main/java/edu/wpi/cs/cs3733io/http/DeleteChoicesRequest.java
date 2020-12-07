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

	/**
	 * Delete Choice Request.
	 * 
	 * @param days Double.
	 */
	public DeleteChoicesRequest(double days) {
		this.days = days;
	}

	/**
	 * @return Returns "Successful".
	 */
	public String successMessage() {
		return "Successful";
	}

}
