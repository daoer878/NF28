package td3;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

@SuppressWarnings("serial")
public class ContactTreeModel extends DefaultTreeModel implements TreeNode{

	public ContactTreeModel(TreeNode arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public String toXML(){
		String myXML = "";
		DefaultMutableTreeNode root  = (DefaultMutableTreeNode) getRoot();
		myXML += visit(root);
		return myXML;
	}
	
	public String visit(DefaultMutableTreeNode Node) {
		String sub_XML = "";
		if (Node.getUserObject().getClass().equals(Contact.class)) {
			sub_XML += ((Contact)Node.getUserObject()).getXML();
		} else if(Node.getChildCount() == 0){
			sub_XML += "<"+Node.toString()+"/>\n";
		} else{
			sub_XML += "<"+Node.toString()+">\n";
			DefaultMutableTreeNode nextNode = (DefaultMutableTreeNode) Node.getFirstChild();
			sub_XML += visit(nextNode);
			nextNode = nextNode.getNextSibling();
			while (nextNode != null) {
				sub_XML += visit(nextNode);
				nextNode = nextNode.getNextSibling();
			}
			sub_XML += "</"+Node.toString()+">\n";
		}
		return sub_XML;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

}
