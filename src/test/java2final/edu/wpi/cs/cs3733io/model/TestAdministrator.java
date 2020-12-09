package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;

import org.junit.Test;

public class TestAdministrator {
	
	Administrator admin, admin2;
	UUID uuidChoice;
	
	@Before
	public void createAdministrator() {
		uuidChoice = UUID.randomUUID();
		admin = new Administrator("user1", "password", uuidChoice);
		admin2 = new Administrator("user2", "password2");
	}
	
	@Test
	public void testName() {
		assertEquals(admin.getName(), "user1");
	}
	
	@Test
	public void testUUIDChoice() {
		assertEquals(admin.getUuidChoice(), uuidChoice);
	}
	
	@Test
	public void testPassword() {
		assertEquals(admin.getPassword(), "password");
	}
	
	@Test
	public void testDeleteChoices() {
		assertFalse(admin.requestDeleteAllChoices(10.0));
	}

}