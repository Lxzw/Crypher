package com.l.mk.crypher.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LocalXMLParser {

	/**
	 * 從輸入流中獲得XML文件信息，轉換爲map對象
	 * @param is
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static Map<String,String> parseMessage(String path) 
			throws FileNotFoundException {
		return LocalXMLParser.parseMessage(new File(path));
	}
	
	/**
	 * 獲得XML文件對象信息，轉換爲map對象
	 * @param is
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static Map<String,String> parseMessage(File file) 
			throws FileNotFoundException {
		Map<String,String> map = new HashMap<String,String>();
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(file);
			Element root = document.getRootElement();
			
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element e : list) {
				map.put(e.getName(), e.getTextTrim());
			}
			System.out.println(root.getName());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
