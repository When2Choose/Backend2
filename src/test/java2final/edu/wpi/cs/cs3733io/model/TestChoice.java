package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import static org.junit.Before.*;

import org.junit.Test;

public class TestChoice {
	
	@BeforeEach
	public void createChoices() {
		String[] alternativeNames1 = {"Alt1", "Alt2", "Alt3", "Alt4", "Alt5"};
		String[] alternativeNames2 = {"Alt1", "Alt2", "Alt3"};
		Choice choice1 = new Choice("03a6902e-3b28-46c8-a30f-5018994a67a0", 10, "Test Description", "2020/12/06 13:54:22", "2020/12/06 13:54:21", true, alternativeNames1);
		Choice choice2 = new Choice("4591d7bf-8637-4ecd-9376-85eca5318abd", 2, "Choice 2 Description", "2020/12/01 13:54:22", "2020/12/01 13:54:21", false, alternativeNames2);
	}

	@Test
	public void testUUIDString() {
		assertEquals(choice1.getUuidString(), "03a6902e-3b28-46c8-a30f-5018994a67a0");
	}
	
	@Test
	public void testMemberCount() {
		assertEquals(choice2.getMemberCount(), 2);
	}
	
	@Test
	public void testDescription() {
		assertEquals(choice2.getDescription(), "Choice 2 Description");
	}
	
//	@Test
//	public void testAlternativeNames() {
//		
//	}
//	
//	@Test
//	public void testAlternatives() {
//		
//	}
	
	@Test
	public void testIsCompleted() {
		assertEquals(choice1.getIsCompleted(), true);
		assertEquals(choice2.getIsCompleted(), false);
	}
	
	@Test
	public void testDateCreated() {
		assertEquals(choice1.getDateCreated(), "2020/12/06 13:54:21");
	}
	
	@Test
	public void testDateCompleted() {
		assertEquals(choice1.getDateCompleted(), "2020/12/06 13:54:22");
	}

}
