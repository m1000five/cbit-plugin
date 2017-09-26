package com.ath.esqltool.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.xml.namespace.NamespaceContext;
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

public class AnalyzerWsdl {

	private LinkedHashSet<String> setNamespaces = new LinkedHashSet<String>();
	private LinkedHashSet<String> setOperations = new LinkedHashSet<String>();

	private HashMap<String, String> mapOpMsgs = new HashMap<String, String>();
	private HashMap<String, String> mapMsgElements = new HashMap<String, String>();

	private String namespace;
	private String oprname;
	private String wsdlBinding;
	private String wsdlPort;
	private String wsdlSvcPort;
	private String msgRq;
	private String msgRs;
	private Document document;

	public AnalyzerWsdl() {
		super();
	}

	public LinkedHashSet<String> getNamespaces() {
		return getSetNamespaces();
	}

	public void parse(File inputSource)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true); // This is really important, without it that XPath does not work
		DocumentBuilder db = dbf.newDocumentBuilder();
		setDocument(db.parse(inputSource)); // inputSource, inputStream or file which contains your XML.

		XPath xpath = XPathFactory.newInstance().newXPath();

		NodeList nodeList = (NodeList) xpath.evaluate("//*[namespace-uri()]/@targetNamespace", getDocument(),
				XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (i == 0) {
				setNamespace(currentNode.getNodeValue());
				continue;
			}

			getSetNamespaces().add(currentNode.getNodeValue());
		}

		String soapNameSpace = xpath.evaluate("/*/namespace::*[name()='soap']", getDocument());
		System.out.println(soapNameSpace);

		NamespaceContext context = new NamespaceContextMap("wsdl", "http://schemas.xmlsoap.org/wsdl/");

		xpath.setNamespaceContext(context);

		// <wsdl:binding name="CardPswdAssignmentSvcBinding"
		// type="tns:CardPswdAssignmentSvc">
		nodeList = (NodeList) xpath.evaluate("//wsdl:operation/@name", getDocument(), XPathConstants.NODESET);

		boolean isSet = false;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			System.out.println("OPERATIONS->" + currentNode);
			System.out.println("OPERATIONS->" + currentNode.toString());
			System.out.println("OPERATIONS->" + currentNode.getNodeValue());

			if (currentNode.getNodeValue() != null) {
				if (!isSet) {
					setOprname(currentNode.getNodeValue());
					isSet = true;
				}
				getSetOperations().add(currentNode.getNodeValue());
			} else {
				if (currentNode.getAttributes() != null) {
					NamedNodeMap attributes = currentNode.getAttributes();
					for (int j = 0; j < attributes.getLength(); j++) {
						Node item = attributes.item(j);
						System.out.println("OPERATIONS->attr->" + item.getNodeValue());
						if (!isSet) {
							setOprname(item.getNodeValue());
							isSet = true;
						}
						getSetOperations().add(item.getNodeValue());
					}

				}
			}

		}

		String arrayOperations[];
		if (getSetOperations() != null && !getSetOperations().isEmpty()) {
			arrayOperations = new String[getSetOperations().size()];
			Iterator<String> iterator = getSetOperations().iterator();
			int i = 0;
			while (iterator.hasNext()) {
				String next = iterator.next();
				arrayOperations[i] = next;
				i++;
			}
		} else {
			arrayOperations = new String[1];
			arrayOperations[0] = "";
		}

		// <wsdl:binding name="CardPswdAssignmentSvcBinding"
		// type="tns:CardPswdAssignmentSvc">
		nodeList = (NodeList) xpath.evaluate("//wsdl:binding/@name", getDocument(), XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeValue() != null) {
				setWsdlBinding((currentNode.getNodeValue()));
			} else {
				if (currentNode.getAttributes() != null) {
					NamedNodeMap attributes = currentNode.getAttributes();
					for (int j = 0; j < attributes.getLength(); j++) {
						Node item = attributes.item(j);
						setWsdlBinding((item.getNodeValue()));
						break;
					}
				}
			}
			break;
		}

		//// wsdl:portType/wsdl:operation[@name='modCardPswd']
		//// wsdl:portType/wsdl:operation[@name='modCardPswd']/wsdl:input/@message
		nodeList = (NodeList) xpath.evaluate("//wsdl:portType/wsdl:operation", getDocument(), XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			String key = null;
			StringBuffer valueBuf = new StringBuffer();

			if (currentNode.getAttributes() != null) {
				NamedNodeMap attributes = currentNode.getAttributes();
				Node item = attributes.item(0);
				key = item.getNodeValue();
			}
			NodeList nodeMsgList = (NodeList) currentNode.getChildNodes();
			if (nodeMsgList != null) {
				for (int x = 0; x < nodeMsgList.getLength(); x++) {
					Node currentMsgNode = nodeMsgList.item(x);
					if (currentMsgNode.getAttributes() != null) {
						NamedNodeMap attributes = currentMsgNode.getAttributes();
						Node item = attributes.item(0);
						valueBuf.append(item.getNodeValue()).append(";");
					}
				}
			}
			if (key != null && valueBuf.length() > 0) {
				mapOpMsgs.put(key, valueBuf.toString());
			}

		}
		
		
		nodeList = (NodeList) xpath.evaluate("//wsdl:message", getDocument(), XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			String key = null;
			String valueMsg = "";

			if (currentNode.getAttributes() != null) {
				NamedNodeMap attributes = currentNode.getAttributes();
				Node item = attributes.item(0);
				key = item.getNodeValue();
			}
			NodeList nodeMsgList = (NodeList) currentNode.getChildNodes();
			if (nodeMsgList != null) {
				for (int x = 0; x < nodeMsgList.getLength(); x++) {
					Node currentMsgNode = nodeMsgList.item(x);
					if (currentMsgNode.getAttributes() != null) {
						NamedNodeMap listAttrs = currentMsgNode.getAttributes();
						for (int j = 0; j < listAttrs.getLength(); j++) {
							Node item = listAttrs.item(j);
							if (item.getNodeName().equalsIgnoreCase("name")) {
								valueMsg = item.getNodeValue();
								break;
							}
						}
					}
				}
			}
			if (key != null && valueMsg.length() > 0) {
				mapMsgElements.put(key, valueMsg.toString());
			}
		}
		
		
		

		// <wsdl:portType name="CardPswdAssignmentSvc">
		nodeList = (NodeList) xpath.evaluate("//wsdl:portType/@name", getDocument(), XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeValue() != null) {
				setWsdlPort((currentNode.getNodeValue()));
			} else {
				if (currentNode.getAttributes() != null) {
					NamedNodeMap attributes = currentNode.getAttributes();
					for (int j = 0; j < attributes.getLength(); j++) {
						Node item = attributes.item(j);
						setWsdlPort((item.getNodeValue()));
						break;
					}
				}
			}
			break;
		}

		nodeList = (NodeList) xpath.evaluate("//wsdl:service/wsdl:port/@name", getDocument(), XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeValue() != null) {
				setWsdlSvcPort((currentNode.getNodeValue()));
			} else {
				if (currentNode.getAttributes() != null) {
					NamedNodeMap attributes = currentNode.getAttributes();
					for (int j = 0; j < attributes.getLength(); j++) {
						Node item = attributes.item(j);
						setWsdlSvcPort((item.getNodeValue()));
						break;
					}
				}
			}
			break;
		}

	}

	public LinkedHashSet<String> getSetNamespaces() {
		return setNamespaces;
	}

	public void setSetNamespaces(LinkedHashSet<String> setNamespaces) {
		this.setNamespaces = setNamespaces;
	}

	public LinkedHashSet<String> getSetOperations() {
		return setOperations;
	}

	public void setSetOperations(LinkedHashSet<String> setOperations) {
		this.setOperations = setOperations;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getOprname() {
		return oprname;
	}

	public void setOprname(String oprname) {
		this.oprname = oprname;
	}

	public String getWsdlBinding() {
		return wsdlBinding;
	}

	public void setWsdlBinding(String wsdlBinding) {
		this.wsdlBinding = wsdlBinding;
	}

	public String getWsdlPort() {
		return wsdlPort;
	}

	public void setWsdlPort(String wsdlPort) {
		this.wsdlPort = wsdlPort;
	}

	public String getWsdlSvcPort() {
		return wsdlSvcPort;
	}

	public void setWsdlSvcPort(String wsdlSvcPort) {
		this.wsdlSvcPort = wsdlSvcPort;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getMsgRq() {
		return msgRq;
	}

	public void setMsgRq(String msgRq) {
		this.msgRq = msgRq;
	}

	public String getMsgRs() {
		return msgRs;
	}

	public void setMsgRs(String msgRs) {
		this.msgRs = msgRs;
	}

	public HashMap<String, String> getMapOpMsgs() {
		return mapOpMsgs;
	}

	public void setMapOpMsgs(HashMap<String, String> mapOpMsgs) {
		this.mapOpMsgs = mapOpMsgs;
	}

	public HashMap<String, String> getMapMsgElements() {
		return mapMsgElements;
	}

	public void setMapMsgElements(HashMap<String, String> mapMsgElements) {
		this.mapMsgElements = mapMsgElements;
	}

}
