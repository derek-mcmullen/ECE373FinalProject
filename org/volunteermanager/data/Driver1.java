package org.volunteermanager.data;

import org.volunteermanager.data.*;
import org.volunteermanager.people.*;
import java.util.*;

public class Driver1 {
	
	public static void main(String[] args) 	{
	
		// Initialize volunteerManager System
		ManagementSystem ms1 = new ManagementSystem(); 
		ms1.addSupportedSkill("Power Tools");
		ms1.addSupportedSkill("Spanish");
		ms1.addSupportedSkill("Childcare");
		ms1.addSupportedSkill("Painting");
		ms1.addSupportedSkill("Public Speaking");
		
		ms1.addSupportedInterest("Education");
		ms1.addSupportedInterest("Politics");
		ms1.addSupportedInterest("Construction");
		ms1.addSupportedInterest("Children");
		
	/* Create events, volunteers, coordinators, timeslots, messages  */ 
		
		// Volunteers
		Volunteer v1 = new Volunteer(); 
		Volunteer v2 = new Volunteer(); 
		Volunteer v3 = new Volunteer(); 
		Volunteer v4 = new Volunteer(); 
		
		// Coordinators
		Coordinator c1 = new Coordinator(); 
		Coordinator c2 = new Coordinator(); 
		Coordinator c3 = new Coordinator(); 
		
		// Events
		Event e1 = new Event(); 
		Event e2 = new Event(); 
		Event e3 = new Event(); 
		Event e4 = new Event(); 
		
		// TimeSlots
		TimeSlot t1 = new TimeSlot(); 
		TimeSlot t2 = new TimeSlot(); 
		TimeSlot t3 = new TimeSlot(); 
		TimeSlot t4 = new TimeSlot(); 
		
		// Messages
		Message m1 = new Message(); 
		Message m2 = new Message(); 
		Message m3 = new Message(); 
		Message m4 = new Message(); 
		
	/* Set basic attributes for each */ 
		
		// Set base attributes for volunteers
		v1.setName("Derek"); 
		v1.setUserName("dmcmullen");
		v1.addSkill("Power Tools");
		v1.addSkill("Painting");
		v1.addInterest("Construction");
		
		v2.setName("Dennis");
		v2.setUserName("dhardy");
		v2.addSkill("Painting");
		v2.addSkill("Spanish");
		v2.addSkill("Childcare");
		v2.addInterest("Politics");
		
		v3.setName("Corey");
		v3.setUserName("cminer");
		v3.addSkill("Power Tools");
		v3.addInterest("Construction");

		v4.setName("Michael");
		v4.setUserName("mmarefat");
		v4.addSkill("Public Speaking");
		v4.addSkill("Childcare");
		v4.addInterest("Education");
		v4.addInterest("Children");
		
		// Set base attributes for coordinators
		c1.setName("Habitat Coordinator");
		c1.setUserName("hcoordinator");
		c1.setOrganization("Habitat For Humanity");
		
		c2.setName("Politics Coordinator");
		c2.setUserName("pcoordinator");
		c2.setOrganization("AZ Democrats");
		
		c3.setName("Education Coordinator");
		c3.setUserName("ecoordinator");
		c3.setOrganization("University of Arizona");
		
		// Set base attributes of events
		e1.setTitle("Building a House");
		e1.setEventId(1);
		e1.setLocation("1234 Eastside St.");
		e1.addRequiredSkill("Power Tools");
		e1.addExtraInfo("Free Pizza");
		e1.addInterestCategory("Construction");

		e2.setTitle("Get Out The Vote");
		e2.setEventId(2);
		e2.setLocation("Downtown");
		e2.addRequiredSkill("Public Speaking");
		e2.addInterestCategory("Politics");
		
		e3.setTitle("Community Lecture - Programming 101");
		e3.setEventId(3);
		e3.setLocation("University of Arizona");
		e3.addExtraInfo("Learning Java has never been so easy!");
		e3.addInterestCategory("Education");
		
		e4.setTitle("Holiday Party Childcare");
		e4.setEventId(4);
		e4.setLocation("Raytheon Christmas Party");
		e4.addRequiredSkill("Childcare");
		e4.addRequiredSkill("Public Speaking");
		e4.addInterestCategory("Children");
		
		
		
		/* Testing Conflict Scenarios */
		System.out.println("Testing skill requirement conflicts: ");
		System.out.println("------------------------------------ ");
		
		// Conflicting skill requirements
		v1.joinEvent(e1);        // This one is good
		v2.joinEvent(e1);        // This one is bad
		v3.joinEvent(e1); 		 // This one is good

		// Single requirement, v4
		v2.joinEvent(e2);        // This one is bad
		v3.joinEvent(e2);        // This one is bad
		v4.joinEvent(e2);        // This one is good
		
		// No requirements all can join
		v1.joinEvent(e3);		
		v2.joinEvent(e3); 
		v3.joinEvent(e3);
		v4.joinEvent(e3);
		
		// Two requirements event, v2 only matches one of the 2 required 
		v2.joinEvent(e4);        // This one is bad
		v4.joinEvent(e4);        // This one is good
		
		
		
		// Conflicting event time slots
		System.out.println("\nTesting time slot conflicts:");
		System.out.println("----------------------------");
		
		Date startTime1 = new Date("11/20/2018 10:00"); 
		Date stopTime1 = new Date("11/20/2018 12:00"); 
		Date startTime2 = new Date("11/20/2018 11:00"); 
		Date stopTime2 = new Date("11/20/2018 13:00"); 
		Date startTime3 = new Date("11/20/2018 12:00"); 
		Date stopTime3 = new Date("11/20/2018 14:00"); 
		
		c1.prepareEvent(e1, startTime1, stopTime1);
		c2.prepareEvent(e2, startTime2, stopTime2); 	
		c3.prepareEvent(e3, startTime3, stopTime3); 		
		
		c2.prepareEvent(e1, startTime1, stopTime1);			// conflict exists
		c2.prepareEvent(e3, startTime3, stopTime3); 		// conflict exists
		
		
		// Try sending some messages
		System.out.println("\nTesting message transfer");
		System.out.println("------------------------");
		
		// sending message to everyone in the to field 
		m1.addToField(v1);
		m1.addToField(v2);
		m1.setFromField(c1);
		m1.setTitle("Test message for sending to field");
		m1.setBody("Test message body here. This is the body string. This message should goto v1 and v2 only");
		m1.setEvent(e1);
		
		m2.setTitle("This one has no to field");
		m2.setBody("Since there is no to field there will be no one to receive this message.");
		m2.setFromField(c2);
		
		// Sending message to everyone connected to event
		m3.setEvent(e3); 
		m3.setFromField(c3); 
		m3.setTitle("Test message for event sending");
		m3.setBody("This test message will goto everyone in event e2. This will be everyone");

		m4.addToField(v1);
		m4.addToField(v2);
		m4.addToField(v3);
		m4.addToField(v4);
		m4.setFromField(c3);
		m4.setEvent(e4); 			// should only reach v4
		
		
		m1.sendMessageToAddressed();
		m2.sendMessageToAddressed();
		m3.sendMessageToEventMembers();
		m4.sendMessageToEventMembers();
		
		
		// Check if correct recipients
		System.out.println(v1.getName() + " should have 2 messages. The inbox has: " + v1.getInbox().size() + " messages.");
		System.out.println(v2.getName() + " should have 2 messages. The inbox has: " + v2.getInbox().size() + " messages.");
		System.out.println(v3.getName() + " should have 1 message. The inbox has: " + v3.getInbox().size() + " messages.");
		System.out.println(v4.getName() + " should have 2 messages. The inbox has: " + v4.getInbox().size() + " messages.");
		
		//TODO: need to add some roster verification and print schedule stuff to show that we have whats expected
	}
}
