import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ConsoleView extends JFrame{
	private Console console;
	protected JSlider slider;
	protected JLabel label;
	
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 10;
	static final int FPS_INIT = 1;    //initial frames per second
	public ConsoleView() {
		this.console= Console.getInstance();
		
		//Region window
		this.setSize(400,200);
		this.setTitle("Console View");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   //EndRegion window
		
		//Region Txtfield
		final JTextField textfield= new JTextField(15);
		JPanel pnlPut = new JPanel();
		pnlPut.setLayout(new GridLayout(1,1));
		//pnlPut.add(new JLabel ("Intervalle : "),SwingConstants.LEFT);
		pnlPut.add(textfield);
		add(pnlPut,BorderLayout.NORTH);
	   //EndRegion Txtfield
		
		//Region ButtonPanel
		JPanel pnlButton = new JPanel();
		JButton jButtonStart = new JButton("Start");
		jButtonStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				console.startTimer();
			}
		}
		);
		JButton jButtonStop = new JButton("Stop");
		
		jButtonStop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				console.stopTimer();
			}
		}
		);
		jButtonStart.setSize(50, 100);
		jButtonStart.setLocation(0,0);
		pnlButton.add(jButtonStart);
		pnlButton.add(jButtonStop);
		
		this.add(pnlButton,BorderLayout.CENTER);		
		//EndRegion ButtonPanel
		
		//Region SliderPanel
		JPanel pnlSlide = new JPanel();
		pnlSlide.setLayout(new GridLayout(2,1));
		label = new JLabel("1 Seconde");
	    label.setFont(new Font("Arial", Font.BOLD, 15));
	    pnlSlide.add(label,SwingConstants.CENTER);

		slider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT); 
	    slider.setPaintLabels(true);
	    slider.setMajorTickSpacing(5);
	    MyChangeListener lst = new MyChangeListener();
	    slider.addChangeListener(lst);
	    
	    slider.setMajorTickSpacing(10);
	    slider.setMinorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    
	    Font font = new Font("Serif", Font.ITALIC, 15);
	    slider.setFont(font);	
	    pnlSlide.add(slider);

	    this.add(pnlSlide,BorderLayout.SOUTH );
		//EndRegion SliderPane
		
		
		
		
		this.setVisible(true); 
	}
	class MyChangeListener implements ChangeListener {
	    MyChangeListener() {
	    }

		@Override
		public synchronized void stateChanged(ChangeEvent e) {
			 int frequency = slider.getValue();
		     label.setText(frequency + " Seconde");
		     //console.setIntervalle(frequency *1000);
		     console.updateTimer(frequency *1000);
			
		}
	  }

}


