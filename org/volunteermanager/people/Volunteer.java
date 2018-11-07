package org.volunteermanager.people;

import org.volunteermanager.data.*;
import java.util.*;

public class Volunteer extends User {

	private ArrayList<String> skills; 
	private ArrayList<String> interests; 
	
	public Volunteer() { 
		skills = new ArrayList<String>(); 
		interests = new ArrayList<String>(); 
	}
	
	public ArrayList<String> getSkills() {
		return skills;
	}
	public void addSkill(String aSkill) {
		this.skills.add(aSkill);
	}
	public ArrayList<String> getInterests() {
		return interests;
	}
	public void addInterest(String aInterest) {
		this.interests.add(aInterest);
	}
	public void addMessageToOutbox(Message aMessage) {
		this.outbox.add(aMessage);
		aMessage.setFromField(this);
	}
	
	public boolean joinEvent(Event aEvent) { 
		int skillMatches = 0; 
		
		if (aEvent.getRequiredSkills().size() > 0) { 
			for (String reqSkill : aEvent.getRequiredSkills()) { 
				for (String volSkill : this.skills) { 
					if (volSkill.equals(reqSkill)) { 
						skillMatches++; 
					}
				}
			}
			if (skillMatches == aEvent.getRequiredSkills().size()) { 
				this.addEvent(aEvent);
				aEvent.addVolunteer(this);
				return true; 
			} else { 
				System.out.println(this.getName() + " does not have the skill required by '" + aEvent.getTitle() + "' and could not join!");
				return false;
			}
		} else { 
			this.addEvent(aEvent);
			aEvent.addVolunteer(this);
			return true; 
		}
	}
	
	//TODO: 
	// public void getSchedule() 
	// public boolean leaveEvent(Event aEvent)
	// public boolean detectConflict(Event aEvent)
}
