package td3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ContactFacility {
	
	public ContactTreeModel parse(File xmlFile) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			ContactHandler handler = new ContactHandler();
			saxParser.parse(xmlFile, handler);
			ContactTreeModel myModel = new ContactTreeModel(handler.getRoot());
			return myModel;
		} catch (IOException e) {
			throw new RuntimeException("I/O Error", e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Parsing configuration error", e);
		} catch (SAXException e) {
			throw new RuntimeException("SAX exception ocurred", e);
		}
	}

}
