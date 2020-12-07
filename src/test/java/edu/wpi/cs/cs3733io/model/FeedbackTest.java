package edu.wpi.cs.cs3733io.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class FeedbackTest {
	LinkedList<Feedback> feed = new LinkedList<Feedback>();
	Feedback feed1 = new Feedback("orest","feed1", "0a2dcad6-8535-4797-9632-32e0728dc6a3",1 );
	Feedback feed2 = new Feedback("orest","feed12", "0a2dcad6-8535-4797-9632-32e0728dc6a3",1 );
	Feedback feed3 = new Feedback("orest","feed13", "0a2dcad6-8535-4797-9632-32e0728dc6a3",2 );

	@Test
	public void testString() {	
	feed.add(feed1);
	feed.add(feed2);
	feed.add(feed3);
	assertTrue(feed1.toString() instanceof String);
	assertTrue(feed1.toString(feed) instanceof String);
	assertTrue(feed1.feedbackJSON(feed) instanceof String);

	}
	
	@Test
	public void testGetUserName() {	
	assertEquals("orest", feed1.getUserName());
	}
	
	@Test
	public void testGetDescription() {	
	assertEquals("feed1", feed1.getDescription());
	}
	
	@Test
	public void testGetUuidChoice() {	
	assertEquals("0a2dcad6-8535-4797-9632-32e0728dc6a3", feed1.getUuidChoice());
	}
	
	@Test
	public void testGetAlternativeIndex() {	
	assertEquals(1, feed1.getAlternativeIndex());
	}

}
