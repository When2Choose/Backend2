package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class ApproverTest {
	LinkedList<Approver> approversNames = new LinkedList<Approver>();
	Approver aprov1 = new Approver(1, "0a2dcad6-8535-4797-9632-32e0728dc6a3", "orest1");
	Approver aprov2= new Approver(2, "0a2dcad6-8535-4797-9632-32e0728dc6a3" , "orest2");

	@Test
	public void testStrings() {	
	approversNames.add(aprov1);
	approversNames.add(aprov2);		
	assertTrue(aprov1.toString() instanceof String);
	assertTrue(aprov1.toString(approversNames) instanceof String);

	}
	
	@Test
	public void testUserName() {
		assertEquals("orest1", aprov1.getUserName());
		aprov1.setUserName("orestv2");
		assertEquals("orestv2", aprov1.getUserName());
	}
	
	@Test
	public void testChoiceUuid() {
		assertEquals("0a2dcad6-8535-4797-9632-32e0728dc6a3", aprov1.getChoiceUuid());
	}
	
	@Test
	public void testAlternativeIndex() {
		assertEquals(1, aprov1.getAlternativeIndex());
	}

}
