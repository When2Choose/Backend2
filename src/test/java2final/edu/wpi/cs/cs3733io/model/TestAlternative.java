package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;

public class TestAlternative {
	
	Alternative alt1, alt2;
	
	@Before
	public void createAlternatives() {
		alt1 = new Alternative("03a6902e-3b28-46c8-a30f-5018994a67a1", "03a6902e-3b28-46c8-a30f-5018994a67a0", 1, "alt1", false);
		alt2 = new Alternative("alt2", 2, "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testIsChosen()  {
		assertFalse(alt1.getIsChosen());
		alt1.setIsChosen(true);
		assertTrue(alt1.getIsChosen());
	}
	
	@Test
	public void testFeedbackJSON() {
		LinkedList<Feedback> feedback = new LinkedList<>();
		assertTrue(alt1.feedbackJSON(feedback) instanceof String);
		Feedback f = new Feedback("user1", "description", "03a6902e-3b28-46c8-a30f-5018994a67a0", 1);
		feedback.add(f);
		assertTrue(alt1.feedbackJSON(feedback) instanceof String);
		alt1.setFeedback(feedback);
		assertTrue(alt1.feedbackJSON(feedback) instanceof String);
	}
	
	@Test
	public void testToJSON() {
		assertTrue(alt1.toJSON() instanceof String);
		LinkedList<Approver> approvers = new LinkedList<>();
		LinkedList<Disapprover> disapprovers = new LinkedList<>();
		Approver approver1 = new Approver(1, "user1", "03a6902e-3b28-46c8-a30f-5018994a67a0");
		Approver approver2= new Approver(2,"user2","03a6902e-3b28-46c8-a30f-5018994a67a0");
		Disapprover disapprover1 = new Disapprover(1, "user3", "03a6902e-3b28-46c8-a30f-5018994a67a0");
		Disapprover disapprover2= new Disapprover(2,"user4", "03a6902e-3b28-46c8-a30f-5018994a67a0");
		approvers.add(approver1);
		approvers.add(approver2);
		disapprovers.add(disapprover1);
		disapprovers.add(disapprover2);
		alt1.setApproverNames(approvers);
		alt1.setDisapproverNames(disapprovers);
		assertTrue(alt1.toJSON() instanceof String);
	}
	
	@Test
	public void testApprovers() {
		LinkedList<Approver> approvers = new LinkedList<>();
		LinkedList<Disapprover> disapprovers = new LinkedList<>();
		LinkedList<String> approverNames = new LinkedList<>();
		LinkedList<String> disapproverNames = new LinkedList<>();
		Approver approver1 = new Approver(1, "user1", "03a6902e-3b28-46c8-a30f-5018994a67a0");
		Approver approver2= new Approver(2,"user2","03a6902e-3b28-46c8-a30f-5018994a67a0");
		Disapprover disapprover1 = new Disapprover(1, "user3", "03a6902e-3b28-46c8-a30f-5018994a67a0");
		Disapprover disapprover2= new Disapprover(2,"user4", "03a6902e-3b28-46c8-a30f-5018994a67a0");
		approvers.add(approver1);
		approvers.add(approver2);
		disapprovers.add(disapprover1);
		disapprovers.add(disapprover2);
		alt1.setApproverNames(approvers);
		alt1.setDisapproverNames(disapprovers);
		disapproverNames.add("user1");
		disapproverNames.add("user2");
		approverNames.add("user3");
		approverNames.add("user4");
		alt1.setApprovers(approverNames);
		alt1.setDisapprovers(disapproverNames);
		assertEquals(alt1.getApprovers(), approverNames);
		assertEquals(alt1.getDisapprovers(), disapproverNames);
	}
	
}
