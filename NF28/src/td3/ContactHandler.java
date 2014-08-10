package td3;

import javax.swing.tree.DefaultMutableTreeNode;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContactHandler extends DefaultHandler {

	private String currentTag;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode node;
	private DefaultMutableTreeNode currentParent;
	private Contact CurrentContact;
	private boolean is_contact=false;
	

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// store tag name for future use
		currentTag = qName;
		if (qName.equals("contacts")) {
			root = new DefaultMutableTreeNode("contacts");
			currentParent = root;
		} else if(currentTag.equals("contact")) {
			is_contact = true;
			CurrentContact = new Contact();
			node = new DefaultMutableTreeNode(CurrentContact);
			currentParent.add(node);
			currentParent = node;
		} else if(is_contact) {
		} else {
			node = new DefaultMutableTreeNode(qName);
			currentParent.add(node);
			currentParent = node;
		}
		
	}
	

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String content = new String(ch, start, length);
		
		// ignore empty content
		if(content.trim().equals("") || content.trim().equals("\n")) {
			return;
		} else if (currentTag.equals("nom")){
			CurrentContact.setNom(content);
		} else if (currentTag.equals("mail")){
			CurrentContact.setMail(content);
		} else if (currentTag.equals("icone")){
			CurrentContact.setIcone(content);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("contact")) {
			is_contact = false;
		}
		if (!is_contact) {
			currentParent = (DefaultMutableTreeNode) currentParent.getParent();
		}
	}
	
	public DefaultMutableTreeNode getRoot(){
		return root;
	}

}
