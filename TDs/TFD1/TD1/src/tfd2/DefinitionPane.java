package tfd2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class DefinitionPane extends JPanel implements PropertyChangeListener{

	private JTextArea resultAreaFr = new JTextArea(9,20);
	private JTextArea resultAreaEn = new JTextArea(9,20);
	private JScrollPane scrollingAreaFr;
	private JScrollPane scrollingAreaEn; 
	
	
	public DefinitionPane (String def){
		
		//************************ADD -Property-Change--Listener     *********************************************//
		ListMotDefModel.getInstance().addPropertyChangeListener(this);
		
		if (def == null){
			resultAreaFr.setText("");		
		}
		else
			{resultAreaFr.setText(def);}
		
		scrollingAreaFr = new JScrollPane(resultAreaFr);
		setScrollingAreaEn(new JScrollPane(resultAreaEn));
		JSplitPane split_pane= new JSplitPane(0,scrollingAreaFr,scrollingAreaEn);
		split_pane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.add(split_pane);


	}
	
	//************************ Recieve Property-Change -> Reponse action  *********************************************//

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getPropertyName().equals("selection")) {
			System.out.println(arg0.getNewValue());
			this.resultAreaFr.setText(((Mots)arg0.getNewValue()).getDefinitionFr());
			this.resultAreaEn.setText(((Mots)arg0.getNewValue()).getDefinitionEn());
		}
	}
	
	public JTextArea getResualtAreaFr(){
		return resultAreaFr;
		
	}
	public JTextArea getResualtAreaEn(){
		return resultAreaEn;
		
	}



	public JScrollPane getScrollingAreaEn() {
		return scrollingAreaEn;
	}

	public void setScrollingAreaEn(JScrollPane scrollingAreaEn) {
		this.scrollingAreaEn = scrollingAreaEn;
	}
	
	
}