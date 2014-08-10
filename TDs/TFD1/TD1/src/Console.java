import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;

import javax.swing.ImageIcon;


public class Console {
	///*PROPERTY*///
	private Timer time = new Timer();
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

	public void setIntervalle(int newI) {
	    if (newI <=0) {
	       intervalle =1000;
	    } else {
	        intervalle = newI;
	    }
	}

	public int getIntervalle() {
	    if (intervalle <=0) {
	        return 0;
	    } else {
	       return intervalle;
	    }
	}
	
	///*PROPERTY*///
	public void addPropertyChangeListener(
		PropertyChangeListener pcl) {
		changes.addPropertyChangeListener(pcl);
	}
	public Timer getTimer() {
		return time;
	}
	public void startTimer(){
		getTimer().cancel();
		time = new Timer(); 
		ImageTimerTask task = new ImageTimerTask(this);	
		getTimer().schedule(task, 0, intervalle);
	}
	public void stopTimer(){
		getTimer().cancel();
	}
	 public void updateTimer(int x) {
		if (x<=0){
			stopTimer();
		}
		else {
				getTimer().cancel();
				time = new Timer(); 
				ImageTimerTask task = new ImageTimerTask(this);	
				getTimer().schedule(task, 0, x);
				setIntervalle(x);
		}
	 }
	public void setImage(String imageName){
		ImageIcon myImage = new ImageIcon(imageName);
		changes.firePropertyChange("image", null, myImage);
	}
	
}
