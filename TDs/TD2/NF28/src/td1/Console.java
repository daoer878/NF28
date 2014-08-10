package td1;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;

import javax.swing.ImageIcon;


public class Console {
	private Timer timer = new Timer();
	private int intervalle=1000;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private static Console console = null;
	public static Console getInstance() {
		if (console == null) {
			console = new Console();
			return console;
		}
		return console;
	}
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		changes.addPropertyChangeListener(pcl);
	}

	public void startTimer(){
		ImageTimerTask task = new ImageTimerTask(this);	
		timer.schedule(task, 0, intervalle);
	}
	public void stopTimer(){
		timer.cancel();
		timer = new Timer();
	}
	public void setIntervalle(String intervalle) {
		int my_int = Integer.parseInt(intervalle);
		//System.out.println("change intervalle to "+ intervalle);
		if (my_int > 0) {
			this.intervalle = my_int;
		}
	}
	public void setImage(String imageName){
		ImageIcon myImage = new ImageIcon(imageName);
		changes.firePropertyChange("image", null, myImage);
	}
	
}
