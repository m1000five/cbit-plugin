package com.ath.esqltool.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
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

	public AnalyzerBrokerXml() {
		super();
		dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true); // This is really important, without it that XPath does not work

		xpath = XPathFactory.newInstance().newXPath();
	}

	public void init(File inputSource) throws ParserConfigurationException, SAXException, IOException {
		dbf.setNamespaceAware(true); // This is really important, without it that XPath does not work
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = (db.parse(inputSource)); // inputSource, inputStream or file which contains your XML.
	}

	public void extractOverrides(String destinationProperties) throws XPathExpressionException, IOException {

		NodeList nodeList = (NodeList) xpath.evaluate("/Broker/CompiledMessageFlow/ConfigurableProperty[@override]",
				document, XPathConstants.NODESET);

		LinkedProperties prop = new LinkedProperties();
		OutputStream output = null;

		output = new FileOutputStream(destinationProperties);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			if (currentNode.getAttributes() != null) {
				NamedNodeMap attributes = currentNode.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++) {
					Node item = attributes.item(j);

					// ConfigurableProperty->attr->override -> EXCEPTION.REGISTER.REQ
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#MQOUT.EXCEPTION.REGISTER.REQ.queueName
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#MQOUT.EXCEPTION.REGISTER.REQ.validateMaster
					// ConfigurableProperty->attr->override -> ESBDATA
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#GetReplyIdentifierLog_WS.dataSource
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#CardPswdAssignmentSvc_SOAPReply.validateMaster
					// ConfigurableProperty->attr->override -> ESBDATA
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#CmpCreateMsgError.dataSource
					// ConfigurableProperty->attr->override -> LOG.REGISTER.REQ
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#MQOUT.LOG.REGISTER.REQ.queueName
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#MQOUT.LOG.REGISTER.REQ.validateMaster
					// ConfigurableProperty->attr->override -> CardPswdAssignmentSvc__BABN.FCD.RES
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_RES#CardPswdAssignmentSvc.FCD.RES.queueName
					// ConfigurableProperty->attr->override -> CardPswdAssignmentSvc.xml
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#name
					// ConfigurableProperty->attr->override -> BABN
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#UDP_Channel
					// ConfigurableProperty->attr->override -> CardPswdAssignmentSvc
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#UDP_Application
					// ConfigurableProperty->attr->override ->
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#UDP_BankId
					// ConfigurableProperty->attr->override -> ESBDATA
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#GetInfoError_WS.dataSource
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#CardPswdAssignmentSvc_SOAPReply.validateMaster
					// ConfigurableProperty->attr->override -> ESBDATA
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#CreateDBErrorRespWS.dataSource
					// ConfigurableProperty->attr->override -> ESBDATA
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#CreateErrorRespWS.dataSource
					// ConfigurableProperty->attr->override -> EXCEPTION.REGISTER.REQ
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#MQOUT.EXCEPTION.REGISTER.REQ.queueName
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#MQOUT.EXCEPTION.REGISTER.REQ.validateMaster
					// ConfigurableProperty->attr->override -> LOG.REGISTER.REQ
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#MQOUT.LOG.REGISTER.REQ.queueName
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#MQOUT.LOG.REGISTER.REQ.validateMaster
					// ConfigurableProperty->attr->override -> ESBDATA
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#CmpRouteMsg.dataSource
					// ConfigurableProperty->attr->override -> CardPswdAssignmentSvc__BABN.FCD.RES
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#MQOUT.GENERIC.SRV.REQ.replyToQ
					// ConfigurableProperty->attr->override -> none
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#MQOUT.GENERIC.SRV.REQ.validateMaster
					// ConfigurableProperty->attr->override ->
					// /Customers/Services/CardPswdAssignmentSvc
					// ConfigurableProperty->attr->uri ->
					// com.ath.services.customers.CardPswdAssignmentSvcFcdWs_REQ#SOAP
					// Input.urlSelector

					// sampleFlow#MQ Input.queueName=NEW_INPUT_QUEUE
					// sampleFlow#sampleSubflow1.queueName
					// sampleSubflow1#queueName
					// SUBOUT=NEW_SUBOUT

					System.out.println(
							"ConfigurableProperty->attr->" + item.getNodeName() + " -> " + item.getNodeValue());
					if (item.getNodeName().equalsIgnoreCase("override")) {
						Node nextItem = attributes.item(j + 1);
						if (nextItem.getNodeName().equalsIgnoreCase("uri")) {
							// StringTokenizer tokenizer = new StringTokenizer(nextItem.getNodeValue(),
							// "#");
							prop.setProperty(nextItem.getNodeValue(), item.getNodeValue());
						}

					}

				}

			}

		}

		prop.store(output, null);
	}

}
