package com.l.mk.crypher.format;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		messageHeader.setLength((byte)(41+len+map.get("ZJH").length()/2 + 1));
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
				zjh = zjh.substring(len-31, len-1);
			} else {
				lens = (byte)len;
			}
		}
		byte[] zjh_temp = new byte[16];
		zjh_temp[0] = lens;
		System.arraycopy(BCDConverter.toBCD(zjh), 0, zjh_temp, 0,BCDConverter.toBCD(zjh).length);
		message.setZjh(zjh_temp);
		
		//缴费月份处理 
		// |0|0|6|5|4|3|2|1| 这样排列一个字节的位数
		String[] months = map.get("JFYF").split(",|，");
		byte[] temp = {0x01,0x02,0x04,0x08,0x10,0x20};//表示1-6月
		byte left = 0x00, right = 0x00;
		for (String s : months) {
			if (Integer.parseInt(s) > 6)
				right =(byte)(right + temp[Integer.parseInt(s) - 7]);
			else {
				left = (byte)(left + temp[Integer.parseInt(s)-1]);
			}
		}
		byte[] mon = new byte[2];
		mon[0] = left;
		mon[1] = right;
		message.setJfyf(mon);
		
		//校验码处理MD5
		int Lsph = message.getSph().length;
		int Lname = message.getName().length;
		int Lskhj = message.getSkhj().length;
		int Lqsny = message.getQsny().length;
		int Lkprq = message.getKprq().length;
		int Lzzny = message.getZzny().length;
		int Lzjh  = message.getZjh().length;
		int Ljfyf = message.getJfyf().length;
		int Lzzj  = message.getZzj().length;
		byte[] jym_b = new byte[Lsph + Lname + Lskhj + Lqsny +Lkprq
		                      + Lzzny +Lzjh + Ljfyf + Lzzj];		
		System.arraycopy(message.getSph(), 0, jym_b, 0, Lsph);
		System.arraycopy(message.getName(), 0, jym_b, Lsph, Lname);
		System.arraycopy(message.getSkhj(), 0, jym_b, Lsph + Lname, Lskhj);
		System.arraycopy(message.getQsny(), 0, jym_b, Lsph + Lname + Lskhj, Lqsny);
		System.arraycopy(message.getKprq(), 0, jym_b, Lsph + Lname + Lskhj + Lqsny, Lkprq);
		System.arraycopy(message.getZzny(), 0, jym_b, Lsph + Lname + Lskhj + Lqsny + Lkprq, Lzzny);
		System.arraycopy(message.getZjh(), 0,  jym_b, Lsph + Lname + Lskhj + Lqsny + Lkprq + Lzzny, Lzjh);
		System.arraycopy(message.getJfyf(), 0, jym_b, Lsph + Lname + Lskhj + Lqsny + Lkprq + Lzzny + Lzjh, Ljfyf);
		System.arraycopy(message.getZzj(), 0, jym_b, Lsph + Lname + Lskhj + Lqsny + Lkprq + Lzzny + Lzjh + Ljfyf, Lzzj);
		
		String src = ByteUtil.extend(jym_b);
		byte[] temp_Jym = new  byte[8];
		System.arraycopy(jym_b, jym_b.length-9, temp, 0, 8);
		message.setJym(temp_Jym);
		
		return message;
		
	}
	
	private static byte[] md5(String clear) {
		byte[] b = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(clear.getBytes());
			b = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
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
