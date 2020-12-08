package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.DeleteChoicesRequest;

public class TestDeleteChoicesRequest {
	
	@Test
	public void testDeleteChoiceRequest() {
		DeleteChoicesRequest request = new DeleteChoicesRequest(10.0);
		request.setDays(5.0);
		assertTrue(request.getDays() == 5.0);
		assertEquals(request.successMessage(), "Successful");
		request = new DeleteChoicesRequest();
	}
}
