package com.ath.esqltool.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class LinkedProperties extends Properties {

	private static final long serialVersionUID = 1L;

	private Map<Object, Object> linkMap = new LinkedHashMap<Object, Object>();

	@Override
	public synchronized Object put(Object key, Object value) {
		return linkMap.put(key, value);
	}

	@Override
	public synchronized boolean contains(Object value) {
		return linkMap.containsValue(value);
	}

	@Override
	public boolean containsValue(Object value) {
		return linkMap.containsValue(value);
	}

	@Override
	public synchronized Enumeration<Object> elements() {
		throw new UnsupportedOperationException(
				"Enumerations are so old-school, don't use them, " + "use keySet() or entrySet() instead");
	}

	@Override
	public Set<java.util.Map.Entry<Object, Object>> entrySet() {
		return linkMap.entrySet();
	}

	@Override
	public synchronized void clear() {
		linkMap.clear();
	}

	@Override
	public synchronized boolean containsKey(Object key) {
		return linkMap.containsKey(key);
	}

	@Override
	public synchronized Object setProperty(String key, String value) {
		return put(key, value);
	}

	public synchronized void store(OutputStream out, String comments) throws IOException {
		BufferedWriter awriter;
		awriter = new BufferedWriter(new OutputStreamWriter(out, "8859_1"));
		if (comments != null)
			writeln(awriter, "#" + comments);
		writeln(awriter, "#" + new Date().toString());
		for (Iterator<java.util.Map.Entry<Object, Object>> e = linkMap.entrySet().iterator(); e.hasNext();) {
			java.util.Map.Entry<Object, Object> entry = (java.util.Map.Entry<Object, Object>) e.next();

			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			key = saveConvert(key, true);

			/*
			 * No need to escape embedded and trailing spaces for value, hence pass false to
			 * flag.
			 */
			val = saveConvert(val, false);
			writeln(awriter, key + "=" + val);
		}
		awriter.flush();
	}

	private static void writeln(BufferedWriter bw, String s) throws IOException {
		bw.write(s);
		bw.newLine();
	}

	private String saveConvert(String theString, boolean escapeSpace) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
//			case ' ':
//				if (x == 0 || escapeSpace)
//					outBuffer.append('\\');
//				outBuffer.append(' ');
//				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
//			case '#': // Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/** A table of hex digits */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

}
