package org.volunteermanager.data;

import java.io.*; 
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.volunteermanager.people.*;


public class VolunteerGUI extends JFrame{
	// Instance of a management system
	private ManagementSystem ms; 
	private User loggedInUser; 
	
	// Buttons
	private JButton submitCreate; 
	private JButton sendMessage; 
	
	// eventCreate fields
	private JTextField eventName; 
	private JTextField eventLocation;
	private JTextField eventInfo; 
	private JRadioButton skill1; 
	private JRadioButton skill2;  
	private JRadioButton skill3;  
	private JRadioButton skill4;  
	private JRadioButton interest1;  
	private JRadioButton interest2;  
	private JRadioButton interest3; 
	private JRadioButton interest4; 
	
	 
    public VolunteerGUI(User currentUser) {
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
    	
    /* ------	PANEL IMPLEMENTATIONS BELOW   ------ */
    	
    	// Join panel implementation   
    	JLabel label1 = new JLabel();
    	label1.setText("Join Events");
    	joinPanel.add(label1);
    	
    	
    	
		// Create Event panel implementation 
    	createPanel.setLayout(new BorderLayout()); 
    	
    	JPanel innerPanel = new JPanel(new GridLayout(5,2)); 
    	
		eventName = new JTextField();
	    eventLocation = new JTextField();
	    eventInfo = new JTextField();
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

	    // innerPanel is the center of the createPanel tab
	    innerPanel.add(new JLabel("Event Title:"));
	    innerPanel.add(eventName);
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
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension( 480, 430 ) );
		eventsPanel.add(scrollPane); 
		
//		JOptionPane.showMessageDialog(null, scrollPane, "Schedule",  
//		                                       JOptionPane.YES_NO_OPTION);
    	
	    
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
    	
		JOptionPane.showMessageDialog(null, "Event Created!", "Results", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public static void main (String []args) {
    	User aUser = new Volunteer(); 
    	VolunteerGUI tab = new VolunteerGUI(aUser); 
    }
    
    
}
