package td1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//public class Panneau extends JPanel { 
//	 public void paintComponent(Graphics g){ 
//	 this.setForeground(Color.ORANGE); 
//	 int x1 = this.getWidth()/4; 
//	 int x2 = this.getHeight()/4; 
//	 g.fillOval(x1, x2, this.getWidth()/2, 
//	 this.getHeight()/2); 
//	 g.drawLine(0, 0, this.getWidth(), 
//	 this.getHeight()); 
//	 g.drawLine(this.getWidth(), 0, 0, 
//	 this.getHeight()); 
//	 g.drawRect(x1, x2, this.getWidth()/2, 
//	 this.getHeight()/2); 
//	 } 
//	} 


public class ConsoleView extends JFrame{
	private Console console;
	private String name;
	JPanel pnlButton;   
	JTextField textField;
	JSlider slider;
	JButton jButtonStart,jButtonStop;
	
	public ConsoleView(String name) {
		this.name = name;
		this.console= Console.getInstance();
		//initialize window
		this.setSize(300,200);
		this.setTitle(this.name);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//create panel
		pnlButton = new JPanel();
//		pnlButton.setLayout(null);
		//create text field
		textField = new JTextField(20);
		//create slider
		slider = new JSlider(0, 5000, 1000);
		//create buttons
		jButtonStart = new JButton("Start");
		jButtonStop = new JButton("Stop");

		//setup slider and it listener
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				console.setIntervalle(String.valueOf(slider.getValue()));
				textField.setText(String.valueOf(slider.getValue()));
				
			}
		});
		jButtonStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				console.startTimer();
				console.setIntervalle(textField.getText());
			}
		}
		);
		jButtonStop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				console.stopTimer();
			}
		}
		);
		
		
		//add components in the panel
        pnlButton.add(slider);
		pnlButton.add(textField);
		pnlButton.add(jButtonStart);
		pnlButton.add(jButtonStop);
		//change elements style
		jButtonStart.setBackground(Color.green);
		jButtonStop.setBackground(Color.RED); 
		jButtonStart.setPreferredSize(new Dimension(120,30));
		jButtonStop.setPreferredSize(new Dimension(120,30));
//		jButtonStart.setBorderPainted(false);
		jButtonStart.setBorder(BorderFactory.createRaisedBevelBorder());
		jButtonStop.setBorder(BorderFactory.createRaisedBevelBorder()); 
		//add panel to the windows
		this.setContentPane(pnlButton);
		this.setVisible(true); 
	}

}
