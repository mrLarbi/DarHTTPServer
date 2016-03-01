package com.upmc.dar.http.routing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Router {

	private static final String XML_ROOT = "./xml/";

	private String xmlFilename;
	private Document xmlDocument;
	private HashMap<String, String> mapping;

	public Router(String classname) {
		xmlFilename = XML_ROOT + classname + ".xml";
		xmlDocument = null;
		mapping = new HashMap<String, String>();
	}

	public void initDocument() throws IOException, SAXException, ParserConfigurationException {
		File xmlFile = new File(xmlFilename);

		if(!xmlFile.exists()) throw new FileNotFoundException(xmlFilename + " does not exist");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		xmlDocument = doc;
	}
	
	public void map() throws NullPointerException {
		if(xmlDocument == null) throw new NullPointerException("Document not initialized");
		
		xmlDocument.getDocumentElement().normalize();
		NodeList nList = xmlDocument.getElementsByTagName("mapping");
		
		for(int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				mapping.put(eElement.getElementsByTagName("url").item(0).getTextContent(),
							eElement.getElementsByTagName("class").item(0).getTextContent());
			}
		}
		
	}

	public Document getDocument() {
		return xmlDocument;
	}
	
	public String getMapping(String url) {
		return mapping.get(url);
	}

}
