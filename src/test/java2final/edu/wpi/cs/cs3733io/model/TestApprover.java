package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;

public class TestApprover {
	
	Approver approver1;
	
	@Before
	public void createApprovers() {
		approver1 = new Approver(1, "03a6902e-3b28-46c8-a30f-5018994a67a0", "user");
	}
	
	@Test
	public void testSetUserName() {
		approver1.setUserName("user1");
		assertEquals(approver1.getUserName(), "user1");
	}
	
	@Test
	public void testGetChoiceUuid() {
		assertEquals(approver1.getChoiceUuid(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testGetAlternativeIndex() {
		assertEquals(approver1.getAlternativeIndex(), 1);
	}
	
	@Test
	public void testToString() {
		assertTrue(approver1.toString() instanceof String);
		LinkedList<Approver> approvers = new LinkedList<>();
		approvers.add(approver1);
		assertTrue(approver1.toString(approvers) instanceof String);
	}

}
