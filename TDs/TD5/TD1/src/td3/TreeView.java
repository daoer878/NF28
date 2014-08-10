package td3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import td4.ApplicationModel;
import td4.ContactEditPanel;
import td4.SelectionListener;
import td5.NodeTransferHandler;


@SuppressWarnings("serial")
public class TreeView extends JFrame implements PropertyChangeListener {
//	String name;
	private ApplicationModel app_model;
	private JMenuBar menubar;
	private JMenu menufichier;
	private JMenu menudocuments;
	private JMenuItem itemopenf;
	private JMenuItem itemexitf;
	private JMenuItem itemsave;
	private JMenuItem itemsaveas;
	private JMenuItem itemnew;
	private static String filepath = ".";
	private JSplitPane splitpane;
	private JTabbedPane tabbedpane = null;
	private JScrollPane scrollpanetext;
	private JScrollPane scrollpanetree;
	private ContactEditPanel contact_edit_pane;
	private JTextArea text;
	private JTree tree;
	
	public TreeView(){
		app_model = ApplicationModel.getInstance();
		app_model.addPropertyChangeListener(this);
//		this.name = s;
		menubar = new JMenuBar();
		menufichier = new JMenu("File");
		menudocuments = new JMenu("Edit");
		itemopenf = new JMenuItem("Open");
		itemexitf = new JMenuItem("Exit");
		itemsave = new JMenuItem("Save");
		itemsaveas = new JMenuItem("Save As...");
		itemnew = new JMenuItem("New Contact");
		tabbedpane = new JTabbedPane();
		tree = new JTree();
		tree.setBorder(BorderFactory.createLoweredBevelBorder());
		tree.setModel(null);
		tree.setDragEnabled(true);
		tree.setTransferHandler( new NodeTransferHandler() );
			
		tabbedpane.setTabPlacement(JTabbedPane.TOP);
		text = new JTextArea();
		text.setBackground(Color.cyan);
		//text.setLineWrap(true);
		text.setBorder(BorderFactory.createLoweredBevelBorder()); 
		text.setEditable(false);
		text.setDragEnabled(true);
		tree.setTransferHandler( new NodeTransferHandler() );
		
		scrollpanetext = new JScrollPane(text);
		scrollpanetext.setPreferredSize(new Dimension(190, 135)); 
		contact_edit_pane = new ContactEditPanel();
		tabbedpane.addTab("XML",scrollpanetext);
		tabbedpane.addTab("Contact", contact_edit_pane);
		scrollpanetree = new JScrollPane(tree);

		menufichier.add(itemopenf);
		menufichier.add(itemsave);
		menufichier.add(itemsaveas);
		menufichier.add(itemexitf);
		menudocuments.add(itemnew);
		
		//menudocuments.add(itemopend);
		//menudocuments.add(itemexitd);

		menubar.add(menufichier);
		menubar.add(menudocuments);
		itemsave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				app_model.save();
			}
		});
		
		itemnew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Contact new_contact = new Contact();
				app_model.addContact(new_contact);
			}
		});
		
		itemopenf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			JFileChooser fc = new JFileChooser();
			File dir = new File(filepath);
			fc.setCurrentDirectory(dir);  
	        fc.setDialogTitle("Choisir un fichier");  
 
	        int intRetVal = fc.showOpenDialog(new JFrame());    
	        if (intRetVal == JFileChooser.APPROVE_OPTION)  
	        {  
	            app_model.setCurrentFile(fc.getSelectedFile());
	        }  
			}			
		});
		
		itemsaveas.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new File("."));
			    int retrival = chooser.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
			        app_model.saveAs(new File(chooser.getSelectedFile()+".xml"));
			    }
			}			
		});
		itemexitf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}			
		});
		
		//listener of jtree
		tree.addTreeSelectionListener(new SelectionListener(app_model));
		
		splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,scrollpanetree,tabbedpane);
		splitpane.setDividerLocation(200);
		//splitpane.setOneTouchExpandable(true);
		this.add(splitpane);
		this.setSize(600, 400);		
		this.setTitle("Contacts");
		this.setLocationRelativeTo(null);  //fenetre au centre d'ecran
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arret du programme
		this.setJMenuBar(menubar);
		this.setVisible(true);      // write at last
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("parse")) {
			tree.setModel((TreeModel) arg0.getNewValue());
			text.setText(((ContactTreeModel) arg0.getNewValue()).toXML());
		} else if (arg0.getPropertyName().equals("update")) {
			text.setText(((ContactTreeModel) arg0.getNewValue()).toXML());
			tree.updateUI();
		} else if (arg0.getPropertyName().equals("contact")) {
			//change view of contacteditpannel
			DefaultMutableTreeNode selected_node = (DefaultMutableTreeNode) arg0.getNewValue();
			if (selected_node.getUserObject().getClass().equals(Contact.class)) {
				contact_edit_pane.setMycontact((Contact)selected_node.getUserObject());
			}
		}
		
	}
}
