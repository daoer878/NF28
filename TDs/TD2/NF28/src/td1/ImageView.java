package td1;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageView extends JFrame implements PropertyChangeListener{
	private JLabel lb = new JLabel();
	private String name;
	
	public ImageView(String name) {
		this.name = name;
		Console.getInstance().addPropertyChangeListener(this);
		this.setSize(150,150);
		this.setTitle(this.name);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(lb);		
		this.setVisible(true); 
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("image")) {
			this.lb.setIcon((Icon) arg0.getNewValue());
		}
	}
}
