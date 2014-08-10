package td2;

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
	
	private static ListMotDefModel model = null;
	
	private DefaultListModel<Mots> dict = null;
	
	public static ListMotDefModel getInstance() {
		if (model == null) {
			model = new ListMotDefModel();
		}
		return model;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		changes.addPropertyChangeListener(pcl);
	}
	
	public DefaultListModel<Mots> getDictModel(){
		if (dict == null) {
			dict = new DefaultListModel<Mots>();
		}
		return dict;
	}
	
	public void askDefinition(String key) {
		for (int i = 0; i < dict.getSize(); i++) {
			if(dict.getElementAt(i).getKey().equals(key)) {
				changes.firePropertyChange("definition", null, dict.getElementAt(i));
				break;
			}
		}		
	}
	
	public void initDict(String path){
		filePath = path;
		try {
			File filename = new File(filePath);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";  
			line = br.readLine();
			dict.clear(); //clean the dict at first
			while (line != null) {  
			    String[] items = line.split("=");
			    String key = items[0];
			    if(items[1].contains("@")) {
			    	String[] def_trans = items[1].split("@");
			    	dict.addElement(new Mots(key, def_trans[0], def_trans[1]));
			    } else {
			    	dict.addElement(new Mots(key,items[1],""));
			    }
			    line = br.readLine();
			}
			br.close();
			
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}