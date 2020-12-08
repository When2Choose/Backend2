package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.CompleteChoiceRequest;

public class TestCompleteChoiceRequest {
	
	@Test
	public void testCompleteChoiceRequest() {
		CompleteChoiceRequest request1 = new CompleteChoiceRequest("03a6902e-3b28-46c8-a30f-5018994a67a5", 2);
		CompleteChoiceRequest request = new CompleteChoiceRequest();
		request.setChoiceId("03a6902e-3b28-46c8-a30f-5018994a67a0");
		request.setAlternativeIndex(1);
		assertEquals(request.getChoiceId(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
		assertEquals(request.getAlternativeInex(), 1);
		assertEquals(request1.getChoiceId(), "03a6902e-3b28-46c8-a30f-5018994a67a5");
		assertEquals(request1.getAlternativeInex(), 2);
		assertTrue(request.getDate() instanceof Date);
		assertTrue(request.getDateString() instanceof String);
	}

}
