package org.volunteermanager.people;

import org.volunteermanager.data.*;
import java.util.*; 

public abstract class User {

	private String userName; 
	private String password; 
	private int userId; 
	private String name; 
	private ArrayList<Message> inbox; 
	protected ArrayList<Message> outbox; 
	protected ArrayList<Event> events; 
	
	public User() { 
		userName = ""; 
		password = ""; 
		userId = 0; 
		name = ""; 
		inbox = new ArrayList<Message>();
		outbox = new ArrayList<Message>(); 
		events = new ArrayList<Event>(); 
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Message> getInbox() {
		return inbox;
	}
	public void addMessageToInbox(Message aMessage) {
		this.inbox.add(aMessage);
	}
	public ArrayList<Message> getOutbox() {
		return outbox;
	}
	public void addMessageToOutbox(Message aMessage) {
		this.outbox.add(aMessage);
		aMessage.setFromField(this);
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	public void addEvent(Event aEvent) {
		this.events.add(aEvent);
	}

}
