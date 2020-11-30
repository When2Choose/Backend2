package edu.wpi.cs.cs3733io.model;

import java.util.LinkedList;

public class Approve {

	LinkedList<User> approvers;
	public final Alternative alternative;
	int numberApprovers;

	public LinkedList<User> getApprovers() {
		return approvers;
	}

	public void setApprovers(LinkedList<User> a) {
		this.approvers = a;
	}

	public int getNumberApprovers() {
		return numberApprovers;
	}

	public void setNumberApprovers(int numberApprovers) {
		this.numberApprovers = numberApprovers;
	}

	public Approve(Alternative alternative) {
		this.alternative = alternative;
	}

}
