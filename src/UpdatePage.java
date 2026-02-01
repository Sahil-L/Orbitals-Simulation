import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public class UpdatePage extends JPanel implements ActionListener{
	
	//the attributes of the update page
	private JButton addBody, delBody, modBody, changeTimeSpeed, saveData, showGraph, explanation, submitAdd, submitDelete, submitModify, submitXpos, submitYpos, submitXvel, submitYvel, submitColour, submitMass, submitRadius, submitSpeed, submitOrbit;
	public JButton returnBack, exit;
	private JPanel inputArea;
	private JTextField xPos, yPos, xVel, yVel, colour, type, mass, name, timeSpeed, radius, power;
	private String userXPos, userYPos, userXVel, userYVel, userColour, userType, userMass, userName, userTimeSpeed, userRadius, userPower;
	private JLabel label, infoxPos, infoyPos, infoxVel, infoyVel, infocolour, infotype, infomass, infopower, infoname, inforadius ,infoplanet, infostar, infoasteroid, labelExplanation;
	private Space space;
	public LinkedList<String> namesList = new LinkedList<String>();
	public DefaultListModel<String> namesDefault, characteristics, speeds, showOrbit;
	public JList<String> names, options, speedOptions, orbitOptions;
	String updatedBody;
	
	//this function initialises all the components required for the update page and sets its required characteristics
	public UpdatePage(ActionListener listener, Space space) {
		
		setLayout(null);
		
		this.space = space;
		
		//initialises all the required buttons
		addBody = new JButton("Add body");
		addBody.setBackground(Color.BLACK);
		addBody.setForeground(Color.WHITE);
		addBody.setBounds(0, 80, 300, 50);
		addBody.setFont(new Font("Serif", Font.BOLD, 30));
		addBody.addActionListener(this);
        add(addBody);
        
        delBody = new JButton("Delete body");
		delBody.setBackground(Color.BLACK);
		delBody.setForeground(Color.WHITE);
		delBody.setBounds(0, 160, 300, 50);
		delBody.setFont(new Font("Serif", Font.BOLD, 30));
		delBody.addActionListener(this);
        add(delBody);
        
        modBody = new JButton("Modify body");
		modBody.setBackground(Color.BLACK);
		modBody.setForeground(Color.WHITE);
		modBody.setBounds(0, 240, 300, 50);
		modBody.setFont(new Font("Serif", Font.BOLD, 30));
		modBody.addActionListener(this);
        add(modBody);
        
        changeTimeSpeed = new JButton("Change time speed");
        changeTimeSpeed.setBackground(Color.BLACK);
        changeTimeSpeed.setForeground(Color.WHITE);
        changeTimeSpeed.setBounds(0, 320, 300, 50);
        changeTimeSpeed.setFont(new Font("Serif", Font.BOLD, 30));
        changeTimeSpeed.addActionListener(this);
        add(changeTimeSpeed);
        
        saveData = new JButton("Save simulation");
        saveData.setBackground(Color.BLACK);
        saveData.setForeground(Color.WHITE);
        saveData.setBounds(0, 400, 300, 50);
        saveData.setFont(new Font("Serif", Font.BOLD, 30));
        saveData.addActionListener(this);
        add(saveData);
        
        showGraph = new JButton("Toggle orbit pattern");
        showGraph.setBackground(Color.BLACK);
        showGraph.setForeground(Color.WHITE);
        showGraph.setBounds(0, 480, 300, 50);
        showGraph.setFont(new Font("Serif", Font.BOLD, 25));
        showGraph.addActionListener(this);
        add(showGraph);
        
        explanation = new JButton("How the simulation works");
        explanation.setBackground(Color.BLACK);
        explanation.setForeground(Color.WHITE);
        explanation.setBounds(0, 560, 300, 50);
        explanation.setFont(new Font("Serif", Font.BOLD, 22));
        explanation.addActionListener(this);
        add(explanation);
        
        returnBack = new JButton("Return");
        returnBack.setBackground(Color.BLACK);
        returnBack.setForeground(Color.WHITE);
        returnBack.setBounds(0, 640, 300, 50);
        returnBack.setFont(new Font("Serif", Font.BOLD, 30));
        returnBack.addActionListener(listener);
        add(returnBack);
        
        exit = new JButton("Exit simulation");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setBounds(0, 720, 300, 50);
        exit.setFont(new Font("Serif", Font.BOLD, 30));
        exit.addActionListener(listener);
        add(exit);
        
        submitAdd = new JButton("Submit");
        submitAdd.addActionListener(this);
        
        submitDelete = new JButton("Submit");
        submitDelete.addActionListener(this);
        
        submitModify = new JButton("Submit");
        submitModify.addActionListener(this);
        
        submitXpos = new JButton("Submit");
        submitXpos.addActionListener(this);
        
        submitYpos = new JButton("Submit");
        submitYpos.addActionListener(this);

        submitXvel = new JButton("Submit");
        submitXvel.addActionListener(this);
        
        submitYvel = new JButton("Submit");
        submitYvel.addActionListener(this);
        
        submitColour = new JButton("Submit");
        submitColour.addActionListener(this);
        
        submitMass = new JButton("Submit");
        submitMass.addActionListener(this);
        
        submitRadius = new JButton("Submit");
        submitRadius.addActionListener(this);
        
        submitSpeed = new JButton("Submit");
        submitSpeed.addActionListener(this);
        
        submitOrbit = new JButton("Submit");
        submitOrbit.addActionListener(this);
        
        
        //initialises the panel where the user can input information based on what they want to update
        inputArea = new JPanel();
        inputArea.setLayout(null);
        //inputArea.setLayout(new GridLayout(4,3));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        inputArea.setBorder(blackline);
        inputArea.setBounds(350, 80, 930, 870);
        add(inputArea);
        
        
        //initialises all the labels required
        infoxPos = new JLabel("add xPos, should be a double between -10.0 to 10.0 (AU)");
        infoxPos.setFont(new Font("Serif", Font.BOLD, 20));
        
        infoyPos = new JLabel("add yPos, should be a double between -10.0 to 10.0 (AU)");
        infoyPos.setFont(new Font("Serif", Font.BOLD, 20));
        
        infoxVel = new JLabel("add xVel, should be a double between -100.0 to 100.0 (km s-1)");
        infoxVel.setFont(new Font("Serif", Font.BOLD, 20));
        
        infoyVel = new JLabel("add yVel, should be a double between -100.0 to 100.0 (km s-1)");
        infoyVel.setFont(new Font("Serif", Font.BOLD, 20));
        
        infocolour = new JLabel("add colour, one of blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, white, yellow");
        infocolour.setFont(new Font("Serif", Font.BOLD, 14));
        
        infotype = new JLabel("add type, one of planet, star, asteroid");
        infotype.setFont(new Font("Serif", Font.BOLD, 20));
        
        infomass = new JLabel("add the mantissa of the mass, limits of mass dependant on type (kg)");
        infomass.setFont(new Font("Serif", Font.BOLD, 20));
        
        infopower = new JLabel("add the exponent of the mass, as a power of 10");
        infopower.setFont(new Font("Serif", Font.BOLD, 20));
        
        infoname = new JLabel("add name, should be 10 or less characters and not already taken");
        infoname.setFont(new Font("Serif", Font.BOLD, 20));
        
        inforadius = new JLabel("add radius, should an int between 1 and 1000");
        inforadius.setFont(new Font("Serif", Font.BOLD, 20));
        
        infoplanet = new JLabel("for planets the mass range is e21 to e28");
        infoplanet.setFont(new Font("Serif", Font.BOLD, 20));
        
        infostar = new JLabel("for star the mass is e28 or bigger");
        infostar.setFont(new Font("Serif", Font.BOLD, 20));
        
        infoasteroid = new JLabel("for asteroids the mass range is e21 or less");
        infoasteroid.setFont(new Font("Serif", Font.BOLD, 20));
        
        label = new JLabel();
        label.setBounds(300, 0, 1000, 100);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        
        labelExplanation = new JLabel();
        labelExplanation.setBounds(300, 0, 1000, 100);
        labelExplanation.setFont(new Font("Serif", Font.BOLD, 40));

        
        //initialises all the required text boxes
        xPos = new JTextField();
        
        yPos = new JTextField();
        
        xVel = new JTextField();
        
        yVel = new JTextField();

        colour = new JTextField();
        
        type = new JTextField();
        
        mass = new JTextField();

        power = new JTextField();

        name = new JTextField();

        timeSpeed = new JTextField();
        timeSpeed.setText("choose time speed");
        
        radius = new JTextField();
        
        
        setVisible(true);
		
	}
	
	public Space getSpace() {
		return space;
	}
	
	//this method deals with the placement of the required components if the user wants to delete a body
	public void deleteForm() {
		
		inputArea.removeAll();
		
		infoname.setText("Choose the name of the body to delete");
		inputArea.add(infoname);
		infoname.setBounds(240,0,800,40);

		inputArea.add(submitDelete);
		submitDelete.setBounds(800, 0, 100, 50);
		
		namesList = this.space.namesList;
        
        namesDefault = new DefaultListModel<>();
        
        for (int i = 0; i < namesList.size(); i++) {
        	namesDefault.addElement(namesList.get(i));
        }
        
        names = new JList<>(namesDefault);
		
		names.setBounds(10,10,200,500);
		inputArea.add(names);
		
		inputArea.setVisible(true);
		repaint();
		
	}
	
	//this method deals with the placement of the required components if the user wants to add a body
	public void addForm() {
		
		inputArea.removeAll();
		
		inputArea.add(xPos);
		xPos.setBounds(0, 0, 240, 40);
		
		infoxPos.setBounds(240,0,800,40);
		inputArea.add(infoxPos);
		
		inputArea.add(yPos);
		yPos.setBounds(0, 50, 240, 40);
		
		infoyPos.setBounds(240,50,800,40);
		inputArea.add(infoyPos);
		
		inputArea.add(xVel);
		xVel.setBounds(0, 100, 240, 40);
		
		infoxVel.setBounds(240,100,800,40);
		inputArea.add(infoxVel);
		
		inputArea.add(yVel);
		yVel.setBounds(0, 150, 240, 40);
		
		infoyVel.setBounds(240,150,800,40);
		inputArea.add(infoyVel);
		
		inputArea.add(colour);
		colour.setBounds(0, 200, 240, 40);
		
		infocolour.setBounds(240,200,800,40);
		inputArea.add(infocolour);
		
		inputArea.add(type);
		type.setBounds(0, 250, 240, 40);
		
		infotype.setBounds(240,250,800,40);
		inputArea.add(infotype);
		
		inputArea.add(mass);
		mass.setBounds(0, 300, 240, 40);
		
		infomass.setBounds(240,300,800,40);
		inputArea.add(infomass);
		
		inputArea.add(power);
		power.setBounds(0, 350, 240, 40);
		
		infopower.setBounds(240,350,800,40);
		inputArea.add(infopower);
		
		inputArea.add(name);
		name.setBounds(0, 400, 240, 40);
		
		infoname.setText("add name, should be 10 or less characters and not already taken");
		infoname.setBounds(240,400,800,40);
		inputArea.add(infoname);
		
		inputArea.add(radius);
		radius.setBounds(0, 450, 240, 40);
		
		inforadius.setBounds(240,450,800,40);
		inputArea.add(inforadius);
		
		inputArea.add(submitAdd);
		submitAdd.setBounds(800, 0, 100, 50);
		
		infoplanet.setBounds(0,500, 800, 40);
		inputArea.add(infoplanet);
		
		infostar.setBounds(0,550, 800, 40);
		inputArea.add(infostar);
		
		infoasteroid.setBounds(0,600, 800, 40);
		inputArea.add(infoasteroid);
		
		inputArea.setVisible(true);
		repaint();
		
	}
	
	//this method deals with the placement of the required components if the user wants to modify a body
	public void modifyForm() {
		
		inputArea.removeAll();
		
		namesList = this.space.namesList;
        
        namesDefault = new DefaultListModel<>();
        
        for (int i = 0; i < namesList.size(); i++) {
        	namesDefault.addElement(namesList.get(i));
        }
        
        names = new JList<>(namesDefault);
		
		names.setBounds(10,60,200,500);
		inputArea.add(names);
		
		infoname.setText("Choose the name of the body to modify");
		inputArea.add(infoname);
		infoname.setBounds(0,0,800,40);
		
		
		characteristics = new DefaultListModel<>();
		characteristics.addElement("xPos");
		characteristics.addElement("yPos");
		characteristics.addElement("xVel");
		characteristics.addElement("yVel");
		characteristics.addElement("mass");
		characteristics.addElement("colour");
		characteristics.addElement("radius");
		
		options = new JList<>(characteristics);
		
		JLabel infooptions = new JLabel("Choose characteristic to modify");
		infooptions.setFont(new Font("Serif", Font.BOLD, 20));
		
		options.setBounds(400, 60, 200,140);
		inputArea.add(options);
		
		infooptions.setBounds(400,0,500,40);
		inputArea.add(infooptions);
		
		inputArea.add(submitModify);
		submitModify.setBounds(800, 0, 100, 50);
		
		inputArea.setVisible(true);
		repaint();
		
	}
	
	
	//the required actions based on user input is performed below
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//based on the button clicked by the user, the content on the screen will change accordingly to the next present
		if (e.getSource() == addBody) {
			
			addForm();
		}
		
		if (e.getSource() == delBody) {
			
			deleteForm();
		}
		
		if (e.getSource() == modBody) {
			
			modifyForm();
		}
		
		//this method deals with the placement of the required components if the user wants to change the speed of simulated time
		if (e.getSource() == changeTimeSpeed) {
			inputArea.removeAll();
			
			speeds = new DefaultListModel<>();
			speeds.addElement("slow");
			speeds.addElement("med");
			speeds.addElement("fast");
			
			speedOptions = new JList<>(speeds);
			
			JLabel infospeeds = new JLabel("Choose the speed of the simulation");
			infospeeds.setFont(new Font("Serif", Font.BOLD, 20));
			
			speedOptions.setBounds(10, 60, 200,60);
			inputArea.add(speedOptions);
			
			infospeeds.setBounds(0,0,500,40);
			inputArea.add(infospeeds);
			
			
			//inputArea.add(timeSpeed);
			//timeSpeed.setBounds(0, 0, 240, 40);
			
			inputArea.add(submitSpeed);
			submitSpeed.setBounds(800, 0, 100, 50);
			
			inputArea.setVisible(true);
			repaint();
		}
		
		//this method deals with saving the contents of the simulation onto the device, based on the user's chosen location
		if (e.getSource() == saveData) {
			inputArea.removeAll();
			
			//saves data
			System.out.println("will save data");
			String text = space.saveData();
			
			//displays respective output
			label.setText(text);
			inputArea.add(label); 
			
			inputArea.setVisible(true);
			
			
			
			repaint();
		}
		
		//this method deals with the placement of the required components if the user wants to see the orbit of a body
		if (e.getSource() == showGraph) {
			inputArea.removeAll();
			
			infoname.setText("Choose the name of the body to show the orbit patterm of");
			inputArea.add(infoname);
			infoname.setBounds(0,0,800,40);

			inputArea.add(submitOrbit);
			submitOrbit.setBounds(800, 0, 100, 50);
			
			namesList = this.space.namesList;
	        
	        namesDefault = new DefaultListModel<>();
	        
	        for (int i = 0; i < namesList.size(); i++) {
	        	namesDefault.addElement(namesList.get(i));
	        }
	        
	        names = new JList<>(namesDefault);
			
			names.setBounds(10,50,200,500);
			inputArea.add(names);
			
			showOrbit = new DefaultListModel<>();
			showOrbit.addElement("show orbit");
			showOrbit.addElement("dont show orbit");
			
			orbitOptions = new JList<>(showOrbit);
			
			JLabel infoorbit = new JLabel("Choose to show or not show orbit");
			infoorbit.setFont(new Font("Serif", Font.BOLD, 20));
			
			orbitOptions.setBounds(400, 200, 200,40);
			inputArea.add(orbitOptions);
			
			infoorbit.setBounds(400,150,500,40);
			inputArea.add(infoorbit);
			
			inputArea.setVisible(true);
			repaint();
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for adding a body
		if (e.getSource() == submitAdd) {
			inputArea.remove(label);
			repaint();
			
			userXPos = xPos.getText();
			userYPos = yPos.getText();
			userXVel = xVel.getText();
			userYVel = yVel.getText();
			userColour = colour.getText();
			userType = type.getText();
			userMass = mass.getText();
			userName = name.getText();
			userRadius = radius.getText();
			userPower = power.getText();
			
			String text;
			text = space.addBody(userXPos, userYPos, userXVel, userYVel, userColour, userType, userMass, userName, userRadius, userPower);
			if (text.equals("valid, celestial body created")) {
				label.setText(text);
			}
			else {
				label.setText("input error: " + text);
			}
			label.setBounds(0, 650, 1000, 100);
			inputArea.add(label);
			//inputArea.setVisible(true);
			repaint();
			
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for deleting a body
		if (e.getSource() == submitDelete) {
			
			if (names.getSelectedIndex() != -1) {
				System.out.println(names.getSelectedValue());
				space.delete(names.getSelectedValue());
				label.setText(names.getSelectedValue() + " has been deleted");
				deleteForm();
				
			}
			else {
				label.setText("you have not selected a body");
				
			}
			label.setBounds(0, 650, 1000, 100);
			inputArea.add(label);
			repaint();
			
			
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body
		if (e.getSource() == submitModify) {
			label.setText("");
			JLabel modLabel;
			modLabel = new JLabel();
	        //modLabel.setBounds(300, 0, 1000, 100);
	        modLabel.setFont(new Font("Serif", Font.BOLD, 50));
			
	        //there is validation put in place to ensure that the input is only processed as long as the user add chosen and add all the required information
			//the input area is also modified by adding the required components based on characteristic chosen to modify
	        if (names.getSelectedIndex() != -1) {
				
				if (options.getSelectedIndex() != -1) {
					updatedBody = names.getSelectedValue();
					modLabel.setText(updatedBody + "  " + options.getSelectedValue());
					
					String option = options.getSelectedValue();
					if (option.equals("xPos")) {
						
						modifyForm();
						
						System.out.println("xPos");
						inputArea.add(xPos);
						xPos.setBounds(250, 300, 240, 40);
						
						inputArea.add(infoxPos);
						infoxPos.setBounds(250,250,800,40);
						
						inputArea.add(submitXpos);
						submitXpos.setBounds(500, 300, 100, 40);
					}
					
					if (option.equals("yPos")) {
						
						modifyForm();
						
						System.out.println("yPos");
						inputArea.add(yPos);
						yPos.setBounds(250, 300, 240, 40);
						
						inputArea.add(infoyPos);
						infoyPos.setBounds(250,250,800,40);
						
						inputArea.add(submitYpos);
						submitYpos.setBounds(500, 300, 100, 40);
	
					}
					
					if (option.equals("xVel")) {
						
						modifyForm();
						
						System.out.println("xVel");
						inputArea.add(xVel);
						xVel.setBounds(250, 300, 240, 40);
						
						inputArea.add(infoxVel);
						infoxVel.setBounds(250,250,800,40);
						
						inputArea.add(submitXvel);
						submitXvel.setBounds(500, 300, 100, 40);
	
					}
					
					if (option.equals("yVel")) {
						
						modifyForm();
						
						System.out.println("yVel");
						inputArea.add(yVel);
						yVel.setBounds(250, 300, 240, 40);
						
						inputArea.add(infoyVel);
						infoyVel.setBounds(250,250,800,40);
						
						inputArea.add(submitYvel);
						submitYvel.setBounds(500, 300, 100, 40);
	
					}
					
					if (option.equals("radius")) {
						
						modifyForm();
						
						System.out.println("radius");
						inputArea.add(radius);
						radius.setBounds(250, 300, 240, 40);
						
						inputArea.add(inforadius);
						inforadius.setBounds(250,250,800,40);
						
						inputArea.add(submitRadius);
						submitRadius.setBounds(500, 300, 100, 40);
	
					}
					
					if (option.equals("colour")) {
						
						modifyForm();
						
						System.out.println("colour");
						inputArea.add(colour);
						colour.setBounds(250, 300, 240, 40);
						
						inputArea.add(infocolour);
						infocolour.setBounds(250,250,800,40);
						
						inputArea.add(submitColour);
						submitColour.setBounds(500, 300, 100, 40);
						
					}
					
					if (option.equals("mass")) {
						
						modifyForm();
						
						System.out.println("mass");
						inputArea.add(mass);
						mass.setBounds(250, 300, 240, 40);
						
						inputArea.add(infomass);
						infomass.setBounds(250,250,800,40);
						
						inputArea.add(power);
						power.setBounds(250, 400, 240, 40);
						
						inputArea.add(infopower);
						infopower.setBounds(250,350,800,40);
						
						infoplanet.setBounds(250,470, 800, 40);
						inputArea.add(infoplanet);
						
						infostar.setBounds(250,520, 800, 40);
						inputArea.add(infostar);
						
						infoasteroid.setBounds(250,570, 800, 40);
						inputArea.add(infoasteroid);
						
						inputArea.add(submitMass);
						submitMass.setBounds(500, 400, 100, 40);
						
					}
					
					
					
					
				}
				else {
					label.setText("you have not selected a characteristic");
				}
				
			}
			else {
				label.setText("you have not selected a body");
			}
			
			modLabel.setBounds(0, 600, 1000, 100);
			inputArea.add(modLabel);
			
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();
			
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's x position
		if (e.getSource() == submitXpos) {
			
			label.setText(space.updateBody(xPos.getText(), "xPos", updatedBody, ""));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's y position
		if (e.getSource() == submitYpos) {
			
			label.setText(space.updateBody(yPos.getText(), "yPos", updatedBody, ""));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's x velocity
		if (e.getSource() == submitXvel) {
			
			label.setText(space.updateBody(xVel.getText(), "xVel", updatedBody, ""));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's y velocity
		if (e.getSource() == submitYvel) {
			
			label.setText(space.updateBody(yVel.getText(), "yVel", updatedBody, ""));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's radius
		if (e.getSource() == submitRadius) {
			
			label.setText(space.updateBody(radius.getText(), "radius", updatedBody, ""));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's colour
		if (e.getSource() == submitColour) {
			
			label.setText(space.updateBody(colour.getText(), "colour", updatedBody, ""));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying a body's mass
		if (e.getSource() == submitMass) {
			
			label.setText(space.updateBody(mass.getText(), "mass", updatedBody, power.getText()));
			label.setBounds(0, 750, 1000, 100);
			inputArea.add(label);
			repaint();	
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for modifying the speed of simulated time
		if (e.getSource() == submitSpeed) {
			
			if (speedOptions.getSelectedIndex() != -1) {
				System.out.println(speedOptions.getSelectedValue());
				String speed = speedOptions.getSelectedValue();
				
				if (speed.equals("slow")) {
					space.timeInterval = 100;
				}
				else if (speed.equals("med")) {
					space.timeInterval = 20;
				}
				else if (speed.equals("fast")) {
					space.timeInterval = 1;
				}
				
				label.setText("the speed is " + speed);
				
			}
			else {
				label.setText("you have not selected a speed");
				
			}
			label.setBounds(0, 650, 1000, 100);
			inputArea.add(label);
			repaint();
			
		}
		
		//this method takes in the user input and does the respective actions if the user submits information for toggling the orbit pattern of a body
		if (e.getSource() == submitOrbit) {
			boolean toDraw = false;
			
			if (names.getSelectedIndex() != -1) {
				
				if (orbitOptions.getSelectedIndex() != -1) {
					
					String draw = orbitOptions.getSelectedValue();
					if (draw.equals("show orbit")) {
						toDraw = true;
						label.setText(names.getSelectedValue() + "'s orbit will be shown");
					}
					else if (draw.equals("dont show orbit")) {
						toDraw = false;
						label.setText(names.getSelectedValue() + "'s orbit will not be shown");
					}
					
					space.draw(names.getSelectedValue(), toDraw);
					
				}
				else {
					label.setText("orbit decision not chosen");
				}
				
				
			}
			else {
				label.setText("you have not selected a body");
				
			}
			label.setBounds(0, 650, 1000, 100);
			inputArea.add(label);
			repaint();
			
		}
		
		//this method displays the respective information if the user wants to see an explanation of the simulation
		if (e.getSource() == explanation) {
			inputArea.removeAll();
			
			labelExplanation.setText("<html>The equation F = GMm/r^2 is used to find the gravitational force exterted on the planet.<br/><br/>The gravitational force is found between every pair combination of bodies in the simulation.<br/><br/>For each pair, the angle between the bodies is calculated to have forces in the x and y component to update the x and y position individually<br/><br/>The position is updated in each dimension to use the force and mass to calculate acceleration, the velocity of the body and the current position</html>");
			labelExplanation.setBounds(0, 0, 930, 870);
			inputArea.add(labelExplanation);
			repaint();
			
		}
		
	}
		

}

