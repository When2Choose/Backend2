package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ChoiceTest {
	
	String[] strings = {"one", "two", "three", "four", "five"};
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
		assertEquals (null, choice.getAlternatives());

	}
	
	@Test
	public void testString() {
		assertTrue(choice.toJSON() instanceof String);
		assertTrue(choice2.toJSON() instanceof String);
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(choice);
		choices.add(choice2);
		assertTrue (choice.toStringForGeneratingReport(choices) instanceof String);

	}
	
	@Test
	public void testStringAlternatives() {
		Alternative alternative = new Alternative("orest", 1 ,"0a2dcad6-8535-4797-9632-32e0728dc6a3"); 
		Alternative alternative2 = new Alternative("0a2dcad6-8535-4797-9632-32e0728dc6a3", "0a2dcad6-8535-4797-9632-32e0728dc6a3", 2, "orest2", true ); 
		LinkedList<Alternative> alts = new LinkedList<Alternative>();
		alts.add(alternative);
		alts.add(alternative2);
		choice.competed("test");
		assertTrue(choice.toString(alternative) instanceof String);
		assertTrue(choice.toString(alternative2) instanceof String);
		assertTrue(choice.toString(alts) instanceof String);

	}
	

}
