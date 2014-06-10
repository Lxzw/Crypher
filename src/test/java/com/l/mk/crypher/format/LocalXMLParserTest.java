package com.l.mk.crypher.format;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Test;

public class LocalXMLParserTest {
	
	@Test
	public void testParseMessage() throws FileNotFoundException {
		File file = new File("E:/123.xml");
		Map<String,String> map = LocalXMLParser.parseMessage(file);
		assertTrue(map.get("XM").equals("周星星"));
		assertTrue(map.get("JFYF").equals("9,10"));
	}
	
}