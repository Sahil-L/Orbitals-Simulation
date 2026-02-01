import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

//class that handles all the GUI
public class GUI extends JFrame implements ActionListener {
	
	public JButton newSimulation, loadSimulation, test, test2;
	private JLabel label;
	private JPanel startUp;
	public Container container;
	public CardLayout cardlayout;
	public JFrame main;
	private HomePage homePage;
	private MainPage mainPage;
	private UpdatePage updatePage;
	private Space space;
	private int timeInterval = 20;
	private Timer timer;
	public boolean draw;
	
	public GUI(ActionListener listener) {
		
		//creates and adds title
		setTitle("Space Orbitals Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,950);
		
		space = new Space();
		
		
		//variables for the different presents and are organised
		homePage = new HomePage(this);
		mainPage = new MainPage(this);
		updatePage = new UpdatePage(this, space);
		//space = updatePage.getSpace();
		
		draw = false;
		
		container = getContentPane();
		cardlayout = new CardLayout();
		container.setLayout(cardlayout);
		
		container.add("a", homePage);
		container.add("b", mainPage);
		container.add("c", updatePage);

	    setVisible(true);
	    
	    
	    timer = new Timer(timeInterval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//will repaint the display here
				mainPage.paint();
				
			}
		});
	    
	}
	
 
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//based on the button clicked by the user, the content on the screen will change accordingly to the required present
		if (e.getSource() == homePage.newSimulation) {
			//cardlayout.next(container);
			cardlayout.show(container, "b");
			
			//timer.start();
			
		}
		
		//if this button is clicked, the user is able to choose a file path where to load the simulation from
		else if (e.getSource() == homePage.loadSimulation) {
			
			//chooses, reads and validates the file.
			Boolean pass = space.loadData();
			
			//checks if there was an issue loading with the file
			if (pass) {
				//goes to the simulation
				cardlayout.show(container, "b");
			}
			else {
				//pop up shows stating there was a problem
				JOptionPane.showMessageDialog(null, "PROBLEM WITH LOADING FILE!", "ISSUE!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			
		}
		
		//based on the button clicked by the user, the content on the screen will change accordingly to the required present
		else if (e.getSource() == mainPage.modify) {
			cardlayout.show(container, "c");
			//when the user switches away from the simulation, the simulation is automatically paused
			//the button display changes accordingly
			timer.stop();
			draw = false;
			mainPage.pause.setText("Play Simulation");
			
		}
		//if the user presses the pause/play button for the display, the result is updated accordingly
		//the button display also changes accordingly
		else if (e.getSource() == mainPage.pause) {
		//else if (timeInterval == 5) {	
			
			if (draw == true) {
				timer.stop();
				draw = false;
				mainPage.pause.setText("Play Simulation");
			}
			else {
				mainPage.draw(space);    // if causing issues, remove this and uncomment the same method under: else if (e.getSource() == updatePage.returnBack) {
				timer.start();
				draw = true;
				mainPage.pause.setText("Pause Simulation");
			}
			
		}
		
		//based on the button clicked by the user, the content on the screen will change accordingly to the required present
		else if (e.getSource() == updatePage.returnBack) {
			cardlayout.show(container, "b");
			space = updatePage.getSpace();
			
			//any changes to the speed to simulated time is updated here
			timeInterval = space.timeInterval;
			timer.setDelay(timeInterval);
			//mainPage.draw(space);

			//timer.start();
			//draw = true;
			//mainPage.pause.setText("Pause Simulation");
			
		}
		//if the user chooses to leave the program, the program is terminated
		else if (e.getSource() == updatePage.exit) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		
	}
	

}
