package td1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ImageView extends JFrame implements PropertyChangeListener{
	
	private JLabel lb;
	public ImageView(){

		Console.getInstance().addPropertyChangeListener(this);
		this.setTitle("Image View");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);		//������ʾ����Ļ�м�
		this.setLayout(null);
		
		lb = new JLabel("image");
		this.add(lb);
		lb.setBounds(100, 30, 100, 100);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arret du programme
		this.setVisible(true);
	}
	public void propertyChange(PropertyChangeEvent arg0){
		if (arg0.getPropertyName().equals("image")){
			System.out.println(arg0.getNewValue());   //*************��ʾ����
			this.lb.setIcon(new ImageIcon(arg0.getNewValue().toString()));
		}		
	}
}