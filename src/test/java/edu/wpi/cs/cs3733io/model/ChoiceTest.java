package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChoiceTest {
	
	String[] strings = {"one", "two", "three", "four"};
	Choice choice = new Choice("0a2dcad6-8535-4797-9632-32e0728dc6a3", 3, "description", "2007", "2006", true, strings);
	Choice choice2 = new Choice(3, "description", strings);

	
	@Test
	public void testUuidString() {
		assertEquals ("0a2dcad6-8535-4797-9632-32e0728dc6a3", choice.getUuidString());
	}
	
	@Test
	public void testMemberCount() {
		assertEquals (3, choice.getMemberCount());
		assertEquals (3, choice2.getMemberCount());
	}
	
	@Test
	public void testDescription() {
		assertEquals ("description", choice.getDescription());
		assertEquals ("description", choice2.getDescription());
	}
	
	@Test
	public void testDateCompleted() {
		assertEquals ("2007", choice.getDateCompleted());
		choice.setDateCompleted("2008");
		assertEquals ("2008", choice.getDateCompleted());
	}
	
	@Test
	public void testDateCreated() {
		assertEquals ("2006", choice.getDateCreated());
		choice.setDateCreated("2007");
		assertEquals ("2007", choice.getDateCreated());
	}
	
	@Test
	public void testIsCompleted() {
		assertEquals (true, choice.getIsCompleted());
		choice.setIsCompleted(false);
		assertEquals (false, choice.getIsCompleted());
	}
	
	@Test
	public void testAlternativeNames() {
		assertEquals (strings, choice2.getAlternatives());
	}
	

}
