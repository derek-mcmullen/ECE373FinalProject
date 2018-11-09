package org.volunteermanager.data;

import org.volunteermanager.data.*;
import org.volunteermanager.people.*;
import java.util.*;

public class Driver2 {
	
	public static void main(String[] args) 	{
	
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
		
		// Coordinators
		Coordinator c1 = new Coordinator(); 
		Coordinator c2 = new Coordinator(); 
		Coordinator c3 = new Coordinator(); 
		
		// add users to list
		ms1.addUser(v1);
		ms1.addUser(v2);
		ms1.addUser(c1);
		ms1.addUser(c2);
		ms1.addUser(c3);
		
		// Events
		Event e1 = new Event(); 
		Event e2 = new Event(); 
		Event e3 = new Event(); 
		Event e4 = new Event(); 
		
		// Messages
		Message m1 = new Message(); 
		
	/* Set basic attributes for each */ 
		
		// Set base attributes for volunteers
		v1.setName("John");
		v1.setUserName("jsmith");
		v1.setPassword("j");
		v1.addSkill("Power Tools");
		v1.addInterest("Construction");

		v2.setName("Tom");
		v2.setUserName("tsnack");
		v2.setPassword("t");
		v2.addSkill("Public Speaking");
		v2.addSkill("Childcare");
		v2.addInterest("Education");
		v2.addInterest("Children");
		
		// Set base attributes for coordinators
		c1.setName("Habitat Coordinator");
		c1.setUserName("hcoordinator");
		c1.setOrganization("Habitat For Humanity");
		c1.setPassword("h");
		
		c2.setName("Politics Coordinator");
		c2.setUserName("pcoordinator");
		c2.setOrganization("AZ Democrats");
		c2.setPassword("p");
		
		c3.setName("Education Coordinator");
		c3.setUserName("ecoordinator");
		c3.setOrganization("University of Arizona");
		c3.setPassword("e");
		
		// Set base attributes of events
		e1.setTitle("Building a House");
		e1.setEventId(1);
		e1.setLocation("1234 Eastside St.");
		e1.addRequiredSkill("Power Tools");
		e1.addExtraInfo("Free Pizza");
		e1.addInterestCategory("Construction");
		e1.setCoordinator(c1);

		e2.setTitle("Get Out The Vote");
		e2.setEventId(2);
		e2.setLocation("Downtown");
		e2.addRequiredSkill("Public Speaking");
		e2.addInterestCategory("Politics");
		e2.setCoordinator(c2);
		
		e3.setTitle("Community Lecture - Programming 101");
		e3.setEventId(3);
		e3.setLocation("University of Arizona");
		e3.addExtraInfo("Learning Java has never been so easy!");
		e3.addInterestCategory("Education");
		e3.setCoordinator(c3);
		
		e4.setTitle("Holiday Party Childcare");
		e4.setEventId(4);
		e4.setLocation("Raytheon Christmas Party");
		e4.addRequiredSkill("Childcare");
		e4.addRequiredSkill("Public Speaking");
		e4.addInterestCategory("Children");
		e4.setCoordinator(c3);
		
		m1.addToField(v1);
		m1.addToField(v2);
		m1.setFromField(c1);
		m1.setTitle("Test message for sending to field");
		m1.setBody("Test message body here. This is the body string.");
		m1.setEvent(e1);
		m1.sendMessageToAddressed();
		
		v1.addEvent(e1);
		v1.addEvent(e2);
		
		v2.addEvent(e3);
		v2.addEvent(e4);
		c1.addEvent(e1);
		c2.addEvent(e2);
		c3.addEvent(e3);
		c3.addEvent(e4);
		
		ms1.addEvent(e1);
		ms1.addEvent(e2);
		ms1.addEvent(e3);
		ms1.addEvent(e4);
		/* System */
		System.out.println("Welcome to the VolunteerManager System! ");
		System.out.println("------------------------------------ ");
		
		Scanner scanner = new Scanner(System.in);	
		User curUser = null;
		String ans = "";
		boolean logging = true;
		while (logging) {
			System.out.print("Do you have an account? (y/n) ");
			ans = scanner.nextLine();
			if (ans.equals("y")) { //log in
				System.out.print("Enter your username: ");
				String username = scanner.nextLine();
				curUser = findUser(username, ms1);
				if (curUser != null) {
					System.out.print("Enter your password: ");
					String password = scanner.nextLine();
					logging = !isCorrectPassword(password, curUser);
				} else {
					System.out.println("No user with that username exists. Please try again.");
				}
			} else if (ans.equals("n")) { // create an account
				curUser = createAccount(ms1, scanner);
				if (curUser != null) {
					logging = false;
				}
			} else {
				System.out.println("Not a valid input, try again.");
			}
		}
		boolean program = true;
		while (program) {
			ans = printMainMenu(curUser, scanner);
			if (ans.equals("m")) {
				inboxMenu(curUser, ms1, scanner);
			} else if (ans.equals("p")) {
				profileMenu(curUser, scanner);
			} else if (ans.equals("e")) {
				eventsMenu(curUser, scanner);
			} else if (curUser.getClass() == Volunteer.class && ans.equals("f")) {
				findEventMenu(curUser, ms1, scanner);
			} else if (curUser.getClass() == Coordinator.class && ans.equals("c")){
				createEventMenu(curUser, ms1, scanner);
			}
			else if (ans.equals("q")) {
				System.out.println("Goodbye!");
				return;
			}
			else {
				System.out.println("Not a valid input, try again.");
			}
		}
		scanner.close();
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public static void printMessage(Message email, Scanner scanner){
		String ans = "";
		System.out.println("Press any key then enter to go to the main menu");
		System.out.println("Title: " +email.getTitle());
		System.out.println("From: " + email.getFromField().getName());
		System.out.println("Messsage: " +email.getBody());
		ans = scanner.nextLine();
		if (ans != null) {
			
		}
	}
	
	public static String printMainMenu(User user, Scanner scanner) {
		String ans = null;
		if(user.getClass() == Volunteer.class) {
			System.out.println("Welcome to the home page! You can do many things:");
			System.out.println("Messages (m)");
			System.out.println("Profile (p)");
			System.out.println("Upcoming Events (e)");
			System.out.println("Find Event (f)");
			System.out.println("Log Out (q)");
			System.out.println("What would you like to do ?");
		} else {
			System.out.println("Welcome to the home page! What would you like to do?");
			System.out.println("Messages (m)");
			System.out.println("Profile (p)");
			System.out.println("Upcoming Events (e)");
			System.out.println("Create Event (c)");
			System.out.println("Log Out (q)");
			System.out.println("What would you like to do ?");
		}
		ans = scanner.nextLine();
		return ans;
	}
	
	public static void inboxMenu(User curUser, ManagementSystem ms1, Scanner scanner) {
		String ans = "";
		System.out.println("Inbox:");
		System.out.println("Press 'h' to go to the main menu");
		System.out.println("Press 'n' for new message");
		System.out.println("Press the message number for details.");
		System.out.println("------------------------------------ ");
		int x = 1;
		for (Message message : curUser.getInbox()) {
			System.out.println(x+") "+ message.getTitle()+" from: "+message.getFromField().getName());
			x++;
		}
		ans = scanner.nextLine();
		if (ans.equals("h")) {
		} else if (ans.equals("n")) {
			Message mNew = new Message(); 
			System.out.println("What username do you want to send this to? ");
			ans = scanner.nextLine();
			mNew.addToField(findUser(ans, ms1));
			mNew.setFromField(curUser);
			System.out.println("What title of your message? ");
			ans = scanner.nextLine();
			mNew.setTitle(ans);
			System.out.println("What body of your message? ");
			ans = scanner.nextLine();
			mNew.setBody(ans);
			mNew.sendMessageToAddressed();	
		} else if (isNumeric(ans)) {
			Message email = curUser.getInbox().get(Integer.parseInt(ans) - 1);
			printMessage(email, scanner);
		} else {
			System.out.println("Not a valid input, try again.");
		}
	}
	
	public static User findUser(String username, ManagementSystem ms1) {
		for (User user : ms1.getUsers()) {
			if (user.getUserName().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public static User createAccount(ManagementSystem ms1, Scanner scanner) {
		System.out.println("Lets make an account!");
		System.out.println("Are you a volunteer (v) or coordinator (c)? ");
		String ans = scanner.nextLine();
		if (ans.equals("v")) {
			Volunteer v = new Volunteer(); 
			System.out.println("Whats your name?");
			ans = scanner.nextLine();
			v.setName(ans);
			System.out.println("Whats your username?");
			ans = scanner.nextLine();
			if (findUser(ans, ms1) != null) {
				System.out.println("Username already exists, try again.");
				return null;
			} else {
				v.setUserName(ans);
				System.out.println("Whats your password?");
				ans = scanner.nextLine();
				v.setPassword(ans);
				return v;
			}
		} else if (ans.equals("c")) {
			Coordinator c = new Coordinator(); 
			System.out.println("Whats your name?");
			ans = scanner.nextLine();
			c.setName(ans);
			System.out.println("Whats your username?");
			ans = scanner.nextLine();
			c.setName(ans);
			System.out.println("Whats your password?");
			ans = scanner.nextLine();
			c.setPassword(ans);
			return c;
		} else {
			System.out.println("Not a valid input, try again.");
			return null;
		}
	}
	
	public static void profileMenu(User curUser, Scanner scanner) {
		String ans = "";
		System.out.println(curUser.getName()+"'s Profile:");
		System.out.println("Press 'h' to go to the main menu");
		System.out.println("------------------------------------ ");
		System.out.println("Name: "+curUser.getName());
		System.out.println("Username: "+curUser.getUserName());
		if(curUser.getClass() == Volunteer.class) {
			System.out.println("Skills:");
			Volunteer v = (Volunteer) curUser;
			for (String skill : v.getSkills()) {
				System.out.println(skill);
			}
			System.out.println("Interests:");
			for (String interest : v.getInterests()) {
				System.out.println(interest);
			}
		} else {
			Coordinator c = (Coordinator) curUser;
			System.out.println("Organization:"+ c.getOrganization());
		}
		while(true) {
			ans = scanner.nextLine();
			if (ans.equals("h")) {
				return;
			}
		}
	}
	
	public static void eventsMenu(User curUser, Scanner scanner) {
		String ans = "";
		System.out.println(curUser.getName()+"'s Upcoming Events:");
		System.out.println("Press 'h' to go to the main menu");
		System.out.println("------------------------------------ ");
		for (Event e : curUser.getEvents()) {
			System.out.println(e.getTitle());
			System.out.println("Coordinator: " + e.getCoordinator().getName());
			System.out.println("Location: " + e.getLocation());
			System.out.println("Required Skills:");
			for (String skill : e.getRequiredSkills()) {
				System.out.println(skill);
			}
			System.out.println("Interests:");
			for (String inter : e.getInterestCategory()) {
				System.out.println(inter);
			}
			System.out.println("Extra Info:");
			for (String info : e.getExtraInfo()) {
				System.out.println(info);
			}
			System.out.println();
		}
		while(true) {
			ans = scanner.nextLine();
			if (ans.equals("h")) {
				return;
			}
		}
	}
	
	public static void findEventMenu(User curUser, ManagementSystem ms1, Scanner scanner) {
		String ans = "";
		System.out.println("Find an Event!");
		System.out.println("Press 'h' to go to the main menu");
		System.out.println("Press the event number to join!");
		System.out.println("------------------------------------ ");
		int x = 0;
		for (Event e : ms1.getEvents()) {
			x++;
			System.out.println(x+") "+ e.getTitle());
			System.out.println("Coordinator: " + e.getCoordinator().getName());
			System.out.println("Location: " + e.getLocation());
			System.out.println("Required Skills:");
			for (String skill : e.getRequiredSkills()) {
				System.out.println(skill);
			}
			System.out.println("Interests:");
			for (String inter : e.getInterestCategory()) {
				System.out.println(inter);
			}
			System.out.println("Extra Info:");
			for (String info : e.getExtraInfo()) {
				System.out.println(info);
			}
			System.out.println();
		}
		ans = scanner.nextLine();
		if (isNumeric(ans)) {
			Event e = ms1.getEvents().get(Integer.parseInt(ans) - 1);
			curUser.addEvent(e);
		} else {
			System.out.println("Not a valid input, try again.");
		}
		System.out.println("All done! Press 'h' to go to the main menu");
		while(true) {
			ans = scanner.nextLine();
			if (ans.equals("h")) {
				return;
			}
		}
	}

	public static void createEventMenu(User curUser, ManagementSystem ms1, Scanner scanner) {
		String ans = "";
		System.out.println("Create an Event!");
		System.out.println("Press 'h' to go to the main menu");
		System.out.println("------------------------------------ ");
		Event e1 = new Event(); 
		System.out.println("What is the Event Title: ");
		ans = scanner.nextLine();
		e1.setTitle(ans);
		System.out.println("What is the Event Location: ");
		ans = scanner.nextLine();
		e1.setLocation(ans);
		System.out.println("Choose a required Skill: ");
		e1.addRequiredSkill(chooseSkill(ms1, scanner));
		System.out.println("Add extra info: ");
		ans = scanner.nextLine();
		e1.addExtraInfo(ans);
		System.out.println("Choose an interest: ");
		e1.addInterestCategory(chooseInterest(ms1, scanner));
		ms1.addEvent(e1);
		curUser.addEvent(e1);
		System.out.println("All done! Press 'h' to go to the main menu");
		while(true) {
			ans = scanner.nextLine();
			if (ans.equals("h")) {
				return;
			}
		}
	}
	
	public static String chooseSkill(ManagementSystem ms1, Scanner scanner) {
		int x = 1;
		String ans = "";
		System.out.println("Press the skill number to select: ");
		for (String s : ms1.getSupportedSkills()) {
			System.out.println(x+") "+ s);
			x++;
		}
		ans = scanner.nextLine();
		if (isNumeric(ans)) {
			return ms1.getSupportedSkills().get(Integer.parseInt(ans) - 1);
		} else {
			System.out.println("Not a valid input, try again.");
		}
		return null;
	}
	
	public static String chooseInterest(ManagementSystem ms1, Scanner scanner) {
		int x = 1;
		String ans = "";
		System.out.println("Press the interest number to select: ");
		for (String s : ms1.getSupportedInterests()) {
			System.out.println(x+") "+ s);
			x ++;
		}
		ans = scanner.nextLine();
		if (isNumeric(ans)) {
			return ms1.getSupportedInterests().get(Integer.parseInt(ans) - 1);
		} else {
			System.out.println("Not a valid input, try again.");
		}
		return null;
	}
	
	public static boolean isCorrectPassword(String password, User user) {
		if(user.getPassword().equals(password)) {
			System.out.println("Successfully logged on! Welcome " + user.getName() + "!");
			return true;
		} else {
			System.out.println("Password does not match. Please try again.");
			return false;
		}
	}
}
