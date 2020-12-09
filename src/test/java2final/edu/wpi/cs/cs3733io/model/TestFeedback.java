package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;

public class TestFeedback {
	
	LinkedList<Feedback> feedbacks = new LinkedList<>();
	Feedback feedback1, feedback2, feedback3;
	
	@Before
	public void createFeedback() {
		feedback1 = new Feedback("user1", "description", "03a6902e-3b28-46c8-a30f-5018994a67a0", 1);
		feedback2 = new Feedback("user2", "description2", "03a6902e-3b28-46c8-a30f-5018994a67a0", 2);
		feedback3 = new Feedback("user3", "description3", "03a6902e-3b28-46c8-a30f-5018994a67a0", 2, "2020/12/06 13:54:22");
		feedbacks.add(feedback1);
		feedbacks.add(feedback2);
	}
	
	@Test
	public void testGetUuidChoice() {
		assertEquals(feedback1.getUuidChoice(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testGetAlternativeIndex() {
		assertEquals(feedback1.getAlternativeIndex(), 1);
	}
	
	@Test
	public void testToString() {
		assertTrue(feedback1.toString() instanceof String);
		assertTrue(feedback1.toString(feedbacks) instanceof String);
	}
	
	@Test
	public void testFeedbackJSON() {
		assertTrue(Feedback.feedbackJSON(feedbacks) instanceof String);
	}
	
	@Test
	public void testDateCreated() {
		assertTrue(feedback1.getDateCreated() instanceof String);
	}
	
	@Test
	public void testUserName() {
		assertEquals(feedback1.getUserName(), "user1");
	}
	
	@Test
	public void testDescription() {
		assertEquals(feedback1.getDescription(), "description");
	}

}