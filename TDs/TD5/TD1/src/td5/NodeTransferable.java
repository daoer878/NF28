package td5;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

public class NodeTransferable implements Transferable{
	private DefaultMutableTreeNode myNode;
	protected static final DataFlavor nodeFlaver = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType, "ContactNode");
	
	public NodeTransferable(DefaultMutableTreeNode node) {
		myNode = node;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] result = {nodeFlaver};
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
		}
		return null;
	}

}
