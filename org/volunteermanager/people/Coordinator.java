package org.volunteermanager.people;

import org.volunteermanager.data.*;
import java.util.*;

public class Coordinator extends User {

	private String organization; 

	public Coordinator() { 
		organization = ""; 
	}
	
	public String getOrganization() { 
		return this.organization; 
	}
	
	public void setOrganization(String aOrg) { 
		this.organization = aOrg; 
	}
	

	
	public void prepareEvent(Event aEvent, Date startTime, Date stopTime) { 
		
		TimeSlot TS = new TimeSlot(); 
		TS.setStartTime(startTime);
		TS.setStopTime(stopTime);
		
		if (!detectDuplicateEvent(aEvent, TS) ) { 
			aEvent.setCoordinator(this);
			aEvent.setTime(TS);
			this.addEvent(aEvent);
		}
		
	}
	
	public boolean detectDuplicateEvent(Event aEvent, TimeSlot aTS) { 
		boolean isDuplicate = false;
		
		for (Event event : this.getEvents()) {
			if ((aTS.getStartTime().getTime() >= event.getTime().getStopTime().getTime()) || (aTS.getStopTime().getTime() <= event.getTime().getStartTime().getTime()) ) {
				// Do nothing
			} else { 
				System.out.println("The event '" + aEvent.getTitle() + "' cannot be created on " + aTS.getStartTime() + " b/c it has a time conflict with '" + event.getTitle() + "'");
				isDuplicate = true; 
			}
		}
		
		return isDuplicate;
	}
}
