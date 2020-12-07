package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;

public class TestDisapprover {
	
	Disapprover disapprover1;
	
	@Before
	public void createApprovers() {
		disapprover1 = new Disapprover(1, "03a6902e-3b28-46c8-a30f-5018994a67a0", "user");
	}
	
	@Test
	public void testSetUserName() {
		disapprover1.setUserName("user1");
		assertEquals(disapprover1.getUserName(), "user1");
	}
	
	@Test
	public void testGetChoiceUuid() {
		assertEquals(disapprover1.getChoiceUuid(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testGetAlternativeIndex() {
		assertEquals(disapprover1.getAlternativeIndex(), 1);
	}
	
	@Test
	public void testToString() {
		assertTrue(disapprover1.toString() instanceof String);
		LinkedList<Disapprover> disapprovers = new LinkedList<>();
		disapprovers.add(disapprover1);
		assertTrue(disapprover1.toString(disapprovers) instanceof String);
	}

}
