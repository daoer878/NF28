package td1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ConsoleView extends JFrame {//implements PropertyChangeListener
	Console console = new Console();
	JSlider slider;
	JTextField champ;

	public ConsoleView(){
		
		this.console = Console.getInstance();
		this.setSize(400,230);
		this.setTitle("Console View");
		
		//*************** text
		//final JTextField champ = new JTextField();
		champ = new JTextField();
		JPanel paneltext = new JPanel();		
		paneltext.setLayout(new GridLayout(1,1));
		paneltext.add(champ);
		add(paneltext,BorderLayout.NORTH);
		
		//*************** button
		JPanel panelbutton = new JPanel();
		panelbutton.setLayout(null);
		JButton start = new JButton("start");
		JButton stop = new JButton("stop");
		start.setBounds(0, 0,300, 70);
		stop.setBounds(300,0,100,70);
				
		start.setBackground(Color.yellow);
		start.setOpaque(true);
		start.setBorderPainted(false);
		
		stop.setBackground(Color.pink);
		stop.setOpaque(true);
		stop.setBorderPainted(false);
		
		panelbutton.add(start);
		panelbutton.add(stop);
		this.add(panelbutton,BorderLayout.CENTER);	
		
		//**************** slider

		JPanel panelslider = new JPanel();
		panelslider.setLayout(new GridLayout(2,1));
		slider = new JSlider(JSlider.HORIZONTAL,0,10,5);	
	
		slider.setPaintTicks(true);          // jia kedu
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(0);
		slider.setPaintLabels(true);
		slider.setPaintTrack(true);
		MyChangeListener my = new MyChangeListener();
		slider.addChangeListener(my);  //************
		//slider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
		JLabel labelslider = new JLabel("                                      Intervalle en s");	
		
		panelslider.add(labelslider);
		panelslider.add(slider);
		
		this.add(panelslider,BorderLayout.SOUTH);	
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arret du programme
		this.setVisible(true);
		
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				console.startTimer();
			}
		});
		
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				console.stopTimer();
			}
		});
		
		champ.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s = champ.getText();
				console.updateTimer(s);
				slider.setValue(Integer.valueOf(s).intValue()/1000);
			}
		});	
	}
	class MyChangeListener implements ChangeListener {
	    MyChangeListener() {
	    }
		@Override
		public synchronized void stateChanged(ChangeEvent e) {
			 int changeinterval = slider.getValue();
			 if (changeinterval == 0)                        // interval = 0; interval = 1;
				 changeinterval = 1;
		     console.updateTimer(String.valueOf(changeinterval*1000));
		     champ.setText(String.valueOf(changeinterval*1000));
		}
	  }
}