package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;

public class TestRemoveApproveRequest {
	
	@Test
	public void testRemoveApproveRequest() {
		RemoveApproveRequest request = new RemoveApproveRequest("03a6902e-3b28-46c8-a30f-5018994a67a5", "user", 1);
		request.setChoiceId("03a6902e-3b28-46c8-a30f-5018994a67a0");
		request.setUser("user1");
		request.setAlternative(1);
		assertEquals(request.getChoiceId(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
		assertEquals(request.getUser(), "user1");
		assertEquals(request.getAlternative(), 1);
		request = new RemoveApproveRequest();
	}
	
	@Test
	public void testToString() {
		RemoveApproveRequest request = new RemoveApproveRequest("03a6902e-3b28-46c8-a30f-5018994a67a5", "user", 1);
		assertTrue(request.toString() instanceof String);
	}
}
