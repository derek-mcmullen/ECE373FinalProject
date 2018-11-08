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
		ms1.addEvent(e1);

		e2.setTitle("Get Out The Vote");
		e2.setEventId(2);
		e2.setLocation("Downtown");
		e2.addRequiredSkill("Public Speaking");
		e2.addInterestCategory("Politics");
		ms1.addEvent(e2); 
		
		e3.setTitle("Community Lecture - Programming 101");
		e3.setEventId(3);
		e3.setLocation("University of Arizona");
		e3.addExtraInfo("Learning Java has never been so easy!");
		e3.addInterestCategory("Education");
		ms1.addEvent(e3); 
		
		e4.setTitle("Holiday Party Childcare");
		e4.setEventId(4);
		e4.setLocation("Raytheon Christmas Party");
		e4.addRequiredSkill("Childcare");
		e4.addRequiredSkill("Public Speaking");
		e4.addInterestCategory("Children");
		ms1.addEvent(e4); 
		
		// Conflicting event time slots
		System.out.println("\nTesting create events with time slot conflicts:");
		System.out.println("-----------------------------------------------");	
		
		Date startTime1 = new Date("11/20/2018 10:00"); 
		Date stopTime1 = new Date("11/20/2018 12:00"); 
		t1.setStartTime(startTime1);
		t1.setStopTime(stopTime1);
		Date startTime2 = new Date("11/20/2018 11:00"); 
		Date stopTime2 = new Date("11/20/2018 13:00"); 
		t2.setStartTime(startTime2);
		t2.setStopTime(stopTime2);
		Date startTime3 = new Date("11/20/2018 12:00"); 
		Date stopTime3 = new Date("11/20/2018 14:00"); 	
		t3.setStartTime(startTime3);
		t3.setStopTime(stopTime3);
		Date startTime4 = new Date("12/07/2018 15:00"); 
		Date stopTime4 = new Date("12/07/2018 18:00"); 
		t4.setStartTime(startTime4);
		t4.setStopTime(stopTime4);
		
		c1.prepareEvent(e1, startTime1, stopTime1);
		c2.prepareEvent(e2, startTime2, stopTime2); 	
		c3.prepareEvent(e3, startTime3, stopTime3); 
		
		c2.prepareEvent(e4, startTime1, stopTime1);			// conflict exists
		c2.prepareEvent(e4, startTime3, stopTime3); 		// conflict exists
		c2.prepareEvent(e4, startTime4, stopTime4);
		
	/* Testing Conflict Scenarios */
		System.out.println("\nTesting skill requirement and schedule conflicts for join event: ");
		System.out.println("---------------------------------------------------------------- ");
		
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

		
		
	/* Testing message functions */
		System.out.println("\nTesting message send error checks:");
		System.out.println("----------------------------------");
		
		// 2 messages for sending message to everyone in the to field 
		m1.addToField(v1);
		m1.addToField(v2);
		m1.setFromField(c1);
		m1.setTitle("Test message for sending to field");
		m1.setBody("Test message body here. This is the body string. This message should goto v1 and v2 only");
		m1.setEvent(e1);
		
		m2.setTitle("This one has no to field");
		m2.setBody("Since there is no to field there will be no one to receive this message.");
		m2.setFromField(c2);
		
		// 2 messages for sending messages to everyone connected to the event
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
		
		// Send the prepared messages
		m1.sendMessageToAddressed();			
		m2.sendMessageToAddressed();
		m3.sendMessageToEventMembers();
		m4.sendMessageToEventMembers();
		
		System.out.println("\nChecking Inbox message counts:");
		System.out.println("------------------------------");
		
		// Check if correct recipients
		System.out.println(v1.getName() + " should have 2 messages. The inbox has: " + v1.getInbox().size() + " messages.");
		System.out.println(v2.getName() + " should have 2 messages. The inbox has: " + v2.getInbox().size() + " messages.");
		System.out.println(v3.getName() + " should have 1 message. The inbox has: " + v3.getInbox().size() + " messages.");
		System.out.println(v4.getName() + " should have 1 message. The inbox has: " + v4.getInbox().size() + " messages.");
		
	/* Print each volunteers schedules */
		System.out.println("\nPrinting the event rosters:");
		System.out.println("---------------------------");
		for (Event evt : v1.getEvents()) { 
			System.out.println(v1.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
		for (Event evt : v2.getEvents()) { 
			System.out.println(v2.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
		for (Event evt : v3.getEvents()) { 
			System.out.println(v3.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
		for (Event evt : v4.getEvents()) { 
			System.out.println(v4.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
			
	/* Testing leaving event conflicts */
		System.out.println("\nTesting leave event conflict checking:");
		System.out.println("---------------------------------------");
				
		v1.leaveEvent(e2); 		// not enrolled in this
		v1.leaveEvent(e3); 		// is enrolled in this
		v3.leaveEvent(e1); 		// is enrolled in this
		v3.leaveEvent(e4); 		// not enrolled in this
		v4.leaveEvent(e1); 		// not enrolled in this
		v4.leaveEvent(e2); 		// is enrolled in this

	
	/* Print each volunteers schedules again */
		System.out.println("\nPrinting the event rosters (after leaves - each vol should have 1 remaining event):");
		System.out.println("-----------------------------------------------------------------------------------");
		for (Event evt : v1.getEvents()) { 
			System.out.println(v1.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
		for (Event evt : v2.getEvents()) { 
			System.out.println(v2.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
		for (Event evt : v3.getEvents()) { 
			System.out.println(v3.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
		for (Event evt : v4.getEvents()) { 
			System.out.println(v4.getName() + " is signed up for an event on " + evt.getTime().getStartTime() + " named: '" + evt.getTitle() + "'");
		}
	
	/* Print the events by interest and skill req */
		System.out.println("\nPrinting events by interest category:");
		System.out.println("-------------------------------------");		
		
		// By interest category
		ArrayList<Event> events = new ArrayList<Event>(); 
		events = ms1.getEventsByInterest("Construction"); 
		System.out.println("Interest Category: 'Construction'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
		events = ms1.getEventsByInterest("Politics"); 
		System.out.println("Interest Category: 'Politics'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
		events = ms1.getEventsByInterest("Education"); 
		System.out.println("Interest Category: 'Education'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
		events = ms1.getEventsByInterest("Children"); 
		System.out.println("Interest Category: 'Children'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
		
		System.out.println("\nPrinting events by skill requirement:");
		System.out.println("-------------------------------------");	
		// By skill req
		events = ms1.getEventsBySkill("Power Tools"); 
		System.out.println("Skill Required: 'Power Tools'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
		events = ms1.getEventsBySkill("Public Speaking"); 
		System.out.println("Skill Required: 'Public Speaking'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
		events = ms1.getEventsBySkill("Childcare"); 
		System.out.println("Skill Required: 'Childcare'");
		for (Event evt : events) {
			System.out.println("    -> " + evt.getTitle());
		}
	}
	
}
