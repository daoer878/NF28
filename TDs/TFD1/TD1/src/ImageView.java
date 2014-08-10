import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageView extends JFrame implements PropertyChangeListener{
	private JLabel lb = new JLabel("Images");
	public ImageView() {
		Console.getInstance().addPropertyChangeListener(this);
		
		this.setSize(80,130);
		this.setTitle("Image View");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(lb);		
		this.setVisible(true); 
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals(
		"image")) {
			System.out.println(arg0.getNewValue());
			this.lb.setIcon(new ImageIcon(arg0.getNewValue().toString()));
		}
	}
}
