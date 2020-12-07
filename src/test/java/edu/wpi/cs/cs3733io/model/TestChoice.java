package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;

public class TestChoice {
	
	Choice choice1, choice2;
	
	@Before
	public void createChoices() {
		String[] alternativeNames1 = {"Alt1", "Alt2", "Alt3", "Alt4", "Alt5"};
		String[] alternativeNames2 = new String[5];
		choice1 = new Choice("03a6902e-3b28-46c8-a30f-5018994a67a0", 10, "Test Description", "2020/12/06 13:54:22", "2020/12/06 13:54:21", true, alternativeNames1);
		choice2 = new Choice(2, "Choice 2 Description", alternativeNames2);
		choice2.setDateCreated("2020/12/06 13:54:20");
		choice2.setDateCompleted("2020/12/06 13:54:22");
		choice2.setIsCompleted(false);
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
	@Test
	public void testAlternatives() {
		assertEquals(null, choice1.getAlternatives());
	}
	
	@Test
	public void testIsCompleted() {
		assertEquals(choice1.getIsCompleted(), true);
	}
	
	@Test
	public void testDateCreated() {
		assertEquals(choice1.getDateCreated(), "2020/12/06 13:54:21");
	}
	
	@Test
	public void testDateCompleted() {
		assertEquals(choice1.getDateCompleted(), "2020/12/06 13:54:22");
	}
	
	@Test
	public void testToString() {
		Alternative alt1 = new Alternative("alt1", 3, "03a6902e-3b28-46c8-a30f-5018994a67a0");
		Alternative alt2 = new Alternative("03a6902e-3b28-46c8-a30f-5018994a67a7", "03a6902e-3b28-46c8-a30f-5018994a67a0", 1, "alt2", false);
		LinkedList<Alternative> alts = new LinkedList<>();
		alts.add(alt1);
		alts.add(alt2);
		assertTrue(choice1.toString(alt1) instanceof String);
		assertTrue(choice1.toString(alts) instanceof String);
	}
	
	@Test
	public void testToJSON() {
		assertTrue(choice1.toJSON() instanceof String);
	}
	
	@Test
	public void testToStringForGeneratingReport() {
		ArrayList<Choice> choices = new ArrayList<>();
		assertTrue(choice1.toStringForGeneratingReport(choices) instanceof String);
	}
	
	@Test
	public void testCompleted() {
		choice2.completed("2020/12/06 13:54:22");
		assertTrue(choice2.getIsCompleted());
	}

}
