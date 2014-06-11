package com.l.mk.crypher.format;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.l.mk.crypher.obj.Message;
import com.l.mk.crypher.obj.MessageHeader;

public class MessageDataTransfer {

	/**
	 * 将map对象转换为数据报文头
	 * @param map
	 * @return
	 */
	public static MessageHeader getMessageHeader(Map<String,String> map) {
		String xm = map.get("XM");
		MessageHeader messageHeader = new MessageHeader();
		
		//一个中文转换为两个字节
		int len = 0;
		try {
			len = xm.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageHeader.setLength((byte)(51+len));
		messageHeader.setKprq(BCDConverter.toBCD(map.get("KPRQ")));
		messageHeader.setSph(BCDConverter.toBCD(map.get("SPH")));
		messageHeader.setZzj(BCDConverter.toBCD(map.get("ZZJ")));
		return messageHeader;
	}
	
	/**
	 * 将map对象的数据转换为Message对象
	 * @param map
	 * @return
	 */
	public static Message getMessage(Map<String,String> map) {
		Message message = new Message();
		message.setSph(BCDConverter.toBCD(map.get("SPH")));
		message.setSkhj(BCDConverter.toBCD(map.get("SKHJ")));
		message.setQsny(BCDConverter.toBCD(map.get("QSNY")));
		message.setZzj(BCDConverter.toBCD(map.get("ZZJ")));
		message.setKprq(BCDConverter.toBCD(map.get("KPRQ")));
		message.setZzny(BCDConverter.toBCD(map.get("ZZNY")));
		
		//姓名处理
		try {
			byte[] name = map.get("XM").getBytes("GBK");
			byte len = (byte)name.length;
			byte[] temp = new byte[30];
			temp[0] = len;
			System.arraycopy(name, 0, temp, 1, name.length);
			message.setName(temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//证件号处理
		String zjh = map.get("ZJH");
		int len  = zjh.getBytes().length;
		byte lens = 0x00;
		if (len == 18 ) {
			lens = 0x01;
			if (zjh.substring(len-1, len).toUpperCase().equals("X")) 
				lens = 0x02;
				zjh = zjh.substring(0,zjh.length()-2);
		} else {
			if (len > 30) {
				lens = 0x1e;
				zjh = zjh.substring(len-32, len-2);
			} else {
				lens = (byte)len;
			}
		}
		byte[] zjh_temp = new byte[16];
		
		//
		
		return message;
	}
	
	/**
	 * 将MessageHeader对象转换为map
	 * @param header
	 * @return
	 */
	public static Map<String,String> messageHeaderToMap(MessageHeader header) {
		return null;
	}
	
	/**
	 * 将Message对象转换 Map
	 */
	public static Map<String,String> messageToMap(Message message) {
		return null;
	}
	
}
