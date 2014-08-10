package td5;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

import td3.Contact;

public class NodesTransferable implements Transferable{
	private DefaultMutableTreeNode[] myNode;
	protected static final DataFlavor nodeFlaver = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType, "ContactNode");
	
	public NodesTransferable(DefaultMutableTreeNode[] nodes) {
		myNode = nodes;
	}


	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] result = {nodeFlaver, DataFlavor.stringFlavor};
		return result;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return Arrays.asList(getTransferDataFlavors()).contains(flavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (flavor == nodeFlaver) {
			return myNode;
		} else if (flavor == DataFlavor.stringFlavor) {
			if (myNode[0].getUserObject() instanceof Contact) {
				String return_xmls = "";
				for (int i = 0; i < myNode.length; i++) {
					return_xmls += ((Contact)myNode[i].getUserObject()).getXML();
				}
				return return_xmls;
			}
		}
		return null;
	}

}
