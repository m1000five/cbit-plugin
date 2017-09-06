package com.ath.esqltool.application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.ath.esqltool.util.AnalyzerBrokerXml;
import com.ath.esqltool.util.LinkedProperties;

public class BARUtil {

	private LinkedProperties properties = new LinkedProperties();

	public static void main(String[] args) {
		//
		BARUtil barUtil = new BARUtil();

		String barPath = null;
		if (args == null || args.length == 0) {
			barPath = "C:\\Users\\milton.vega\\eclipse-workspace\\tmp\\CardPswdAssignmentSvcFcdWs.bar";
		} else {
			barPath = args[0];
		}
		try {
			File fileBAR = new File(barPath);

			String csvOfBrokerFiles = extractBarAndGetBrokers(barPath);
			// URL url = barUtil.getClass().getResource("/broker.xml");
			System.out.println("BROKER-FILE------------>" + csvOfBrokerFiles);

			StringTokenizer tokenizer = new StringTokenizer(csvOfBrokerFiles, ";");

			while (tokenizer.hasMoreTokens()) {
				String brokerFile = (String) tokenizer.nextToken();
				if (brokerFile.length() > 0) {
					File fileXml = new File(brokerFile);

					String fileNameBar = fileBAR.getName();
					if (fileNameBar.indexOf(".bar") != -1) {
						fileNameBar = fileNameBar.substring(0, fileNameBar.indexOf(".bar"));
						if (fileNameBar.indexOf("project.generated") != -1) {
							fileNameBar = fileNameBar.substring(0, fileNameBar.indexOf("project.generated"));
						}
					}

					barUtil.extractAndPopulateOverrides(fileXml, fileNameBar);

				}

			}
			
			LinkedProperties propertiesFile = barUtil.getProperties();

			OutputStream output = null;

			String current = new java.io.File(".").getCanonicalPath();

			output = new FileOutputStream(current + "/config.properties");

			propertiesFile.store(output, null);

		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void extractAndPopulateOverrides(File file, String appName) {
		AnalyzerBrokerXml analyzer = new AnalyzerBrokerXml();

		try {

			analyzer.init(file);
			analyzer.extractOverrides(appName);

			HashMap<String, String> mapOverrideProperties = analyzer.getMapOverrideProperties();

			if (!mapOverrideProperties.isEmpty()) {
				Set<String> keySet = mapOverrideProperties.keySet();
				Iterator<String> iterator = keySet.iterator();
				while (iterator.hasNext()) {
					String keyProp = (String) iterator.next();
					properties.setProperty(keyProp, mapOverrideProperties.get(keyProp));

				}

			}

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

	static public String extractBarAndGetBrokers(String zipFile) throws ZipException, IOException {

		System.out.println(zipFile);

		int BUFFER = 2048;
		File file = new File(zipFile);

		ZipFile zip = new ZipFile(file);

		StringBuffer buffer2 = new StringBuffer();

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
				lastExtracted = extractBarAndGetBrokers(destFile.getAbsolutePath());

				if (lastExtracted.endsWith("broker.xml")) {
					buffer2.append(lastExtracted);
					buffer2.append(";");
				}

			}
		}
		return buffer2.toString();
	}

	public LinkedProperties getProperties() {
		return properties;
	}

	public void setProperties(LinkedProperties properties) {
		this.properties = properties;
	}

}
