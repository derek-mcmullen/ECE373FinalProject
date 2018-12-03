package org.volunteermanager.people;

import org.volunteermanager.data.*;
import java.util.*;
import javax.swing.*;

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
	
	public boolean joinEvent(Event aEvent) { 
		
		// Check if there is a schedule conflict
		if (detectScheduleConflict(aEvent)) { 
			return false; 
		}
		
		// Next check if the volunteer has the required skils
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
   
   public boolean joinEventGUI(Event aEvent) { 
		
		// Check if there is a schedule conflict
		if (detectScheduleConflictGUI(aEvent)) { 
			return false; 
		}
		
		// Next check if the volunteer has the required skils
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
            String message = (this.getName() + " does not have the skill required by '" + aEvent.getTitle() + "' and could not join!");
            JOptionPane.showMessageDialog(null, message, "Error: Missing skills", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else { 
			this.addEvent(aEvent);
			aEvent.addVolunteer(this);
			return true; 
		}
	}

	public boolean detectScheduleConflict(Event aEvent) { 
		boolean conflictDetected = false; 
		
		for ( Event evt : this.getEvents() ) { 
			if ((aEvent.getTime().getStartTime().getTime() >= evt.getTime().getStopTime().getTime()) || (aEvent.getTime().getStopTime().getTime() <= evt.getTime().getStartTime().getTime()) ) {
				// Do nothing
			} else { 
				System.out.println(this.getName() + " cannot add the event '" + aEvent.getTitle() + "' because it has a time conflict with '" + evt.getTitle() + "' already scheduled.");
				conflictDetected = true; 
			}
		}
		return conflictDetected; 
	}
   
   public boolean detectScheduleConflictGUI(Event aEvent) { 
		boolean conflictDetected = false; 
		
		for ( Event evt : this.getEvents() ) { 
			if ((aEvent.getTime().getStartTime().getTime() >= evt.getTime().getStopTime().getTime()) || (aEvent.getTime().getStopTime().getTime() <= evt.getTime().getStartTime().getTime()) ) {
				// Do nothing
			} else { 
            String message = (this.getName() + " cannot add the event '" + aEvent.getTitle() + "' because it has a time conflict with '" + evt.getTitle() + "' already scheduled.");
            JOptionPane.showMessageDialog(null, message, "Error: Conflicting Time", JOptionPane.ERROR_MESSAGE);
				conflictDetected = true; 
			}
		}
		return conflictDetected; 
	}

	public boolean leaveEvent(Event aEvent) { 
		boolean isEnrolled = false; 
		
		for ( Event evt : this.getEvents() ) { 
			if (evt.equals(aEvent)) { 
				isEnrolled = true; 
			}
		}
		
		if (isEnrolled) { 
			this.events.remove(aEvent); 
		} else { 
			System.out.println(this.getName() + " cannot leave '" + aEvent.getTitle() + "' because they are not signed up for it!");
		}
		return isEnrolled; 
	}
   public boolean leaveEventGUI(Event aEvent) { 
		boolean isEnrolled = false; 
		
		for ( Event evt : this.getEvents() ) { 
			if (evt.equals(aEvent)) { 
				isEnrolled = true; 
			}
		}
		
		if (isEnrolled) { 
         this.events.remove(aEvent); 
         JOptionPane.showMessageDialog(null, "You have left event: "+aEvent.getTitle()+".\n", "Success", JOptionPane.PLAIN_MESSAGE);
		} else { 
         JOptionPane.showMessageDialog(null, "You cannot leave '" + aEvent.getTitle() + "' because you are not signed up for it!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return isEnrolled; 
	}
}
