package com.ath.esqltool.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AnalyzerBrokerXml {

	private Document document;
	private DocumentBuilderFactory dbf;
	private XPath xpath;
	private HashMap<String, String> mapOverrideProperties = new HashMap<String, String>();

	public AnalyzerBrokerXml() {
		super();
		dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true); // This is really important, without it that XPath does not work

		xpath = XPathFactory.newInstance().newXPath();
	}

	public void init(File fileInput) throws ParserConfigurationException, SAXException, IOException {
		dbf.setNamespaceAware(true); // This is really important, without it that XPath does not work

		DocumentBuilder db = dbf.newDocumentBuilder();
		document = (db.parse(fileInput)); // inputSource, inputStream or file which contains your XML.
	}

	public void extractOverrides(String appName) throws XPathExpressionException, IOException {

		NodeList nodeList = (NodeList) xpath.evaluate("/Broker/CompiledMessageFlow/ConfigurableProperty[@override]",
				document, XPathConstants.NODESET);


		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			if (currentNode.getAttributes() != null) {
				NamedNodeMap attributes = currentNode.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++) {
					Node item = attributes.item(j);

					if (item.getNodeName().equalsIgnoreCase("override")) {
						Node nextItem = attributes.item(j + 1);
						if (nextItem.getNodeName().equalsIgnoreCase("uri")) {
							StringTokenizer tokenizerAll = new StringTokenizer(nextItem.getNodeValue(), "#");
							String absoluteFlow = tokenizerAll.nextToken();
							String nodeAndProperty = tokenizerAll.nextToken();
							StringTokenizer tokenizerFlow = new StringTokenizer(absoluteFlow, ".");
							String flowName = null;
							while (tokenizerFlow.hasMoreTokens()) {
								flowName = (String) tokenizerFlow.nextToken();
							}
							StringTokenizer tokenizerNode = new StringTokenizer(nodeAndProperty, ".");
							String propName = null;
							StringBuffer nodeNameBuf = new StringBuffer();
							int countTokens = tokenizerNode.countTokens();
							int x = 1;
							while (tokenizerNode.hasMoreTokens()) {

								propName = (String) tokenizerNode.nextToken();

								if (tokenizerNode.hasMoreTokens()) {
									nodeNameBuf.append(propName);

									if (x < countTokens - 1) {
										nodeNameBuf.append(".");
									}

									x++;
								}

							}
							if (!item.getNodeValue().equalsIgnoreCase("none") && !item.getNodeValue().equalsIgnoreCase("ESBDATA")) {
								System.out.println(
										appName + "\t" + flowName + ".msgflow" + "\t" + absoluteFlow.replace('.', '/')
												+ "\t" + nodeNameBuf + "\t" + propName + "\t" + item.getNodeValue());
							}

							
							mapOverrideProperties.put(nextItem.getNodeValue(), item.getNodeValue());
						}

					}

				}

			}

		}

		
	}

	public HashMap<String, String> getMapOverrideProperties() {
		return mapOverrideProperties;
	}

	public void setMapOverrideProperties(HashMap<String, String> mapOverrideProperties) {
		this.mapOverrideProperties = mapOverrideProperties;
	}

}
