package com.l.mk.crypher.format;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.l.mk.crypher.obj.Message;
import com.l.mk.crypher.obj.MessageHeader;

public class MessageDataTransferTest {

	@Test
	public void testGetMessageHeader() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("XM", "周星星");
		map.put("SPH", "0000000000000000000000000000000000000");
		map.put("KPRQ", "20130901");
		map.put("ZZJ", "1111111112");
		MessageHeader messageHeader = MessageDataTransfer.getMessageHeader(map);
		for (byte b : messageHeader.getKprq()) 
			System.out.printf("%-2x\n", b);
		for (byte b : messageHeader.getSph()) 
			System.out.printf("%-2x\n", b);
		for (byte b : messageHeader.getZzj()) 
			System.out.printf("%-2x\n", b);
		System.out.println(messageHeader.getLength());
		assertTrue(messageHeader.getLength() == 57);
	}

	@Test
	public void testGetMessage() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("XM","周星星" );
		map.put("ZJH", "330283198910102323");
		map.put("QSNY", "201309");
		map.put("ZZNY", "201311");
		map.put("SPH", "1111122222333334444");
		map.put("KPRQ", "20131101");
		map.put("SKHJ", "1700.00");
		map.put("JFYF", "9，10");
		map.put("ZZJ", "00000001");
		Message message = MessageDataTransfer.getMessage(map);
		//处理随访月份
		String[] sarray = map.get("JFYF").split(",|，");
		for (String s : sarray) 
			System.out.println(s);
		
	}

	@Test
	public void testMessageHeaderToMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testMessageToMap() {
		fail("Not yet implemented");
	}

}
