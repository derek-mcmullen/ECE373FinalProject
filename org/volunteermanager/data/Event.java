package org.volunteermanager.data;

import org.volunteermanager.people.*;
import java.util.*;

public class Event {

	private String title; 
	private int eventId; 
	private TimeSlot time; 
	private ArrayList<Volunteer> roster; 
	private Coordinator coordinator; 
	private ArrayList<String> requiredSkills; 
	private ArrayList<String> extraInfo; 
	private ArrayList<String> interestCategory; 
	private String location; 
	
	public Event() { 
		title = ""; 
		eventId = 0; 
		time = new TimeSlot(); 
		roster = new ArrayList<Volunteer>(); 
		coordinator = new Coordinator(); 
		requiredSkills = new ArrayList<String>(); 
		extraInfo = new ArrayList<String>(); 
		interestCategory = new ArrayList<String>(); 
		location = ""; 
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public TimeSlot getTime() {
		return time;
	}

	public void setTime(TimeSlot time) {
		this.time = time;
	}

	public ArrayList<Volunteer> getRoster() {
		return roster;
	}

	public Coordinator getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}

	public ArrayList<String> getRequiredSkills() {
		return requiredSkills;
	}

	public void addRequiredSkill(String reqSkill) {
		this.requiredSkills.add(reqSkill);
	}
	
	public void removeRequiredSkill(String reqSkill) { 
		this.requiredSkills.remove(reqSkill); 
	}

	public ArrayList<String> getExtraInfo() {
		return extraInfo;
	}

	public void addExtraInfo(String aInfo) {
		this.extraInfo.add(aInfo);
	}
	
	public void removeExtraInfo(String aInfo) { 
		this.extraInfo.remove(aInfo); 
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void addVolunteer(Volunteer aVol) { 
		this.roster.add(aVol); 
	}
	
	public void removeVolunteer(Volunteer aVol ) { 
		this.roster.remove(aVol); 
	}
	
	public void addInterestCategory(String aCat) { 
		this.interestCategory.add(aCat); 
	}
	
	public ArrayList<String> getInterestCategory() { 
		return interestCategory; 
	}
	
}
