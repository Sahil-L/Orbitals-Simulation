import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

//class that handles the window that displays the interactions between the bodies
public class OrbitDisplay extends JPanel {
	
	JLabel test;
	//Ball ball;
	//LinkedList<Ball> objects = new LinkedList<Ball>();
	LinkedList<CelestialObject> bodies = new LinkedList<CelestialObject>();
	int size = 5000;
	Space space;
	
	public OrbitDisplay() {
		setBackground(Color.BLACK);
		
		//creates multiple instances of balls, I am able to enter the coordinates in the display where to draw the ball
		
		//objects.add(new Ball(10, 10));
		//objects.add(new Ball(1000, 10));
		//objects.add(new Ball(10, 1000));
		

//		setBounds(-500, -500, 8000, 900);
		
		//ImageIcon i = new ImageIcon("unique-solar-system.png");
		//test = new JLabel();
		//test.setBounds(-500, -500, 8000, 900);
		//test.setOpaque(true);
		
		//add(test);
		
		//sets the size of the display
		this.setPreferredSize(new Dimension(size,size));

		setVisible(true);
	}
	
	public void giveSpace(Space space) {
		this.space = space;
		bodies = space.getCelestialBodies();
	}
	
	//the bodies are drawn on the display window
	public void paintComponent(Graphics g) {
		
		//processes the orbits of the bodies
		if (space != null) {
			//bodies = space.move();
			space.processOrbits();
		}
		
		
		//iterates through each body in the list of bodies and draws each of them
		super.paintComponent(g);
		//g.setColor(Color.yellow);
		//g.fillOval(1000, 1000, 10, 10);
		
		//for (Ball obj:objects) {
		//	obj.paintComponent(g);;
		//}
		for (CelestialObject body : bodies) {
			//g.fillOval(1000, 1000, 10, 10);

			body.paintComponent(g);
		}
		
		//System.out.println("yes");
		
		
	}
	

}

