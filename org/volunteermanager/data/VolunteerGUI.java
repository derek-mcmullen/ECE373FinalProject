package org.volunteermanager.data;

import java.io.*; 
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.volunteermanager.people.*;


public class VolunteerGUI extends JFrame{
	// Instance of a management system
	private ManagementSystem ms; 
	private Volunteer loggedInUser; 
	
	// Buttons
	private JButton submitCreate; 
	private JButton sendMessage; 
	
	// joinEvents fields
	private JPanel innerEventsList; 
	
	// eventCreate fields
	private JTextField eventName; 
	private JTextField eventLocation;
	private JTextField eventInfo; 
	private JTextField eventCoord; 
	private JTextField startTime; 
	private JTextField stopTime;
	private JRadioButton skill1; 
	private JRadioButton skill2;  
	private JRadioButton skill3;  
	private JRadioButton skill4;  
	private JRadioButton interest1;  
	private JRadioButton interest2;  
	private JRadioButton interest3; 
	private JRadioButton interest4; 
	
	// myEvents fields
	private JScrollPane scrollPane; 
	
	 
    public VolunteerGUI(Volunteer currentUser) {
    	ms = new ManagementSystem(); 
    	loggedInUser = currentUser; 
    	
    	// GUI Title and size
    	setTitle("Volunteer Management System"); 
    	setSize(500,500); 
    	
    	// Prep a tab  pane for navbar
    	JTabbedPane jtp = new JTabbedPane(); 
    	
    	// Place the tabbedpane on the navbar
    	getContentPane().add(jtp); 
    	
    	// List of all tabs to be populated
    	JPanel joinPanel = new JPanel();
    	JPanel createPanel = new JPanel();
    	JPanel eventsPanel = new JPanel();
    	JPanel profilePanel = new JPanel();
    	JPanel messagingPanel = new JPanel(); 
    		
    	// Adding the tabs to the navbar
    	jtp.addTab("Join Events", joinPanel);
    	jtp.addTab("Create Events", createPanel);
    	jtp.addTab("My Events", eventsPanel);
    	jtp.addTab("View Profile", profilePanel);
    	jtp.addTab("Messaging", messagingPanel);
    	
    	jtp.addChangeListener(new ChangeListener() { 
    		@Override
    		public void stateChanged(ChangeEvent e) {
                reloadPanel();    // This function below can be used to refresh pages on tab change 
            }

        });
    	
    /* ------	PANEL IMPLEMENTATIONS BELOW   ------ */
    	
    	// Join panel implementation 
    	joinPanel.setLayout(new BorderLayout());
    	innerEventsList = new JPanel(new GridLayout(6,2)); 
    	joinPanel.add(innerEventsList, BorderLayout.CENTER); 
    	
    	
    	
		// Create Event panel implementation 
    	createPanel.setLayout(new BorderLayout()); 
    	
    	JPanel innerPanel = new JPanel(new GridLayout(6,2)); 
    	
		eventName = new JTextField();
	    eventLocation = new JTextField();
	    eventInfo = new JTextField();
	    eventCoord = new JTextField(); 
	    startTime = new JTextField(); 
	    stopTime = new JTextField(); 
	    skill1 = new JRadioButton(); 
	    skill2 = new JRadioButton(); 
	    skill3 = new JRadioButton(); 
	    skill4 = new JRadioButton(); 
	    interest1 = new JRadioButton(); 
	    interest2 = new JRadioButton(); 
	    interest3 = new JRadioButton(); 
	    interest4 = new JRadioButton(); 
	    
	    // Skills options
	    JPanel skillsPanel = new JPanel(new GridLayout(4,2));
	    skillsPanel.setAlignmentX(RIGHT_ALIGNMENT);
	    skillsPanel.add(skill1); 
	    skillsPanel.add(new JLabel("Power Tools"));
	    skillsPanel.add(skill2); 
	    skillsPanel.add(new JLabel("Public Speaking"));
	    skillsPanel.add(skill3); 
	    skillsPanel.add(new JLabel("Childcare"));
	    skillsPanel.add(skill4);
	    skillsPanel.add(new JLabel("CPR Certified"));
	    skillsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	    // Interests category
	    JPanel interestsPanel = new JPanel(new GridLayout(4,2));
	    interestsPanel.setAlignmentX(RIGHT_ALIGNMENT);
	    interestsPanel.add(interest1); 
	    interestsPanel.add(new JLabel("Construction"));
	    interestsPanel.add(interest2); 
	    interestsPanel.add(new JLabel("Politics"));
	    interestsPanel.add(interest3); 
	    interestsPanel.add(new JLabel("Education"));
	    interestsPanel.add(interest4);
	    interestsPanel.add(new JLabel("Children"));
	    interestsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	    
	    // TimeSlot Panel
	    JPanel timeLabels = new JPanel(new GridLayout(2,1)); 
	    timeLabels.add(new JLabel("Event Starts:"));
	    timeLabels.add(new JLabel("Event Ends:"));
	    JPanel timeFields = new JPanel(new GridLayout(2,1)); 
	    timeFields.add(startTime); 
	    timeFields.add(stopTime);
	    
	    // Title Pane
	    JPanel titleLabels = new JPanel(new GridLayout(2,1));
	    titleLabels.add(new JLabel("Event Title:")); 
	    titleLabels.add(new JLabel("Event Coordinator:"));
	    JPanel titleFields = new JPanel(new GridLayout(2,1)); 
	    titleFields.add(eventName);
	    titleFields.add(eventCoord);
	    
	    // innerPanel is the center of the createPanel tab
	    innerPanel.add(titleLabels);
	    innerPanel.add(titleFields);
	    innerPanel.add(timeLabels); 
	    innerPanel.add(timeFields); 
	    innerPanel.add(new JLabel("Location:"));
	    innerPanel.add(eventLocation);
	    innerPanel.add(new JLabel("Extra Info:"));
	    innerPanel.add(eventInfo); 
	    innerPanel.add(new JLabel("Required Skills:"));
	    innerPanel.add(skillsPanel);
	    innerPanel.add(new JLabel("Interest Category:"));
	    innerPanel.add(interestsPanel);
	   
	    createPanel.add(innerPanel, BorderLayout.CENTER); 
    	
    	submitCreate = new JButton("Create Event");
    	createPanel.add(submitCreate, BorderLayout.SOUTH);
    	submitCreate.addActionListener(new ButtonHandler());
    	setVisible(true);
    	
    	// MyEvents panel implementation
    	String msgArea = ms.getEventsByUserAsString(new Volunteer()); 
    	JTextArea textArea = new JTextArea(msgArea);
		scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension( 480, 430 ) );
		eventsPanel.add(scrollPane); 
		

		// Messaging panel implementation 
    	sendMessage = new JButton("Send Message");
    	messagingPanel.add(sendMessage);

    	sendMessage.addActionListener(new ButtonHandler());
    	setVisible(true);
    }
 
    class ButtonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		
    		JButton source = (JButton)(e.getSource());
    		
    		// IF-ELSE tree to check which button was pressed (all buttons must be declared as private fields of the overall class to be readable here)
    		if (source.equals(submitCreate)) { 
    			handleEventCreation(); 
    			
    		} else if (source.equals(sendMessage)) { 
    			JOptionPane.showMessageDialog(null, "sendMessage button has been clicked", "Title Here", JOptionPane. INFORMATION_MESSAGE);
    		}
    	}
    }
    
    
    public void handleEventCreation() { 
    	String eName = eventName.getText(); 
    	String eLocation = eventLocation.getText(); 
    	String eInfo = eventInfo.getText(); 

    	Event toAdd = new Event(); 
    	TimeSlot ts = new TimeSlot(); 
    	Date start = new Date(startTime.getText()); 
    	Date stop = new Date(stopTime.getText()); 
    	ts.setStartTime(start);
    	ts.setStopTime(stop);
  
    	toAdd.setTime(ts);
    	toAdd.setTitle(eName);
    	toAdd.setEventId(ms.getEvents().size()+1);
    	toAdd.setLocation(eLocation);
    	toAdd.addExtraInfo(eInfo);
    	if (skill1.isSelected()) { 
    		toAdd.addRequiredSkill("Power Tools");
    	}
    	if (skill2.isSelected()) { 
    		toAdd.addRequiredSkill("Public Speaking");
    	}
    	if (skill3.isSelected()) { 
    		toAdd.addRequiredSkill("Childcare");
    	}
    	if (skill4.isSelected()) { 
    		toAdd.addRequiredSkill("CPR Certified");
    	}
    	if (interest1.isSelected()) { 
        	toAdd.addInterestCategory("Construction");
    	}
    	if (interest2.isSelected()) { 
        	toAdd.addInterestCategory("Politics");
    	}
    	if (interest3.isSelected()) { 
        	toAdd.addInterestCategory("Education");
    	}
    	if (interest4.isSelected()) { 
        	toAdd.addInterestCategory("Children");
    	}

		ms.addEvent(toAdd);
    	loggedInUser.joinEvent(toAdd); 
		
		JOptionPane.showMessageDialog(null, "Event Created!", "Results", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void reloadPanel() { 
    	String msgArea = ms.getEventsByUserAsString(loggedInUser); 
    	JTextArea textArea = new JTextArea(msgArea);
		scrollPane.setViewportView(textArea);
		
    }
    
    
    public static void main (String []args) {
    	Volunteer aUser = new Volunteer(); 
    	VolunteerGUI tab = new VolunteerGUI(aUser); 
    }
    
    
}
