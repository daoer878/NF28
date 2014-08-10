package td4;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class SelectionListener implements TreeSelectionListener{
	private ApplicationModel AppModel;
	public SelectionListener(ApplicationModel model) {
		AppModel= model;
	}
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		JTree tree = (JTree) arg0.getSource();
	    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
	        .getLastSelectedPathComponent();
	    if (selectedNode != null) {
	    	AppModel.setSelectedNode(selectedNode);
	    }
	}
}
