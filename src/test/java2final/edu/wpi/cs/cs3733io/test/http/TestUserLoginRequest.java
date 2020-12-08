package edu.wpi.cs.cs3733io.test.http;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733io.http.UserLoginRequest;

public class TestUserLoginRequest {
	
	@Test
	public void testUserLoginRequest() {
		UserLoginRequest request = new UserLoginRequest("user", "03a6902e-3b28-46c8-a30f-5018994a67a5", "password");
		request.setName("user1");
		request.setChoiceId("03a6902e-3b28-46c8-a30f-5018994a67a0");
		request.setPassword("password1");
		assertEquals(request.getName(), "user1");
		assertEquals(request.getChoiceId(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
		assertEquals(request.getPassword(), "password1");
		request = new UserLoginRequest();
	}
	
	@Test
	public void testToString() {
		UserLoginRequest request = new UserLoginRequest("user", "03a6902e-3b28-46c8-a30f-5018994a67a5", "password");
		assertTrue(request.toString() instanceof String);
	}

}
