package td2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import td1.Console;

public class ListView extends JFrame{
	private String name;
	private JMenuBar menu_bar;
	private DefinitionPane definition_panel;
	private JScrollPane mot_pane;
	private JList<Mots> list_mot;
	private ListMotDefModel model;
	private JSplitPane split_pane;

	public ListView(String name) {
		this.name = name;
		this.model= ListMotDefModel.getInstance();
		this.menu_bar = new JMenuBar();
		
		this.definition_panel = new DefinitionPane();
		this.mot_pane = new JScrollPane();
		
		//load model to list
		list_mot = new JList<Mots>();
//		list_mot.setModel(model.getDict());
		mot_pane.add(list_mot);
		
		//add definition pane and mot pane to the split pane
		
		split_pane= new JSplitPane(0,mot_pane,definition_panel);
		split_pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_pane.setDividerLocation(250);
		this.add(split_pane);
		

		//add components to the menu bar.
		JMenuItem menu_fichier = new JMenuItem("Ouvrir");
		JMenuItem menu_exit = new JMenuItem("Quitter");
		JMenu my_menu = new JMenu("Fichier");
		my_menu.add(menu_fichier);
		my_menu.add(menu_exit);
		menu_bar.add(my_menu);
		
		//add action for items.
		menu_fichier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  
		    {  
		        JFileChooser fc = new JFileChooser();  
		        list_mot.setModel(model.getDict());
		        File dir = new File(".");  
		        fc.setCurrentDirectory(dir);  
		        fc.setDialogTitle("Ouvrir");   
		        fc.setFileFilter(fc.getAcceptAllFileFilter());  
		        int intRetVal = fc.showOpenDialog(new JFrame());  
		        if (intRetVal == JFileChooser.APPROVE_OPTION)  
		        {  
		        	ListMotDefModel.getInstance().initDict(fc.getSelectedFile().getPath());
		        }  
		    }    
		});
		menu_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		//use method setJMenuBar to set a nice view bar
		this.setJMenuBar(menu_bar);
		this.setSize(500,500);
		this.setTitle(this.name);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
	}
}
