package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.AllResponse;

public class TestAllResponse {

	@Test
	public void testAllResponse() {
		AllResponse response = new AllResponse("test response", 200);
		assertEquals(response.response, "test response");
		assertEquals(response.statusCode, 200);
		assertEquals(response.error, "");
		
		AllResponse response2 = new AllResponse(400, "test error");
		assertEquals(response2.response, "");
		assertEquals(response2.statusCode, 400);
		assertEquals(response2.error, "test error");
	}
}
