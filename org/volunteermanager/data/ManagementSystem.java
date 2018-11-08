package org.volunteermanager.data;

import org.volunteermanager.people.*;
import java.util.*;

public class ManagementSystem {

	private ArrayList<Event> events;
	private ArrayList<User> users; 
	private ArrayList<String> supportedSkills; 
	private ArrayList<String> supportedInterests; 
	
	// Constructors
	public ManagementSystem() { 
		events = new ArrayList<Event>(); 
		users = new ArrayList<User>(); 
		supportedSkills = new ArrayList<String>(); 
		supportedInterests = new ArrayList<String>(); 
	}

	// Getters and Setters
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public void addEvent(Event aEvent) { 
		events.add(aEvent); 
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void addUser(User aUser) { 
		users.add(aUser); 
	}

	public ArrayList<String> getSupportedSkills() {
		return supportedSkills;
	}
	
	public void addSupportedSkill(String aSkill) { 
		supportedSkills.add(aSkill); 
	}
	public ArrayList<String> getSupportedInterests() {
		return supportedSkills;
	}
	
	public void addSupportedInterest(String aInterest) { 
		supportedInterests.add(aInterest); 
	}

	public ArrayList<Event> getEventsBySkill(String aSkill) { 
		ArrayList<Event> eventsBySkill = new ArrayList<Event>(); 
		
		for (Event evt : events) { 
			for (String skillReq : evt.getRequiredSkills()) { 
				if (skillReq.equals(aSkill)) { 
					eventsBySkill.add(evt); 
				}
			}
		}
		return eventsBySkill; 
	}
	
	public ArrayList<Event> getEventsByInterest(String aInterest) { 
		ArrayList<Event> eventsByInterest = new ArrayList<Event>(); 
		
		for (Event evt : events) { 
			for (String interest : evt.getInterestCategory()) { 
				if (interest.equals(aInterest)) { 
					eventsByInterest.add(evt); 
				}
			}
		}
		return eventsByInterest; 
	}

	
	
}
