package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.GetChoiceRequest;

public class TestGetChoiceRequest {
	
	@Test
	public void testGetChoiceRequest() {
		GetChoiceRequest request = new GetChoiceRequest("03a6902e-3b28-46c8-a30f-5018994a67a5");
		request.setUuidString("03a6902e-3b28-46c8-a30f-5018994a67a0");
		assertEquals(request.getUuidString(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
		request = new GetChoiceRequest();
	}
	
	@Test
	public void testToString() {
		GetChoiceRequest request = new GetChoiceRequest("03a6902e-3b28-46c8-a30f-5018994a67a5");
		assertTrue(request.toString() instanceof String);
	}

}
