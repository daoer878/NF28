package td1;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Console{
	public static Console con = null;
	private Timer time = new Timer();
	private int interval = 1000;							//****************
	private int intervalold = 0;
	private int intervalnew;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener pcl){
			changes.addPropertyChangeListener(pcl);
	}
	
	public int getInterval (String s){    				 //******************
		interval = Integer.valueOf(s).intValue();
		return interval;
	}
	public static Console getInstance(){
		if (con == null)
			con = new Console();
		return con;
	}
	
	public Timer getTimer(){
		return time;
	}
	
	public void startTimer (){
		getTimer().cancel();
		time = new Timer();
		ImageTimerTask task = new ImageTimerTask(this);		
		getTimer().schedule(task,0,interval);   		 //*********
		intervalold = interval;
	}
	
	public void stopTimer(){
		getTimer().cancel();
		intervalold = 0;
	}
	
	public void updateTimer(String s){
		if (intervalold != 0) {
			intervalnew = getInterval(s);
			if (intervalnew != intervalold){
				interval = intervalnew;
				startTimer();
			}		
			intervalold = intervalnew;
		}
	}
	
	public void setImage (String imageadresse){
		ImageIcon imagenow = new ImageIcon (imageadresse);
		changes.firePropertyChange("image", null, imagenow);
	}
}
