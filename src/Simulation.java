import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//main class
public class Simulation implements ActionListener {
	
	private GUI gui;
	
	public Simulation() {
		//Space space = new Space();
		gui = new GUI(this);
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Math.pow(10, 3));
		
		new Simulation();

		
		
		
		
		//double a = 10e2;
		//double b = Math.pow(10, 2);
		//double AU = 149.6e6 * 1000;
		
		//System.out.println((int)(AU / AU));
		
		//double gravConstant = 6.67430e-11;
		//double xForce, mass1, mass2;
		//int xDistance = 100;
		//double scalefactor = Math.pow(10, 13);
		//mass1 = 100;
		//mass2 = 10;
		
		
		//xForce = ((gravConstant * mass1 * mass2) / (Math.pow(xDistance,2)));
		//xForce = xForce * scalefactor;
		//System.out.println(gravConstant * Math.pow(10, 12));

		//System.out.println(xForce);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//if (e.getSource() == gui.loadSimulation) {
		//	System.out.println("load a simulation");
		//}
		//else if (e.getSource() == gui.newSimulation) {
		//	System.out.println("create a new simulation");
		//}
	}

}

