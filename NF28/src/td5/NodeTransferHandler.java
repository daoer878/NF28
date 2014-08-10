package td5;

import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import td3.Contact;
import td4.ApplicationModel;


@SuppressWarnings("serial")
public class NodeTransferHandler extends TransferHandler{
	
	@Override
	public int getSourceActions( JComponent c ){
        return MOVE;
    }
	
	@Override
	public Transferable createTransferable( JComponent c ) {
//        DefaultMutableTreeNode node = (DefaultMutableTreeNode)((JTree)c).getLastSelectedPathComponent();
//        return new NodeTransferable(node);
        JTree tree = (JTree)c;
        TreePath[] paths = tree.getSelectionPaths();
        if(paths != null) {
        	List<DefaultMutableTreeNode> moves =
                    new ArrayList<DefaultMutableTreeNode>();
        	for(int i = 0; i < paths.length; i++) {
                DefaultMutableTreeNode next =
                    (DefaultMutableTreeNode)paths[i].getLastPathComponent();
                moves.add(next);
            }
        	DefaultMutableTreeNode[] nodes =
        			moves.toArray(new DefaultMutableTreeNode[moves.size()]);
        	return new NodesTransferable(nodes);
        }
        return null;
    }
	
	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDrop() &&
				support.getTransferable().isDataFlavorSupported(NodesTransferable.nodeFlaver)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean importData(TransferSupport support) {
		if (canImport(support)) {
			try {
				Transferable t = support.getTransferable();
				DefaultMutableTreeNode[] dmt = (DefaultMutableTreeNode[])
			                            t.getTransferData(NodesTransferable.nodeFlaver);
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
				for (int i = 0; i < dmt.length; i++) {
					parent.add(dmt[i]);
				}
				tm.reload();
				tree.expandPath(tp);
				ApplicationModel.getInstance().applyModel();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}