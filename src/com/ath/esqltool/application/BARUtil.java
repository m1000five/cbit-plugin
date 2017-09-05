package com.ath.esqltool.application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.ath.esqltool.util.AnalyzerBrokerXml;

public class BARUtil {

	public static void main(String[] args) {
		//
		BARUtil barUtil = new BARUtil();

		String barPath = null;
		if (args == null || args.length == 0) {
			barPath = "C:\\workspace_ath\\work108\\GeneratedBarFiles\\CardPswdAssignmentSvcFcdWsproject.generated.bar";
		} else {
			barPath = args[0];
		} 
		try {
			String brokerFile = extractBarAndGetBroker(barPath);
			//URL url = barUtil.getClass().getResource("/broker.xml");
			System.out.println("BROKER-FILE------------>" + brokerFile);
			File file = new File(brokerFile);
			barUtil.extractOverrides(file);
			
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void extractOverrides(File file) {
		AnalyzerBrokerXml analyzer = new AnalyzerBrokerXml();
		String current;
		try {

			current = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + current);

			analyzer.init(file);
			analyzer.extractOverrides(current + "/config.properties");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

	static public String extractBarAndGetBroker(String zipFile) throws ZipException, IOException {
		
		System.out.println(zipFile);
		
		int BUFFER = 2048;
		File file = new File(zipFile);

		ZipFile zip = new ZipFile(file);
		String nameOfZipFile = zipFile.substring(0, zipFile.length() - 4);
		if (zipFile.endsWith("appzip")) {
			nameOfZipFile = zipFile.substring(0, zipFile.length() - 7);
		}

		new File(nameOfZipFile).mkdir();
		Enumeration zipFileEntries = zip.entries();
		
		String lastExtracted = nameOfZipFile;

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			String currentEntry = entry.getName();
			File destFile = new File(nameOfZipFile, currentEntry);
			// destFile = new File(newPath, destFile.getName());
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			if (!entry.isDirectory()) {
				BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();
			}

			if (currentEntry.endsWith("broker.xml")) {
				return nameOfZipFile + "/" + currentEntry;
			}

			if (currentEntry.endsWith(".zip") || currentEntry.endsWith(".appzip")) {
				// found a zip file, try to open
				lastExtracted = extractBarAndGetBroker(destFile.getAbsolutePath());
			}
		}
		return lastExtracted;
	}

}
