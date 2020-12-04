package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class DisapproverTest {

	LinkedList<Approver> approversNames = new LinkedList<Approver>();
	LinkedList<Disapprover> disapproversNames = new LinkedList<Disapprover>();
	Disapprover disov1 = new Disapprover(1, "orest1", "0a2dcad6-8535-4797-9632-32e0728dc6a3");
	Disapprover disov2= new Disapprover(2,"orest2", "0a2dcad6-8535-4797-9632-32e0728dc6a3");

	@Test
	public void testStrings() {	
	disapproversNames.add(disov1);
	disapproversNames.add(disov2);
	assertTrue(disov1.toString() instanceof String);
	assertTrue(disov1.toString(disapproversNames) instanceof String);

	}

}
