package org.volunteermanager.data;

import java.io.*; 
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.volunteermanager.data.*;
import org.volunteermanager.data.Event;
import org.volunteermanager.people.*;


public class VolunteerGUI extends JFrame{
	 
    public VolunteerGUI(ManagementSystem ms1, User loggedIn ) {
    	
    	
    	// GUI Title and size
    	setTitle("Volunteer Management System"); 
    	setSize(500,500); 
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	// Prep a tab  pane for navbar
    	JTabbedPane jtp = new JTabbedPane(); 
    	ButtonHandler bhandler = new ButtonHandler();
    	
    	// Place the tabbedpane on the navbar
    	getContentPane().add(jtp); 
    	
    	// List of all tabs to be populated
    	JPanel joinPanel = new JPanel(new GridLayout(0,2, 0,15));
    	JScrollPane joinPane =new JScrollPane( joinPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,  JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	joinPane.setPreferredSize(new Dimension(500,500));
    	JPanel createPanel = new JPanel(new GridLayout(4, 2));
    	JPanel eventsPanel = new JPanel();
    	JPanel profilePanel = new JPanel();
    	JPanel messagingPanel = new JPanel(); 
    		
    	// Adding the tabs to the navbar
    	jtp.addTab("Join Events", joinPane);
    	jtp.addTab("Create Events", createPanel);
    	jtp.addTab("My Events", eventsPanel);
    	jtp.addTab("View Profile", profilePanel);
    	jtp.addTab("Messaging", messagingPanel);
    	
    /* ------	PANEL IMPLEMENTATIONS BELOW   ------ */
    	
    	// Join panel implementation 
    	for(Event e: ms1.getEvents()) {
    		if(!loggedIn.getEvents().contains(e)) {
	    		JTextArea label = new JTextArea();
		    	JButton join = new JButton("Join Me");
		    	JButton info = new JButton("More Info");
		    	JPanel buttonContainer = new JPanel();
		    	
		    	label.setText(e.getTitle());
		    	label.setLineWrap(true);
		    	label.setWrapStyleWord(true);
		    	label.setEditable(false);
		    	label.setFocusable(false);
		    	label.setOpaque(false);
		    	
		    	info.addActionListener(new infoHandler(e, loggedIn ));
            join.addActionListener(new joinHandler(e, loggedIn ));
             
		    	joinPanel.add(label);
		    	buttonContainer.add(join);
		    	buttonContainer.add(info);
		    	joinPanel.add(buttonContainer);
    		}
    	}
    	
    	
		// Create panel implementation 
		JTextField eventName = new JTextField();
	    JTextField eventLocation = new JTextField();
	    JTextField eventInfo = new JTextField();
	    
	    createPanel.add(new JLabel("Event Title:"));
	    createPanel.add(eventName);
	    createPanel.add(new JLabel("Location:"));
	    createPanel.add(eventLocation);
	    createPanel.add(new JLabel("Extra Info:"));
	    createPanel.add(eventInfo);
    	
    	JButton submitCreate = new JButton("Create Event");
    	createPanel.add(submitCreate);
    	submitCreate.addActionListener(bhandler);
    	setVisible(true);
    	
    	
    	
	    
		// Messaging panel implementation 
    	JButton test = new JButton("Send Message");
    	messagingPanel.add(test);

    	test.addActionListener(bhandler);
    	setVisible(true);
    	
    
    }
    class infoHandler implements ActionListener{
    	private Event event;
    	private User user;
    	public infoHandler(Event e, User u) {
    		this.event=e;
    		this.user=u;
    	}
    	public void actionPerformed(ActionEvent a){
    		 String info =event.getTitle()+"\n";
    		 info += "Location: "+event.getLocation()+"\n";
    		 info += "Coordinator: "+event.getCoordinator().getName()+"\n";
    		 info += "Begins: "+event.getTime().getStartTime()+"\n";
    		 info += "Ends: "+event.getTime().getStopTime()+"\n";
    		 info += "Info: \n";
    		 for(String more:event.getExtraInfo()) {
    			 info += "     "+more +"\n";
    		 }
    		 info += "Required Skills: \n";
    		 for(String skill:event.getRequiredSkills()) {
    			 info += "     "+skill +"\n";
    		 }
    		 Object[] options = {"Join", "Cancel"};
    		 int n = JOptionPane.showOptionDialog(null, info, event.getTitle() +" info" , JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE ,null, options, options[0]);
    		 if(n == JOptionPane.YES_OPTION) {
    			 if(user.getClass() ==  Volunteer.class) {
    				 Volunteer v = (Volunteer) user;
                boolean added = v.joinEventGUI(event);
                if(added){
                  JPanel list = (JPanel)((JButton)a.getSource()).getParent().getParent();
                  int i=0;
                  for(Component comp:list.getComponents()){
                     if(comp==((JButton)a.getSource()).getParent()){
                        list.remove(i);
                        list.remove(i-1);
                     }
                     i++;
                  }
                  list.revalidate();
                  list.repaint();
                }  	 
              }
              else{
               JOptionPane.showMessageDialog(null, "You are a coordinator.\nOnly volunteers can join events.", "Error", JOptionPane.ERROR_MESSAGE);
              }
    		 }
       }
    }
    class joinHandler implements ActionListener{
      private Event event;
      private User user;
      public joinHandler(Event e, User u) {
         this.event=e;
         this.user=u;
      }
      public void actionPerformed(ActionEvent a){
         if(user.getClass() ==  Volunteer.class) {
            Volunteer v = (Volunteer) user;
           boolean added = v.joinEventGUI(event);
           if(added){
             JPanel list = (JPanel)((JButton)a.getSource()).getParent().getParent();
             int i=0;
             for(Component comp:list.getComponents()){
                if(comp==((JButton)a.getSource()).getParent()){
                   list.remove(i);
                   list.remove(i-1);
                }
                i++;
             }
             list.revalidate();
             list.repaint();
           }  	 
         }
         else{
          JOptionPane.showMessageDialog(null, "You are a coordinator.\nOnly volunteers can join events.", "Error", JOptionPane.ERROR_MESSAGE);
         }
      }
    }
    class ButtonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		 JOptionPane.showMessageDialog(null, "I've been pressed", "What happened?", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
    public static void main (String []args) { 
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
    			
    			c2.prepareEvent(e4, startTime4, stopTime4);
    			
    			
    			v1.joinEvent(e1);        // This one is good
    			v3.joinEvent(e1); 		 // This one is good

    			v4.joinEvent(e2);        // This one is good
    			
    			v1.joinEvent(e3);		
    			//v2.joinEvent(e3); 
    			v3.joinEvent(e3);
    			//v4.joinEvent(e3);
    			
    			v4.joinEvent(e4);        // This one is good	

    	VolunteerGUI tab = new VolunteerGUI(ms1, v2); 
    }
    
    
}
