package td5;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import td3.Contact;


@SuppressWarnings("serial")
public class NodeTransferHandler extends TransferHandler{
	
	@Override
	public int getSourceActions( JComponent c ){
        return MOVE;
    }
	
	@Override
	public Transferable createTransferable( JComponent c ) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)((JTree)c).getLastSelectedPathComponent();
        return new NodeTransferable(node);
    }
	
	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDrop() && support.getTransferable().isDataFlavorSupported(NodeTransferable.nodeFlaver)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean importData(TransferSupport support) {
		if (canImport(support)) {
			try {
				Transferable t = support.getTransferable();
				   DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)
				                            t.getTransferData(NodeTransferable.nodeFlaver);
				   JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
				   TreePath tp = dl.getPath();
				   if (tp == null) {
					   return false;
				   }
				   DefaultMutableTreeNode parent = (DefaultMutableTreeNode) tp.getLastPathComponent();
				   if (parent.getUserObject() instanceof Contact) {
					   return false;
				   }
				   JTree tree = (JTree)support.getComponent();
				   DefaultTreeModel tm = (DefaultTreeModel) tree.getModel();
				   parent.add(dmt);
				   tm.reload();
				   tree.expandPath(tp);
				   return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}