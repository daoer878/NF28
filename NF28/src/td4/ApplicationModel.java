package td4;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import td3.Contact;
import td3.ContactFacility;
import td3.ContactTreeModel;


public class ApplicationModel {
	private DefaultMutableTreeNode selectedNode;
	private File currentFile;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private static ContactTreeModel treeModel;

	private static ApplicationModel Application = null;
	public static ApplicationModel getInstance() {
		if (Application == null) {
			Application = new ApplicationModel();
			return Application;
		}
		return Application;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		changes.addPropertyChangeListener(pcl);
	}
	
	public void parse(){
		ContactFacility contactFacility = new ContactFacility();
		treeModel = contactFacility.parse(currentFile);
		changes.firePropertyChange("parse", null, treeModel);
	}
	
	public void applyModel() {
		changes.firePropertyChange("update", null, treeModel);
	}
	
	public void save(){
		saveAs(currentFile);
	}
	
	public void saveAs(File new_file) {
		String my_xml = treeModel.toXML();
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(new_file));
			output.write(my_xml);
		    output.close();
		    currentFile = new_file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addContact (Contact c){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(c);
		if (selectedNode == null) {
			((DefaultMutableTreeNode) treeModel.getRoot()).add(node);
		} else if (selectedNode.getUserObject().getClass().equals(Contact.class)) {
			((DefaultMutableTreeNode) selectedNode.getParent()).add(node);
		} else {
			((DefaultMutableTreeNode) selectedNode).add(node);
		}
		applyModel();
	}
	
	public void setSelectedNode(DefaultMutableTreeNode node) {
		selectedNode = node;
	    changes.firePropertyChange("contact", null, selectedNode);

	}
	
	public DefaultMutableTreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setCurrentFile(File file) {
		currentFile = file;
		parse();
	}

}
