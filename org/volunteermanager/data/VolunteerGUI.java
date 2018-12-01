package org.volunteermanager.data;

import java.io.*; 
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.volunteermanager.people.*;


public class VolunteerGUI extends JFrame{
	
	private JButton submitCreate; 
	private JButton sendMessage; 
	
	 
    public VolunteerGUI() {
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
    	
		JTextField eventName = new JTextField();
	    JTextField eventLocation = new JTextField();
	    JTextField eventInfo = new JTextField();
	    JRadioButton skill1 = new JRadioButton(); 
	    JRadioButton skill2 = new JRadioButton(); 
	    JRadioButton skill3 = new JRadioButton(); 
	    JRadioButton skill4 = new JRadioButton(); 
	    JRadioButton interest1 = new JRadioButton(); 
	    JRadioButton interest2 = new JRadioButton(); 
	    JRadioButton interest3 = new JRadioButton(); 
	    JRadioButton interest4 = new JRadioButton(); 
	    
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
    			JOptionPane.showMessageDialog(null, "Event Created Button", "What happened?", JOptionPane. INFORMATION_MESSAGE);
    		} else if (source.equals(sendMessage)) { 
    			JOptionPane.showMessageDialog(null, "sendMessage button has been clicked", "What happened?", JOptionPane. INFORMATION_MESSAGE);
    		}
    	}
    }
    
    public static void main (String []args) { 
    	VolunteerGUI tab = new VolunteerGUI(); 
    }
    
    
}
