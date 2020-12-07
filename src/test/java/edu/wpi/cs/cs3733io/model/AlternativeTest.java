package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class AlternativeTest {

	Alternative alternative = new Alternative("orest", 1 ,"0a2dcad6-8535-4797-9632-32e0728dc6a3"); 
	Alternative alternative2 = new Alternative("0a2dcad6-8535-4797-9632-32e0728dc6a3", "0a2dcad6-8535-4797-9632-32e0728dc6a3", 2, "orest2", true );
	
	@Test
	public void testStrings() {
		assertTrue(alternative.toJSON() instanceof String);
		assertTrue(alternative2.toJSON() instanceof String);
		LinkedList<Approver> approversNames = new LinkedList<Approver>();
		LinkedList<Disapprover> disapproversNames = new LinkedList<Disapprover>();
		Approver aprov1 = new Approver(1, "orest1", "0a2dcad6-8535-4797-9632-32e0728dc6a3");
		Approver aprov2= new Approver(2,"orest2","0a2dcad6-8535-4797-9632-32e0728dc6a3");
		Disapprover disov1 = new Disapprover(1, "orest1", "0a2dcad6-8535-4797-9632-32e0728dc6a3");
		Disapprover disov2= new Disapprover(2,"orest2", "0a2dcad6-8535-4797-9632-32e0728dc6a3");
		approversNames.add(aprov1);
		approversNames.add(aprov2);		
		disapproversNames.add(disov1);
		disapproversNames.add(disov2);
		alternative2.setApproverNames(approversNames);
		alternative2.setDisapproverNames(disapproversNames);
		assertTrue(alternative.toJSON() instanceof String);
		assertTrue(alternative2.toJSON() instanceof String);
		LinkedList<String> aprovs = new LinkedList<String>();
		aprovs.add("orest1");
		aprovs.add("orest2");
		LinkedList<String> disaprov = new LinkedList<String>();
		disaprov.add("orest11");
		disaprov.add("orest22");
		alternative.setApprovers(aprovs);
		alternative.setDisapprovers(disaprov);
		assertEquals (aprovs, alternative.getApprovers());
		assertEquals (disaprov, alternative.getDisapprovers());


	}

@Test
public void testStringsFeedback() {
	LinkedList<Feedback> feed = new LinkedList<Feedback>();
	Feedback feed1 = new Feedback("orest","feed1", "0a2dcad6-8535-4797-9632-32e0728dc6a3",1 );
	Feedback feed2 = new Feedback("orest","feed12", "0a2dcad6-8535-4797-9632-32e0728dc6a3",1 );
	Feedback feed3 = new Feedback("orest","feed13", "0a2dcad6-8535-4797-9632-32e0728dc6a3",2 );
	feed.add(feed1);
	feed.add(feed2);
	feed.add(feed3);
	alternative.setFeedback(feed);
	assertTrue(alternative.toJSON() instanceof String);
}}
