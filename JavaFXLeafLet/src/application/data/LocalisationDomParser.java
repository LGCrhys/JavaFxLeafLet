package application.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import application.enums.Hostility;

public class LocalisationDomParser {
	
	private String fname;
	private List<Localisation> locList;

	public LocalisationDomParser(String fname){
		this.fname = fname;
	}
	
	public List<Localisation> parse(){
		locList = new ArrayList<Localisation>();
		try {	
	        File inputFile = new File(fname);
	        
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);	        
	        final Element root = doc.getDocumentElement();
	        
	        NodeList childNodes = root.getChildNodes();
	        
	        NodeList nodeList;
	        Node node;
	        
	        for(int i=0 ; i<childNodes.getLength(); i++){
	        	if(childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        	Element item = (Element) childNodes.item(i);
		        	Localisation localisation = new Localisation(i);		        	
		        	
		        	nodeList = item.getElementsByTagName("nom");
		        	node = nodeList.item(nodeList.getLength()-1);
		        	if(node.getParentNode().isEqualNode(childNodes.item(i))){
		        		localisation.setName(node.getTextContent());
		        	}        	
		        	
		        	nodeList = item.getElementsByTagName("longitude");
		        	node = nodeList.item(nodeList.getLength()-1);
		        	if(node.getParentNode().isEqualNode(childNodes.item(i))){
		        		localisation.setLongitude(Double.valueOf(node.getTextContent().replace(',','.')));
		        	}        	
		        	
		        	nodeList = item.getElementsByTagName("latitude");
		        	node = nodeList.item(nodeList.getLength()-1);
		        	if(node.getParentNode().isEqualNode(childNodes.item(i))){
		        		localisation.setLatitude(Double.valueOf(node.getTextContent().replace(',','.')));
		        	}        	
		        	
		        	nodeList = item.getElementsByTagName("hostilite");
		        	node= nodeList.item(nodeList.getLength()-1);
		        	if(node.getParentNode().isEqualNode(childNodes.item(i))){
		        		localisation.setHostility(Hostility.fromString(node.getTextContent()));
		        	}
		        	
		        	locList.add(localisation);
	        	}
	        }
       
	        
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
		
		return locList;
	}
	
	public List<Localisation> getLocList() {
		return locList;
	}
}
