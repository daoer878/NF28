package td5;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;

import javax.swing.TransferHandler;

import td4.ApplicationModel;

@SuppressWarnings("serial")
public class FileTransferHandle extends TransferHandler {
	@Override
	public boolean canImport(TransferSupport support) {
		if (!support
				.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean importData(TransferSupport support) {

		Transferable data = support.getTransferable();
		try {
			@SuppressWarnings("rawtypes")
			List files = (List) data
					.getTransferData(DataFlavor.javaFileListFlavor);

			File xml = (File) files.get(0);

			 ApplicationModel.getInstance().setCurrentFile(xml);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;

	}
}
