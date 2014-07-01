package com.l.mk.crypher.format;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
		messageHeader.setLength((byte)(40+len+map.get("ZJH").length()/2 + 1));
		messageHeader.setKprq(BCDConverter.toBCD(map.get("KPRQ")));
		int lens = map.get("SPH").length();
		//表示20位，不足左补零
		if (lens < 21) {
			int delta = 20 - lens;
			String sphString = map.get("SPH");
			for (int i=0; i<delta; i++) {
				sphString = "0" + sphString; 
			}
			messageHeader.setSph(BCDConverter.toBCD(sphString.substring(6,20)));
		} 
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
		
		//税款合计处理
		float skhj_float = Float.parseFloat(map.get("SKHJ"));
		int skhj_int =(int)(skhj_float * 100);
		String skhjsString = skhj_int + "";
		int skhjLen = skhjsString.length();
		if ((skhj_int + "").length() < 12) {
			for (int i=0; i<12-skhjLen; i++) {
				skhjsString = "0" + skhjsString;
			}
		}
		
		message.setSkhj(BCDConverter.toBCD(skhjsString));
		
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
			message.setLength(len);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			try {
				System.out.println(map.get("XM").getBytes("GBK").length);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		//证件号处理
		String zjh = map.get("ZJH");
		int len  = zjh.getBytes().length;
		byte lens = 0x00;
		if (len == 18 ) {
			lens = 0x01;
			if (zjh.substring(len-1, len).toUpperCase().equals("X")) {
				lens = 0x02;
				zjh = zjh.substring(0,zjh.length()-1);
			}
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
		System.arraycopy(BCDConverter.toBCD(zjh), 0, zjh_temp, 1,BCDConverter.toBCD(zjh).length);
		message.setZjh(zjh_temp);
		
		//缴费月份处理 
		// |0|0|6|5|4|3|2|1| 这样排列一个字节的位数
		String[] months = map.get("JFYF").split(",|，");
		byte[] temp = {0x01,0x02,0x04,0x08,0x10,0x20};//表示1-6月
		byte left = 0x00, right = 0x00;
		for (String s : months) {
			if (Integer.parseInt(s) > 6){
				right = (byte)(right + (temp[Integer.parseInt(s)-7]));
			}
			else {
				left = (byte)(left + temp[Integer.parseInt(s)-1]);
			}
		}
		byte[] mon = new byte[2];
		mon[0] = left;//1-6
		mon[1] = right;//7-12
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
		
		byte[] temp_Jym = new  byte[8];
		System.arraycopy(jym_b, jym_b.length-9, temp_Jym, 0, 8);
		message.setJym(temp_Jym);
		
		//自助机编号
		String zzjString = map.get("ZZJ");
		if (zzjString.length() < 8 ) {
			for (int i=0; i<8-map.get("ZZJ").length(); i++) {
				zzjString = "0" + zzjString;
			}
		} 
		message.setZzj(BCDConverter.toBCD(zzjString));
		
		return message;
		
	}
	
	/**
	 * 将Message对象转换 Map
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String,String> messageToMap(Message message) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String,String>();
		
		map.put("SPH", BCDConverter.fromBCD(message.getSph()));
		
		//税款合计处理
		String skhjString = BCDConverter.fromBCD(message.getSkhj());
		double skhjFloat = (Double.parseDouble(skhjString) / 100) ;
		System.err.println(String.format("%.2f", skhjFloat));
			
		map.put("SKHJ", String.format("%.2f", skhjFloat));
		
		map.put("QSNY", BCDConverter.fromBCD(message.getQsny()));
		map.put("ZZJ", BCDConverter.fromBCD(message.getZzj()));
		map.put("KPRQ", BCDConverter.fromBCD(message.getKprq()));
		map.put("ZZNY", BCDConverter.fromBCD(message.getZzny()));
		
		
		//姓名处理
		byte[] temp_name = message.getName();
		byte[] real_name = new byte[temp_name[0]];
		System.arraycopy(temp_name, 1, real_name, 0, real_name.length);
		String nameString = new String(real_name,"GBK");
		map.put("XM", nameString);
		//证件处理 --仅仅完成了身份证认证
		byte[] temp_zjh = message.getZjh();
		if (temp_zjh[0] == 0x01 || temp_zjh[0] == 0x02) {
			byte[] b = new byte[9];
			System.arraycopy(message.getZjh(), 1, b, 0 , b.length);
			String string  = BCDConverter.fromBCD(b);
			if (temp_zjh[0] == 0x02) {
				string = string.substring(1,string.length()) + "X";
			}
			map.put("ZJH", string);
		}
		
		//缴费月份
		byte[] temp_jfyf = message.getJfyf();
		byte left = temp_jfyf[0];
		byte right = temp_jfyf[1];
		String jfyfString  = "";
		for (int i = 1; i <= 6; i++ ) {
			if (((left>>i-1)&0x01) == 0x01) {
				jfyfString = jfyfString + "" + i + ",";
			}
		}
		for (int i = 7; i <= 12; i++ ) {
			if (((right>>i-7)&0x01) == 0x01) {
				jfyfString = jfyfString + "" + i + ",";
			}
		}
		map.put("JFYF", jfyfString.substring(0, jfyfString.lastIndexOf(",")));
		return map;
	}
	
//	private static byte[] md5(String clear) {
//		byte[] b = null;
//		try {
//			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//			messageDigest.update(clear.getBytes());
//			b = messageDigest.digest();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;
//	}
	
	/**
	 * 将MessageHeader对象转换为map
	 * @param header
	 * @return
	 */
	public static Map<String,String> messageHeaderToMap(MessageHeader header) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("SPH", BCDConverter.fromBCD(header.getSph()));
		map.put("KPRQ", BCDConverter.fromBCD(header.getKprq()));
		map.put("ZZJ", BCDConverter.fromBCD(header.getZzj()));
		return map;
	}
	/**
	 * @功能 对数据进行标准化处理
	 *		@1 去空格
	 *		@2 字符串长度
	 * @param map
	 * @return
	 */
	public static Map<String, String> formatMap(Map<String, String> map) {
		map.put("SPH", Padding.inLeft(map.get("SPH").replace(" ", ""), 20, "0"));
		map.put("SKHJ", Padding.inLeft(map.get("SKHJ").replaceAll(" ", ""), 12, "0"));
		map.put("JFYF",map.get("JFYF").replaceAll(" ", ""));
		map.put("ZZJ", Padding.inLeft(map.get("ZZJ").replaceAll(" ",""), 8, "0"));
		if (map.get("QSNY").replaceAll(" ", "").length() != 6 ) {
			System.err.println("QSNY:数据格式有问题检查是否是\"201306\"的形式|"+map.get("QSNY"));
			map.put("QSNY", Padding.inLeft(map.get("QSNY").replace(" ", ""), 6, "0"));
		}
		if (map.get("ZZNY").replaceAll(" ", "").length() != 6 ) {
			System.err.println("ZZNY:数据格式有问题检查是否是\"201306\"的形式|"+map.get("ZZNY"));
			map.put("ZZNY", Padding.inLeft(map.get("ZZNY").replace(" ", ""), 6, "0"));
		}
		if (map.get("KPRQ").replaceAll(" ", "").length() != 8 ) {
			System.err.println("KPRQ:数据格式有问题检查是否是\"20130601\"的形式|"+map.get("KPRQ"));
			map.put("KPRQ", Padding.inLeft(map.get("KPRQ").replace(" ", ""), 8, "0"));
		}
		if (map.get("ZJH").replaceAll(" ", "").length() != 18) {
			System.err.println("ZJH:不是18位身份证格式|" + map.get("ZJH"));
			map.put("ZJH", Padding.inLeft(map.get("ZJH").replaceAll(" ", ""), 18, "0"));
		}
		return map;
	}
	
}
