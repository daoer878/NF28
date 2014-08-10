package td4;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import td3.Contact;


@SuppressWarnings("serial")
public class ContactEditPanel extends Panel{
	private Contact mycontact;
	JTextField tnom;
	JTextField temail;
	JLabel lnom;
	JLabel lemail;
	JLabel limage;
	JButton bimage;
	JButton bvalider;
	JButton bdelete;
	String filepath=".";
	ApplicationModel AppModel;
	String fileaddresse;
	public Contact getMycontact() {
		return mycontact;
	}
	public void setMycontact(Contact mycontact) {
		this.mycontact = mycontact;
		tnom.setText(mycontact.getNom());
		temail.setText(mycontact.getMail());
		limage.setIcon(new ImageIcon(mycontact.getIcone()));
	}
	
	public ContactEditPanel(){
		tnom = new JTextField();
		temail = new JTextField();
		lnom = new JLabel("Nom :");
		lemail = new JLabel("Email :");
		limage = new JLabel("image");
		bimage = new JButton(" Image ");
		bvalider = new JButton(" Valider ");
		bdelete = new JButton(" Delete ");
		this.setLayout(null);
		lnom.setBounds(0, 0,60, 30);
		tnom.setBounds(60, 0,250, 30);
		lemail.setBounds(0, 50,60, 30);
		temail.setBounds(60, 50,250, 30);
		limage.setBounds(120, 110, 100, 100);
		bimage.setBounds(120, 210, 100, 20);
		bvalider.setBounds(50, 250, 100, 30);
		bdelete.setBounds(200, 250, 100, 30);
 
		this.add(lnom);
		this.add(lemail);
		this.add(tnom);
		this.add(temail);
		this.add(limage);
		this.add(bimage);
		this.add(bvalider);
		this.add(bdelete);
		limage.setIcon(new ImageIcon("."));
		
		bimage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				File dir = new File(filepath);
				fc.setCurrentDirectory(dir);  
		        fc.setDialogTitle("Choisir un image");  
	 
		        int intRetVal = fc.showOpenDialog(new JFrame());    
		        if (intRetVal == JFileChooser.APPROVE_OPTION)  
		        {  
		            fileaddresse = fc.getSelectedFile().getPath();
		    		limage.setIcon(new ImageIcon(fileaddresse));
		        }
			}
		});
		bvalider.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mycontact.setNom(tnom.getText());
				mycontact.setMail(temail.getText());
	    		mycontact.setIcone(fileaddresse);
	    		ApplicationModel.getInstance().applyModel();
			}
		});
		
		bdelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode mynode = ApplicationModel.getInstance().getSelectedNode();
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) mynode.getParent();
				parent.remove(mynode);
				tnom.setText(null);
				temail.setText(null);
				limage.setIcon(new ImageIcon());
				ApplicationModel.getInstance().applyModel();
			}
		});
	}

	}