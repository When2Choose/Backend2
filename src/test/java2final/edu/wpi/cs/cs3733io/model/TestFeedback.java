package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;

public class TestFeedback {
	
	LinkedList<Feedback> feedbacks = new LinkedList<>();
	Feedback feedback1, feedback2;
	
	@Before
	public void createFeedback() {
		feedback1 = new Feedback("user1", "description", "03a6902e-3b28-46c8-a30f-5018994a67a0", 1);
		feedback2 = new Feedback("user2", "description2", "03a6902e-3b28-46c8-a30f-5018994a67a0", 2);
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
		assertTrue(feedback1.feedbackJSON(feedbacks) instanceof String);
	}

}
