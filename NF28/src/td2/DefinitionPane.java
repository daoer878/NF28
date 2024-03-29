package td2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class DefinitionPane extends JPanel implements PropertyChangeListener{
	private JTextArea definition;
	private JTextArea translation;
	private JScrollPane translatscolle;
	private JScrollPane  definitionscolle;
	
	public DefinitionPane() {
		
		definition = new JTextArea();
		translation = new JTextArea();
		
		definition.setLineWrap(true);
		translation.setLineWrap(true);
		definition.setPreferredSize(new Dimension(190, 150));
		translation.setPreferredSize(new Dimension(190, 150));
		definition.setBackground(Color.cyan);
		translation.setBackground(Color.pink);
		translation.setEditable(false);
		definition.setEditable(false);
		definition.setBorder(BorderFactory.createTitledBorder("French"));
		translation.setBorder(BorderFactory.createTitledBorder("English"));
		
		translatscolle = new JScrollPane(translation);
		definitionscolle  = new JScrollPane(definition);
		this.setLayout(new BorderLayout());
		this.add(definitionscolle,BorderLayout.CENTER);
		this.add(translatscolle,BorderLayout.EAST);
		
	
		ListMotDefModel.getInstance().addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("definition")) {
			this.definition.setText(((Mots)arg0.getNewValue()).getDefinition());
			this.translation.setText(((Mots)arg0.getNewValue()).getTranslation());
		}
	}
	
	
}
