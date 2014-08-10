package tfd2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.DefaultListModel;



public class ListMotDefModel  {
	
	private String filePath;
	
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	private static ListMotDefModel mots = null;
	
	private DefaultListModel<Mots> dict = new DefaultListModel<Mots>();
	
	private Mots selectedMot=new Mots("","","");
	
	//************************ Get Model  *********************************************//

	public static ListMotDefModel getInstance() {
		if (mots == null) {
			mots = new ListMotDefModel();
			return mots;
		}
		return mots;
	}
	//************************ add Property-Change  *********************************************//

	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
			changes.addPropertyChangeListener(pcl);
		}
	
	//************************SET( Propety change) -> Envoye notification  *********************************************//

	public void setSelection(Mots item){
		selectedMot=item;
		changes.firePropertyChange("selection", null, selectedMot);
	}
	
	
	public DefaultListModel<Mots> getDict(){
		System.out.println(dict.getSize());
		return dict;
	}
	
	public void initDict(String path){
		filePath = path;
		System.out.println(filePath);
		dict.clear();
		 try {
			File filename = new File(filePath);
			InputStreamReader reader = new InputStreamReader(  
			        new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";  
			line = br.readLine();  
			while (line != null) {  
			    String[] items = line.split("=");
			    String key = items[0];
			    System.out.println(key);
			    	
			    if(items[1].contains("@")) {
			    	String[] def_trans = items[1].split("@");
			    	dict.addElement(new Mots(key, def_trans[0], def_trans[1]));
			    } else {
			    	dict.addElement(new Mots(key,items[1],""));
			    	
			    	
			    }
			    line = br.readLine();

			}


        } 
		 catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}