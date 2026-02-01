import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//class that handles the Home page of the simulation
public class HomePage extends JPanel {
	
	JButton test, newSimulation, loadSimulation;
	JLabel label, bg;
	
	
	public HomePage(ActionListener listener) {
		
		setLayout(null);
		
		//adds background to homepage
		ImageIcon i = new ImageIcon("pexels-zch-12491759.jpg");
        bg = new JLabel(i);
        bg.setBounds(0,0,1800,1000);
        add(bg);
        
        
		
		//creates and adds title
		label = new JLabel("Space Orbitals Simulation");
        label.setBounds(300, 0, 1000, 100);
        label.setFont(new Font("Serif", Font.BOLD, 70));
        label.setForeground(Color.WHITE);
        bg.add(label);  
        
       
        //creates and adds option buttons
        newSimulation = new JButton("New Simulation");
        newSimulation.setBackground(Color.BLACK);
        newSimulation.setForeground(Color.WHITE);
        newSimulation.setBounds(0, 450, 500, 500);
        newSimulation.setFont(new Font("Serif", Font.BOLD, 30));
        newSimulation.addActionListener(listener);
        bg.add(newSimulation);
        
        loadSimulation = new JButton("Load Simulation");
        loadSimulation.setBackground(Color.BLACK);
        loadSimulation.setForeground(Color.WHITE);
        loadSimulation.setBounds(780, 450, 500, 500);
        loadSimulation.setFont(new Font("Serif", Font.BOLD, 30));
        loadSimulation.addActionListener(listener);
        bg.add(loadSimulation);
		
	}

}

