package org.volunteermanager.data;

import org.volunteermanager.people.*;
import java.util.*;

public class Message {

	private String title; 
	private ArrayList<User> toField; 
	private User fromField; 
	private Event event; 
	private String body; 

	public Message() { 
		title = ""; 
		toField = new ArrayList<User>(); 
		//event = new Event(); 
		body = ""; 
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ArrayList<User> getToField() {
		return toField;
	}
	public void addToField(User aUser) { 
		this.toField.add(aUser); 
	}
	public User getFromField() {
		return fromField;
	}
	public void setFromField(User aUser) { 
		this.fromField = aUser; 
	}
	
	public void sendMessageToAddressed() {
		if (toField.size() == 0) {
			System.out.println("This message has not been addressed to anyone. Cannot send.");
			return; 
		}
		
		for (User addressedTo : toField) { 
			addressedTo.addMessageToInbox(this);
		}	
	}
	
	public void sendMessageToEventMembers() {
		for (User eventMbr : this.event.getRoster()) { 
			eventMbr.addMessageToInbox(this);
		}
	}
}
