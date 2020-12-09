package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

public class TestUser {
	
	User user1;
	
	@Before
	public void createUser() {
		user1 = new User("user1", "password", "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testPassword() {
		user1.setPassword("password1");
		assertEquals(user1.getPassword(), "password1");
	}
	
	@Test
	public void testChoiceID() {
		assertEquals(user1.getChoiceId(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testName() {
		assertEquals(user1.getName(), "user1");
	}
	
	@Test
	public void testToString() {
		assertTrue(user1.toString() instanceof String);
		assertTrue(user1.toString(true) instanceof String);
	}

}