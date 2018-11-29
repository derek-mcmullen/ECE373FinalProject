package org.volunteermanager.data;

import java.io.*; 
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.volunteermanager.people.*;


public class VolunteerGUI extends JFrame{
	 
    public VolunteerGUI() {
    	// GUI Title and size
    	setTitle("Volunteer Management System"); 
    	setSize(500,500); 
    	
    	// Prep a tab  pane for navbar
    	JTabbedPane jtp = new JTabbedPane(); 
    	ButtonHandler bhandler = new ButtonHandler();
    	
    	// Place the tabbedpane on the navbar
    	getContentPane().add(jtp); 
    	
    	// List of all tabs to be populated
    	JPanel joinPanel = new JPanel();
    	JPanel createPanel = new JPanel(new GridLayout(4, 2));
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
 
    class ButtonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		 JOptionPane.showMessageDialog(null, "I've been pressed", "What happened?", JOptionPane. INFORMATION_MESSAGE);
    	}
    }
    
    public static void main (String []args) { 
    	VolunteerGUI tab = new VolunteerGUI(); 
    }
    
    
}
