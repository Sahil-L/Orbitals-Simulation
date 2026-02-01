import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class CelestialObject {
	
	//the attributes of a celestial body
	String colour, name;
	double mass, xPos, yPos, xVel, yVel;
	int radius;
	private int timeInterval = 2000;
	private int timeIntervalSecond = timeInterval / 1000;
	LinkedList<double[]> orbit = new LinkedList<double[]>();
	int orbitSize = 2000; //old was 600
	double AU = 149.6e6 * 1000;
	double G = 6.67428e-11;
	//One AU is around 100 pixels in the display
	double SCALE = 250 / AU;
	double TIMESTEP = 3600 * 24; //length of one day
	int size = 5000;
	boolean drawOrbit = false;
	
	//the constructor function takes in the validated inputs from the user to create a body and assigns each to the respective attribute
	//of the celestial body
	public CelestialObject(double xPos, double yPos, double xVel, double yVel, String colour, double mass, String name, int radius) {
		
		//the required conversion is done to position (to be in AU) and velocity (to be in km s-1)
		this.xPos = xPos * AU;
		this.yPos = yPos * AU;
		this.xVel = xVel * 1000;
		this.yVel = yVel * 1000;
		this.colour = colour;
		this.mass = mass;
		this.name = name;
		this.radius = radius;
		
		
	}
	
	//test paint component, actual painting is done within the children classes
	public void paintComponent(Graphics g) {
		if (colour.equals("blue")) {
			g.setColor(Color.blue);
		}
		
		g.fillOval((int)xPos, (int) yPos, 100,100);
	}
	
	
	//this method calculates the gravitational force between this body and another body which it takes in
	public double[] attraction(CelestialObject other) {
			
			//the x and y components of the distance between the pair of bodies is found
			double distanceX = other.xPos - xPos;
			double distanceY = other.yPos - yPos;
			//the magnitude of the distance between the pair of bodies is found
			double distance = Math.pow(Math.pow(distanceX, 2) + Math.pow(distanceY, 2), 0.5);
			
			//the magnitude of the gravitational force is found using Newton's law of gravitational attraction
			double force = G * mass * other.mass / Math.pow(distance, 2);
			//the angle made between the 2 bodies is found
			double theta = Math.atan2(distanceY, distanceX);
			
			//using trigonometry, the x and y components of the force is found
			double forceX = Math.cos(theta) * force;
			double forceY = Math.sin(theta) * force;
			
			double[] forces = new double[2];
			forces[0] = forceX;
			forces[1] = forceY;
			
			
			return forces;
		}
	
	//this method updates the position of the celestial body based on interactions with all the other bodies in the simulation
	public void updatePosition(LinkedList<CelestialObject> celestialBodies) {
		
		//variables to track the total force exerted on the body
		double forceXTotal = 0;
		double forceYTotal = 0;
		
		double[] positions = new double[2];
		
		//the celestial body linked list is iterated through each celestial body in the list to calculate the force between the 2 bodies
		for (int i = 0; i < celestialBodies.size(); i++) {
			CelestialObject body = celestialBodies.get(i);
			
			//validation is done to ensure that the chosen body from the linked list is not the this body and its a different body
			if (!((body.name).equals(name))) {
				
				//the x and y component forces between the pair of bodies is found and updated to the total force being exerted on this body
				double[] forces = new double[2];
				forces = attraction(body);
				double forceX = forces[0];
				double forceY = forces[1];
				
				forceXTotal = forceXTotal + forceX;
				forceYTotal = forceYTotal + forceY;
				
			}

		}
		//the suvat equation "v = u + at" is applied to find the new velocity in each dimension
		xVel = xVel + forceXTotal / mass * TIMESTEP;
		yVel = yVel + forceYTotal / mass * TIMESTEP;
		
		// "xnew = xold + deltax" is applied to find the new position in each dimension
		xPos = xPos + xVel * TIMESTEP;
		yPos = yPos + yVel * TIMESTEP;
		
		//the new position of the body is added to the list of the orbit points
		positions[0] = xPos;
		positions[1] = yPos;
		
		orbit.add(positions);
		
		//if the number of points in the list of orbit points is greater than a predetermined value, the head of the orbit linked list is removed
		//removing the farthest back point that exists in the list in terms of time
		if (orbit.size() > orbitSize) {
			orbit.remove();
		}
		
		
	}
	
	}

