package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;

public class TestCreateChoiceRequest {
	
	@Test
	public void testCreateChoiceRequest() {
		String[] alts = new String[5];
		CreateChoiceRequest request = new CreateChoiceRequest(5, "description", alts);
		request.setMemberCount(10);
		request.setDescription("test description");
		request.setAlternatives(alts);
		assertEquals(request.getMemberCount(), 10);
		assertEquals(request.getDescription(), "test description");
		for (int i = 0; i < 5; i++) {
			assertEquals(request.getAlternatives()[i], alts[i]);
		}
		request = new CreateChoiceRequest();
	}
	
	@Test
	public void testToString() {
		String[] alts = new String[5];
		CreateChoiceRequest request = new CreateChoiceRequest(5, "description", alts);
		assertTrue(request.toString() instanceof String);
	}
}
