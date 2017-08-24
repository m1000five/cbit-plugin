package com.ath.esqltool.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class BUtil {

	private static HashMap<String, String> map = new HashMap<String, String>();

	static {
		map.put("security", "sec");
		map.put("management", "mgmt");
		map.put("creditcard", "cc");
		map.put("statement", "stmt");
		map.put("management", "mgmt");
		map.put("inquiry", "inq");
		map.put("modify", "mod");
		map.put("advise", "adv");
		map.put("customer", "cust");
		map.put("account", "acct");
		map.put("debitcard", "debit");
		map.put("relationship", "rel");
		map.put("pin", "");
	}

	public static String shortenText(String input) {

		String toWrite = input.toLowerCase();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			toWrite = toWrite.replaceAll(entry.getKey(), entry.getValue());
		}
		if (toWrite.length() > 28) {
			toWrite = toWrite.substring(0, 28);
		}
		return toWrite;
	}

	public static String capitalizeFirstLetterOfEachWord(String s) {
		char[] c = s.toCharArray();
		boolean b = true;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				b = true;
				continue; // no need to perform uppercase check, just go on
			}
			if (b && Character.isLetter(c[i])) { // only perform "isLetter" check if you want to capitalize the first
													// <b>real</b> letter - '4life' will be '4Life'
				c[i] = Character.toUpperCase(c[i]);
				b = false;
			}
		}
		return new String(c);
	}

	public static String getAcron(String srvName) {
		Scanner in = new Scanner(srvName);
		String isUp = "";
		String x = in.next();
		int z = x.length();
		for (int y = 0; y < z; y++) {
			if (Character.isUpperCase(x.charAt(y))) {
				char w = x.charAt(y);
				isUp = isUp + w;
			}
		}
		return isUp;
	}
	
	public static HashMap<String, String> genOthersNamespaces(LinkedHashSet<String> setOthersNamespaces) {
		HashMap<String, String> mapOthersNamespaces  = new HashMap<String, String>();
		
		if (setOthersNamespaces == null) {
			return mapOthersNamespaces;
		}
		
		Iterator<String> iterator = setOthersNamespaces.iterator();
		while (iterator.hasNext()) {
			String namespace = (String) iterator.next();
			System.out.println("DETERMINANDO PREFIJO: " + namespace);
			mapOthersNamespaces.put(getPrefix(namespace, mapOthersNamespaces.keySet()), namespace);
			
		}
		
//		facade.setNamespace("urn://grupoaval.com/customers/v1/");
//		facade.setPrefixns("v1");
//		mapOthersNamespaces.put("ifx", "urn://grupoaval.com/xsd/ifx/");
//		mapOthersNamespaces.put("v2", "urn://grupoaval.com/xsd/ifx/v2/");
//		mapOthersNamespaces.put("v3", "urn://grupoaval.com/xsd/ifx/v3/");
		
		return mapOthersNamespaces;
	}

	public static String getPrefix(String namespace, Set<String> currentSet) {
		// urn://grupoaval.com/xsd/ifx/v3/
		// http://com/ath/service/accounts/SvcAcctInq
		//  http://www.s1.com
		
		String tmpPrefix = "";
		
		
		StringTokenizer tokenizer = new StringTokenizer(namespace, "/");
		while (tokenizer.hasMoreElements()) {
			String currentToken = (String) tokenizer.nextElement();
			if (currentToken != "") {
				tmpPrefix = currentToken.toLowerCase();
			}
		}
		
		if (tmpPrefix.length() > 0) {
			if (tmpPrefix.indexOf(".") != -1) {
				StringTokenizer tokenizer2 = new StringTokenizer(namespace, ".");
				while (tokenizer2.hasMoreElements()) {
					String currentToken = (String) tokenizer2.nextElement();
					if (currentToken != "") {
						tmpPrefix = currentToken.toLowerCase();
					}
				}
			}
			tmpPrefix = tmpPrefix.length() > 3? tmpPrefix.substring(0, 3):tmpPrefix;
		} else {
			tmpPrefix = "ns";
		}
		
		if (currentSet != null && !currentSet.isEmpty()) {
			int i = 1;
			while (currentSet.contains(tmpPrefix)) {
				tmpPrefix = tmpPrefix + i;
			}
		}
		
		return tmpPrefix;
	}
	

	

}
