import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//class that handles the Main page of the simulation where the simulation is displayed
public class MainPage extends JPanel{
	
	JButton modify, test2, pause;
	JScrollPane display;
	JLabel test;
	private OrbitDisplay orbitDisplay;
	
	public MainPage (ActionListener listener) {
		
		setLayout(null);
		
		//the required buttons are added and formatted accordingly
		modify = new JButton("Modify Simulation");
        modify.setBackground(Color.BLACK);
        modify.setForeground(Color.WHITE);
        modify.setBounds(0, 50, 300, 50);
        modify.setFont(new Font("Serif", Font.BOLD, 30));
        modify.addActionListener(listener);
        add(modify);
        
        pause = new JButton("Play Simulation");
        pause.setBackground(Color.BLACK);
        pause.setForeground(Color.WHITE);
        pause.setBounds(930, 50, 300, 50);
        pause.setFont(new Font("Serif", Font.BOLD, 30));
        pause.addActionListener(listener);
        add(pause);
        
        //display = new JScrollPane();
        //the display is initialised
        orbitDisplay = new OrbitDisplay();
        orbitDisplay.setBounds(-2000, -2000, 1000, 1000);
        display  = new JScrollPane(orbitDisplay);
        display.setBounds(0, 110, 1230 , 790);
        
        //the scroll bars for each dimensions are initialised
        //the display is made to also be navigated using arrow keys
        JScrollBar vertical = display.getVerticalScrollBar();
        InputMap vert = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        vertical.setUnitIncrement(50);
        vert.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
        vert.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
        
        JScrollBar horizontal = display.getHorizontalScrollBar();
        InputMap hor = horizontal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        horizontal.setUnitIncrement(50);
        hor.put(KeyStroke.getKeyStroke("RIGHT"), "positiveUnitIncrement");
        hor.put(KeyStroke.getKeyStroke("LEFT"), "negativeUnitIncrement");
        
        
        
        //JTextArea description = new JTextArea(10, 60); 
        //ImageIcon i = new ImageIcon("unique-solar-system.png");
        //test = new JLabel(i);
        
        //display.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        //display.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        
        
        
        //test = new JButton("");
        //test.setBounds(0, 0, 5000, 5000);
        //test.setBackground(Color.BLUE);
        //test.setForeground(Color.WHITE);
        
        //display.add(test);
        
                
                
        
        
		add(display);
		
		setVisible(true);
		
	}
	
	public void draw(Space space) {
		orbitDisplay.giveSpace(space);
	}
	
	public void paint() {
		orbitDisplay.repaint();
	}

}
