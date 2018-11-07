package org.volunteermanager.data;

import java.util.*;

public class TimeSlot {

	private Date startTime; 
	private Date stopTime; 
	
	public TimeSlot () { 
		Date startTime = new Date(); 
		Date stopTime = new Date(); 
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	
	// Returns true if there will be a conflict in time between two timeslots
	public boolean detectTimeConflict(TimeSlot aTS) { 
		if (this.startTime.getTime() > aTS.getStopTime().getTime()) { 
			return false; 
		} else if (this.stopTime.getTime() < aTS.getStartTime().getTime()) { 
			return false; 
		} else {
			return true; 
		} 
	}
	
}
