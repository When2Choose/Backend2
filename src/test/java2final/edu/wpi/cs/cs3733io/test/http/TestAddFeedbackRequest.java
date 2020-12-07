package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.AddFeedbackRequest;

public class TestAddFeedbackRequest {
	
	@Test
	public void testAddFeedbackRequest() {
		AddFeedbackRequest request = new AddFeedbackRequest("user", "feedback", "03a6902e-3b28-46c8-a30f-5018994a67a5", 2);
		request.setUser("user1");
		request.setFeedbackText("feedback text");
		request.setChoiceId("03a6902e-3b28-46c8-a30f-5018994a67a0");
		request.setAlternativeIndex(1);
		assertEquals(request.getUser(), "user1");
		assertEquals(request.getFeedbackText(), "feedback text");
		assertEquals(request.getChoiceId(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
		assertEquals(request.getAlternativeIndex(), 1);
	}
	
	@Test
	public void testToString() {
		AddFeedbackRequest request = new AddFeedbackRequest("user", "feedback", "03a6902e-3b28-46c8-a30f-5018994a67a5", 2);
		assertTrue(request.toString() instanceof String);
	}
}
