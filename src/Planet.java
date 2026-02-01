import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

//this class is a child class of CelestialObject
public class Planet extends CelestialObject{
	
	double  scalefactor = Math.pow(10, -20);
	//int radius;
	double AU = 149.6e6 * 1000;
	double G = 6.67428e-11;
	//One AU is around 100 pixels in the display
	double SCALE = 250 / AU;
	double TIMESTEP = 3600 * 24; //length of one day
	int size = 5000;
	double minMass = Math.pow(10, 21);
	double maxMass = Math.pow(10, 28);
	
	//the constructor function takes in the validated inputs from the user to create a body and assigns each to the respective attribute
	//of the planet
	public Planet(double xPos, double yPos, double xVel, double yVel, String colour, double mass, String name, int radius) {
		
		//these are the attributes that this class inherits from its parent class
		super(xPos, yPos, xVel, yVel, colour, mass, name, radius);
		
		
		
	}
	
	//this method paints the body and its orbit pattern on the simulation display
	public void paintComponent(Graphics g) {
		
		//based on the colour attribute, the colour of the body is set accordingly
		if (colour.equals("blue")) {
			g.setColor(Color.blue);
		}
		else if (colour.equals("cyan")) {
			g.setColor(Color.cyan);
		}
		else if (colour.equals("darkGray")) {
			g.setColor(Color.darkGray);
		}
		else if (colour.equals("gray")) {
			g.setColor(Color.gray);
		}
		else if (colour.equals("green")) {
			g.setColor(Color.green);
		}
		else if (colour.equals("lightGray")) {
			g.setColor(Color.lightGray);
		}
		else if (colour.equals("magenta")) {
			g.setColor(Color.magenta);
		}
		else if (colour.equals("orange")) {
			g.setColor(Color.orange);
		}
		else if (colour.equals("pink")) {
			g.setColor(Color.pink);
		}
		else if (colour.equals("red")) {
			g.setColor(Color.red);
		}
		else if (colour.equals("white")) {
			g.setColor(Color.white);
		}
		else if (colour.equals("yellow")) {
			g.setColor(Color.yellow);
		}
		

		//this will check if this body's orbit pattern needs to be drawn
		if (drawOrbit == true) {
			
			//the list of orbit points is iterated through
			for (int i = 0; i < orbit.size(); i++) {
				double[] pos = orbit.get(i);
				
				//the position is converted from AU into the range for the simulation display
				int xPoint = (int) (pos[0] * SCALE) + size / 2;
				int yPoint = (int) (pos[1] * SCALE) + size / 2;
				
				System.out.println(xPoint);
				System.out.println(yPoint);
				//the point is drawn onto the display
				g.fillOval(xPoint, yPoint, 2, 2);
			}
		}
		
		
		
		
		//range (-10 AU to 10 AU)
		//the position is converted from AU into the range for the simulation display
		int x = (int)  (xPos * SCALE) + size / 2;
		int y = (int) (yPos * SCALE) + size / 2;
		//System.out.println(name);
		//System.out.println(x);
		//System.out.println(y);
		
		//if (name.equals("earth")) {
			//System.out.println(mass);
		//}
		
		//the point is drawn onto the display
		g.fillOval(x, y, radius, radius);
		
		//planet type colour
		g.setColor(Color.green);
		//the name and type of celestial body is drawn to be next to the body
		g.drawString("P " + name, x, y);
		
	}
	
	

}

