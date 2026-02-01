import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;
//import org.json.*;
import javax.swing.JFileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Space {
	
	//the attributes of the space class
	int size = 5000;
	double posRange = 10.0;
	double velRange = 100.0;
	int radiusRange = 1000;
	String[] colourList = {"blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow"};
	//double maxMass = Math.pow(10, 20);
	LinkedList<String> namesList = new LinkedList<String>();
	LinkedList<CelestialObject> celestialBodies = new LinkedList<CelestialObject>();
	double scalefactor = Math.pow(10, 12);
	double AU = 149.6e6 * 1000;
	double G = 6.67428e-11;
	//One AU is around 100 pixels in the display
	double SCALE = 250 / AU;
	double TIMESTEP = 3600 * 24; //length of one day
	int timeInterval = 20;
	
	//star min mass : 1 * Math.pow(10,28)
	//planet mass range: 1 * Math.(10,21) to 1 * Math.pow(10,28)
	//asteroid mass max: 1 * Math.(10,21)
	
	
	public Space() {
		System.out.println("start");
		
		//bodies used for testing
		
		//Star sun = new Star(0,0, 0,0, "yellow", 1.98892 * Math.pow(10, 30) ,"sun", 30);
		//celestialBodies.add(sun);
		//namesList.add("sun");
		
		//Planet earth = new Planet(1, 0, 0, -29.783, "blue", 5.9742 * Math.pow(10, 24) ,"earth", 16);
		//celestialBodies.add(earth);
		//namesList.add("earth");
		
		//Planet mars = new Planet(-1.524,0, 0,24.077, "red", 6.39 * Math.pow(10, 23) ,"mars", 12);
		//celestialBodies.add(mars);
		//namesList.add("mars");
		
		//Planet mercury = new Planet(0.387,0, 0,47.4, "darkGray", 0.330 * Math.pow(10, 23) ,"mercury", 8);
		//celestialBodies.add(mercury);
		//namesList.add("mercury");
				
		//Planet venus = new Planet(0.723,0, 0,-35.02, "white", 4.8685 * Math.pow(10, 24) ,"venus", 14);
		//celestialBodies.add(venus);
		//namesList.add("venus");
		
		//Planet mars = new Planet(1,1,10,-10, "red", 1 * Math.pow(10, 24), "Mars", 10);
		//celestialBodies.add(mars);
		//namesList.add("Mars");
		
		//Star sun = new Star(0,-1,1,-7, "yellow", 5 * Math.pow(10, 30), "Sun", 25);
		//celestialBodies.add(sun);
		//namesList.add("Sun");
		
		
		
	}
	
	//this method takes in the user's input for adding a body from the update page and checks if the limit of bodies in the simulation has been reached
	public String addBody(String xPos, String yPos, String xVel, String yVel, String colour, String type, String mass, String name, String radius, String power) {
		String text;
		//namesList.add("test");
		
		if (namesList.size() > 20) {
			text = "limit of celestial bodies reached";
		}
		else {
			text = createBody(xPos, yPos, xVel, yVel, colour, type, mass, name, radius, power);
		}
		
		
		
		
		return text;
	}
	
	//this method deals with validating the user's inputs for all the different required characteristics to create a body
	//the body is created if all inputs are valid, the function will return, stating the respective error otherwise
	public String createBody(String xPos, String yPos, String xVel, String yVel, String colour, String type, String mass, String name, String radius, String power) {
		boolean passed = false;
		int tempInt;
		double tempDouble;
		double maxMass, minMass;
		name = name.trim();
		
		int radiusPass;
		double massPass, xPosPass, yPosPass, xVelPass, yVelPass;
		
		

		//type validation is done by seeing if the program catches an error if parsing is attempted
		try {
			tempDouble = Double.parseDouble(xPos);
	    } catch (NumberFormatException e) {
	        return "xPos, not Double";
	    }
		if (tempDouble > posRange || tempDouble < -posRange ) {
			return "xPos, out of -" +posRange+ " to " +posRange+ " range";
		}
		xPosPass = tempDouble; //xPos is in Astronomical units
		
		try {
			tempDouble = Double.parseDouble(yPos);
	    } catch (NumberFormatException e) {
	        return "yPos, not Double";
	    }
		//range validation is done by seeing if the input value is within a predetermined range
		if (tempDouble > posRange || tempDouble < -posRange ) {
			return "yPos, out of -" +posRange+ " to " +posRange+ " range";
		}
		yPosPass = tempDouble; //yPos is in Astronomical units
		
		try {
			tempDouble = Double.parseDouble(xVel);
	    } catch (NumberFormatException e) {
	        return "xVel, not Double";
	    }
		if (tempDouble > velRange || tempDouble < -velRange ) {
			return "xVel, out of -" +velRange+ " to " +velRange+ " range";
		}
		xVelPass = tempDouble; //xVel is in km per second
		
		try {
			tempDouble = Double.parseDouble(yVel);
	    } catch (NumberFormatException e) {
	        return "yVel, not Double";
	    }
		if (tempDouble > velRange || tempDouble < -velRange ) {
			return "yVel, out of -" +velRange+ " to " +velRange+ " range";
		}
		yVelPass = tempDouble; //yVel is in km per second
		
		//colour validation is done by checking the input against a range of predetermine set of valid colours
		for (String i : colourList) {
			if (colour.equals(i)) {
				passed = true;
				break;
			}
		}
		if (passed == false) {
			return "colour, not a colour";
		}
		
		if (name.isEmpty()) {
			return "name, name is blank";
		}
		else if (name.length() > 10) {
			return "name, name length more than 10";
		}
		//name validation checks the names list to see if the name is already taken or not
		for (String j : namesList) {
			if (name.equals(j)) {
				return "name, name already taken";
			}
		}
		
		try {
			tempInt = Integer.parseInt(radius);
	    } catch (NumberFormatException e) {
	        return "radius, not int";
	    }
		if (tempInt < 1 || tempInt > radiusRange ) {
			//return "mass, out of " +(scalefactor*10)+ " to " +maxMass+  " range";
			return "radius, out of 1 to " +radiusRange+  " range";
		}
		radiusPass = tempInt;
		
		//based on the type that the user chooses their celestial body to be, slightly different validation is done for the mass input
		//because each type of celestial body has different predetermined mass ranges
		if (type.equals("planet")) {
			minMass = Math.pow(10, 21);
			maxMass = Math.pow(10, 28);
			
			try {
		        tempDouble = Double.parseDouble(mass);
		    } catch (NumberFormatException e) {
		        return "mass, not double";
		    }
			
			try {
		        tempInt = Integer.parseInt(power);
		    } catch (NumberFormatException e) {
		        return "power, not int";
		    }
			
			tempDouble = tempDouble * Math.pow(10, tempInt);
			
			if (tempDouble < minMass || tempDouble > maxMass ) {
				return "mass, out of " +minMass+ " to " +maxMass+  " range";
			}
			massPass = tempDouble;
			
			System.out.println("yes");
			
			//as the type chosen is planet, the body will be created as an instance of the planet class
			Planet body = new Planet(xPosPass, yPosPass, xVelPass, yVelPass, colour, massPass, name, radiusPass);
			celestialBodies.add(body);
			namesList.add(name);
		}
		else if (type.equals("star")) {
			minMass = Math.pow(10, 28);
			
			try {
		        tempDouble = Double.parseDouble(mass);
		    } catch (NumberFormatException e) {
		        return "mass, not double";
		    }
			
			try {
		        tempInt = Integer.parseInt(power);
		    } catch (NumberFormatException e) {
		        return "power, not int";
		    }
			
			tempDouble = tempDouble * Math.pow(10, tempInt);
			
			if (tempDouble < minMass) {
				return "mass, needs to be more than " +minMass;
			}
			massPass = tempDouble;
			
			System.out.println("yes");
			
			Star body = new Star(xPosPass, yPosPass, xVelPass, yVelPass, colour, massPass, name, radiusPass);
			celestialBodies.add(body);
			namesList.add(name);
		}
		else if (type.equals("asteroid")) {
			maxMass = Math.pow(10, 21);
			
			try {
		        tempDouble = Double.parseDouble(mass);
		    } catch (NumberFormatException e) {
		        return "mass, not double";
		    }
			
			try {
		        tempInt = Integer.parseInt(power);
		    } catch (NumberFormatException e) {
		        return "power, not int";
		    }
			
			tempDouble = tempDouble * Math.pow(10, tempInt);
			
			if (tempDouble > maxMass) {
				return "mass, needs to be less than " +maxMass;
			}
			massPass = tempDouble;
			
			System.out.println("yes");
			
			Asteroid body = new Asteroid(xPosPass, yPosPass, xVelPass, yVelPass, colour, massPass, name, radiusPass);
			celestialBodies.add(body);
			namesList.add(name);
			
		}
		else if (type.equals("satellite")) {
			//return "satellite";
		}
		else {
			return "type, invalid type";
		}
		
		//this return message is sent to indicate that all the user inputs were valid	
		return "valid, celestial body created";
		
	}
	
	//this method takes in the inputs from the update page for when the user wants to modify a body
	//this method deals with validating the user's inputs for all the different required characteristics to modify a body
    //the body is updated accordingly if all inputs are valid, the function will return, stating the respective error otherwise
	public String updateBody(String input, String characteristic, String name, String input2) {
		
		boolean passed = false;
		boolean found = false;
		int tempInt;
		double tempDouble;
		double maxMass, minMass;
		CelestialObject body = celestialBodies.getFirst();
		
		int pointer = 99999;
		double massPass, xPosPass, yPosPass, xVelPass, yVelPass;
		
		
		//this validation is present to ensure that the user does not choose a body that no longer exists
		//it also flags the body to be modified and its position in the linked list so that after the changes are done, the updated body overrides
		//the correct body
		for (int i = 0; i < namesList.size(); i++) {
			
			if (name.equals(namesList.get(i))) {
				body = celestialBodies.get(i);
				pointer = i;
				found = true;
			}
			
		}
		
		if (found == false) {
			return "body no longer exists";
		}
		
		
		if (characteristic.equals("xPos")) {
			
			try {
				tempDouble = Double.parseDouble(input);
		    } catch (NumberFormatException e) {
		        return "xPos, not Double";
		    }
			if (tempDouble > posRange || tempDouble < -posRange ) {
				return "xPos, out of -" +posRange+ " to " +posRange+ " range";
			}
			//xPos is in Astronomical units so the input is converted
			tempDouble = tempDouble * AU;
			body.xPos = tempDouble;
			//updates are set
			celestialBodies.set(pointer, body);
			
		}
		
		else if (characteristic.equals("yPos")) {
			
			try {
				tempDouble = Double.parseDouble(input);
		    } catch (NumberFormatException e) {
		        return "yPos, not Double";
		    }
			if (tempDouble > posRange || tempDouble < -posRange ) {
				return "yPos, out of -" +posRange+ " to " +posRange+ " range";
			}
			//yPos is in Astronomical units so the input is converted
			tempDouble = tempDouble * AU;
			body.yPos = tempDouble;
			//updates are set
			celestialBodies.set(pointer, body);
			
		}
		
		else if (characteristic.equals("xVel")) {
			
			try {
				tempDouble = Double.parseDouble(input);
		    } catch (NumberFormatException e) {
		        return "xVel, not Double";
		    }
			if (tempDouble > velRange || tempDouble < -velRange ) {
				return "xVel, out of -" +velRange+ " to " +velRange+ " range";
			}
			//xVel is in km per second so the input is converted
			tempDouble = tempDouble * 1000; 
			body.xVel = tempDouble;
			//updates are set
			celestialBodies.set(pointer, body);
			
		}
		
		else if (characteristic.equals("yVel")) {
			
			try {
				tempDouble = Double.parseDouble(input);
		    } catch (NumberFormatException e) {
		        return "yVel, not Double";
		    }
			if (tempDouble > velRange || tempDouble < -velRange ) {
				return "yVel, out of -" +velRange+ " to " +velRange+ " range";
			}
			//yVel is in km per second so the input is converted
			tempDouble = tempDouble * 1000; 
			body.yVel = tempDouble;
			//updates are set
			celestialBodies.set(pointer, body);
			
		}
		
		else if (characteristic.equals("radius")) {
			
			try {
				tempInt = Integer.parseInt(input);
		    } catch (NumberFormatException e) {
		        return "radius, not int";
		    }
			if (tempInt < 1 || tempInt > radiusRange ) {
				//return "mass, out of " +(scalefactor*10)+ " to " +maxMass+  " range";
				return "radius, out of 1 to " +radiusRange+  " range";
			}
			body.radius = tempInt;
			//updates are set
			celestialBodies.set(pointer, body);
			
		}
		
		else if (characteristic.equals("colour")) {
			
			for (String i : colourList) {
				if (input.equals(i)) {
					passed = true;
					break;
				}
			}
			if (passed == false) {
				return "colour, not a colour";
			}
			body.colour = input;
			//updates are set
			celestialBodies.set(pointer, body);
			
		}
		
		else if (characteristic.equals("mass")) {
			
			//this validation checks what class the body to be modified is off because different celestial bodies have different valid mass ranges
			if (body instanceof Planet) {
				System.out.println("it is a planet!");
				minMass = Math.pow(10, 21);
				maxMass = Math.pow(10, 28);
				
				try {
			        tempDouble = Double.parseDouble(input);
			    } catch (NumberFormatException e) {
			        return "mass, not double";
			    }
				
				try {
			        tempInt = Integer.parseInt(input2);
			    } catch (NumberFormatException e) {
			        return "power, not int";
			    }
				
				tempDouble = tempDouble * Math.pow(10, tempInt);
				
				if (tempDouble < minMass || tempDouble > maxMass ) {
					return "mass, out of " +minMass+ " to " +maxMass+  " range";
				}
				
				body.mass = tempDouble;
				//updates are set
				celestialBodies.set(pointer, body);
				
				
			}
			else if (body instanceof Star) {
				System.out.println("it is a star!");
				minMass = Math.pow(10, 28);
				
				try {
			        tempDouble = Double.parseDouble(input);
			    } catch (NumberFormatException e) {
			        return "mass, not double";
			    }
				
				try {
			        tempInt = Integer.parseInt(input2);
			    } catch (NumberFormatException e) {
			        return "power, not int";
			    }
				
				tempDouble = tempDouble * Math.pow(10, tempInt);
				
				if (tempDouble < minMass) {
					return "mass, needs to be more than " +minMass;
				}
				
				body.mass = tempDouble;
				//updates are set
				celestialBodies.set(pointer, body);
			}
			else if (body instanceof Asteroid) {
				System.out.println("it is an asteroid!");
				maxMass = Math.pow(10, 21);
				
				try {
			        tempDouble = Double.parseDouble(input);
			    } catch (NumberFormatException e) {
			        return "mass, not double";
			    }
				
				try {
			        tempInt = Integer.parseInt(input2);
			    } catch (NumberFormatException e) {
			        return "power, not int";
			    }
				
				tempDouble = tempDouble * Math.pow(10, tempInt);
				
				if (tempDouble > maxMass) {
					return "mass, needs to be less than " +maxMass;
				}
				
				body.mass = tempDouble;
				//updates are set
				celestialBodies.set(pointer, body);
				
			}
			
			
		}
		
		
		
		
		
		
		else {
			return "invalid characteristic";
		}
		
		//this return message is sent to indicate that all the user inputs were valid	
		return "valid, update made";
		
	}
	
	public LinkedList<CelestialObject> getCelestialBodies() {
		return celestialBodies;
	}

	
	//this method updates the position of the orbit and deletes the body if its position is outside the bounds of the simulation
	public void processOrbits() {
		CelestialObject body;
		
		//iterates through all bodies to make updates
		for (int i = 0; i < celestialBodies.size(); i++) {
			body = celestialBodies.get(i);
			
			//this conversion is done to scale the position of the body which is in AU into the dimensions of the window
			int x = (int)  (body.xPos * SCALE) + size / 2;
			int y = (int) (body.yPos * SCALE) + size / 2;
			
			//this validation compares the position of the body on the window against the permitted positions and deletes the body if required
			if (x > size || y > size || x < 0 || y < 0) {
				namesList.remove(body.name);
				celestialBodies.remove(body);
			}
			else {
				body.updatePosition(celestialBodies);
			}
			
			
		}
		
	}
	
	//this method deletes the chosen body from the simulation
	public void delete(String name) {
		
		for (int i = 0; i < namesList.size(); i++) {
			
			if (name.equals(namesList.get(i))) {
				namesList.remove(i);
				celestialBodies.remove(i);
				return;
			}
			
		}
		
	}
	
	//this method toggles whether or not the chosen body should have its orbit pattern displayed
	public void draw(String name, Boolean toDraw) {
		
		for (int i = 0; i < namesList.size(); i++) {
			
			if (name.equals(namesList.get(i))) {
				CelestialObject body = celestialBodies.get(i);
				body.drawOrbit = toDraw;
				celestialBodies.set(i, body);
				return;
			}
			
		}
		
	}
	
	//allows the user to choose a location where to save the file and checks to see if the file can be saved to this location
	public String saveData() {
		
		//collects the data that needs to be written into the save file
		JSONArray data = writeFile();
		
		
		JFileChooser chosenFile = new JFileChooser();
		
		//selects a file to save.
		int response = chosenFile.showSaveDialog(null);
		
		//checks if file path is valid
		if (response == JFileChooser.APPROVE_OPTION) {
			//gets file path
			File file = new File(chosenFile.getSelectedFile().getAbsolutePath());
			
			//writes to the file
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(data.toJSONString());
				writer.close();
			} catch (IOException e) {
				
				e.printStackTrace();
				return "Invalid file path chosen";
			}
		}
		else {
			return "File has not been saved";
		}
		
		return "File has been saved";
	
	}
	
	//adds the informations required in the save file
	@SuppressWarnings("unchecked")
	public JSONArray writeFile() {
		
		JSONArray savedBodiesList = new JSONArray();
		
		//iterates through all bodies
		for (int i = 0; i < celestialBodies.size(); i++) {
			
			//bodies' input values are added to the file (eg xpos is in the -10 to 10 scale instead of in terms of AU)
			CelestialObject object = celestialBodies.get(i);
			JSONObject body = new JSONObject();
			
			//based on the class of the body, the type is added
			if (object instanceof Star) {
				body.put("type", "star");
			}
			else if (object instanceof Planet) {
				body.put("type", "planet");
			}
			else if (object instanceof Asteroid) {
				body.put("type", "asteroid");
			}
			
			//all attributes of the body is added to the JSONObject
			body.put("xPos", "" + (object.xPos / AU));
			body.put("yPos", "" + (object.yPos / AU));
			body.put("xVel", "" + (object.xVel / 1000));
			body.put("yVel", "" + (object.yVel / 1000));
			body.put("colour", "" + object.colour);
			body.put("mass", "" + object.mass);
			body.put("name", object.name);
			body.put("radius", "" + object.radius);
			
			JSONObject bodyDetails = new JSONObject();
			bodyDetails.put("body", body);
			
			//the JSONObject is added to the JSONArray
			savedBodiesList.add(bodyDetails);
			
		}
		
		//the information of the save file is returned
		return savedBodiesList;
		
		
		
	}
	
	//validates the data from the file loaded and if valid, the saved simulation is loaded
	@SuppressWarnings("unchecked")
	public Boolean loadData() {
		
		JSONParser jsonParser = new JSONParser();
		JFileChooser chosenFile = new JFileChooser();
		Boolean error = false;
		
		//selects a file to load.
		int response = chosenFile.showOpenDialog(null);
		
		
		//checks if file path is valid
		if (response == JFileChooser.APPROVE_OPTION) {
			//gets file path
			File file = new File(chosenFile.getSelectedFile().getAbsolutePath());
			
			//reads the file
			try {
				FileReader reader = new FileReader(file);
				

				//reads the JSON file
				Object bodies = jsonParser.parse(reader);
				
				//adds the data to a JSON array
				JSONArray bodiesList = (JSONArray) bodies;
				System.out.println(bodiesList);
				
				//for each item in the JSONArray, each element of the item will be validated, and if valid, added to the simulation
				bodiesList.forEach( body -> checkBody( (JSONObject) body ));
				
				reader.close();
				
				
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				error = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				error = true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//identifies to the user that a non JSON file was chosen
				//System.out.println("An invalid file was chosen");
				error = true;
			}
		}
		//means that a file was not chosen
		else {
			return false;
		}
		
		//checks if there was a try/catch error, if there was, returns false
		if (error == false) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	//this method will check each the details of a JSONObject to see if its information is suitable to be added to the simulation
	public void checkBody(JSONObject bodyDetails) {
		
		JSONObject body = (JSONObject) bodyDetails.get("body");
		
		//the attributes of each body is collected
		String type = (String) body.get("type");
		
		String xPos = (String) body.get("xPos");
		
		String yPos = (String) body.get("yPos");
		
		String xVel = (String) body.get("xVel");
		
		String yVel = (String) body.get("yVel");
		
		String colour = (String) body.get("colour");
		
		String mass = (String) body.get("mass");
		
		String name = (String) body.get("name");
		
		String radius = (String) body.get("radius");
		
		//the attributes of the body is added into the method that creates a new body
		String text = addBody(xPos, yPos, xVel, yVel, colour, type, mass, name, radius, "0");
		
		
		
		
		
	}

}
