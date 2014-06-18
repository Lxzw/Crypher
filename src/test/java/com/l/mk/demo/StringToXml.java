package com.l.mk.demo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class StringToXml {
	public static void main(String[] arg) {
		String xmlString = "<?xml version='1.0' encoding='utf-8'?>"
	+ "		<ROOT>	<XM>周星星</XM> <ZJH>330283198910102323</ZJH></ROOT>";
		try {
			Document document = DocumentHelper.parseText(xmlString);
			Element rElement = document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
