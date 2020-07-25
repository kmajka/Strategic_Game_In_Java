package com.bwizard.cegame.documents;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.tools.FileManager;
import com.bwizard.cegame.utils.StringUtil;

/**
 * This class manages a document that stores information about e.g.(cells,figures,layout of panel) and provides possibility 
 * to read all nodes. 
 * <T> can be e.g. BaseDrawFigure, BaseFigure, CellBackgroundInfo, BaseConfiguration
 * @author Krzysztof Majka
 * @version 1.0
 */
public class DocumentManager<T> {
	  
	private static final LogInfo logInfo = new LogInfo(DocumentManager.class);
	
	public ICmnDocument<T> cmnDocument;
	
	public static Document getDocument(String name) {

		Document doc = null;
	    try {
	    	InputStream is = null;

	    	FileManager file = new FileManager(name);
	    	is = file.getInputStream();
	    	
	    	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    	doc = dBuilder.parse(is);	
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
	    return doc;
	}
	  
	private void fillCollectionForRecursivelyElement(ArrayList<T> listComponent, T parentElement, NodeList elementNodeList) {
		  
		for (int elementCount = 0; elementCount < elementNodeList.getLength(); elementCount++) {
				
			Node elementNodeInfo = elementNodeList.item(elementCount);
			 
			if (elementNodeInfo.getNodeType() == Node.ELEMENT_NODE) {
				
				//System.out.println("\nElement Node Name =" + elementNodeInfo.getNodeName());
				
				if (elementNodeInfo.hasAttributes()) {
					 
					NamedNodeMap nodeElementMap = elementNodeInfo.getAttributes();
					
					HashMap<String, String> hashElementAttribute = new HashMap<String, String>();
					for (int i = 0; i < nodeElementMap.getLength(); i++) { 
						Node nodeElement = nodeElementMap.item(i);
						//System.out.println("Element attribute name - " + nodeElement.getNodeName() + " : " + nodeElement.getNodeValue());
						hashElementAttribute.put(nodeElement.getNodeName(), nodeElement.getNodeValue());
					}
					
					for (T componentElement : cmnDocument.createElementFromCellAttribute(parentElement, hashElementAttribute)) {
						
						if (componentElement != null) {
							listComponent.add(componentElement);
							fillCollectionForRecursivelyElement(listComponent, componentElement, elementNodeInfo.getChildNodes());
						}
					}
					
				} else {
					fillCollectionForRecursivelyElement(listComponent, null, elementNodeInfo.getChildNodes());
				}
			}
		}
	}
	
	public void fillDocumentCollection(ArrayList<T> listElement, String pathName) {
			
		logInfo.info("start: fillDocumentCollection(ArrayList<T> listElement, "+pathName+")");
		
		if (StringUtil.isNotEmpty(pathName)) {
			
			Document doc = getDocument(pathName);
			if(doc.hasChildNodes()) {
					
				NodeList nodeListMain = doc.getChildNodes();
				if (nodeListMain.getLength() == 1) { 
					//<main>
					Node main = nodeListMain.item(0);
					//<div>
					NodeList divNodeList = main.getChildNodes();
					fillCollectionForRecursivelyElement(listElement, null, divNodeList);
				}
			}
		}
		
		
		
		logInfo.info("start: fillDocumentCollection(ArrayList<T> listElement, "+pathName+")");
	}
	  
}
