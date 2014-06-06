package com.l.mk.utils;

/**
 * 
 * @author Administrator
 * 
 * BCD码-8421
 * 将10进制数值进行BCD压缩
 * 将BCD字节转换十进制字节数组
 */
public class BCDConverter {
	/**
	 * 对一个字节数组进行BCD压缩，转换成另一个字节数组
	 * @param src 需要去转换的字节数组
	 * @return byte[]  转换后的字节数组
	 */
	public static  byte[] toBCD(String src) {
		int length;
		if (src.length() % 2 == 1) {
			src = "0" + src;
		}
		length = src.length() / 2;
		byte[] temp = new byte[length];
		byte[] str = src.getBytes();
		for (int i=0; i<length; i++) {
			if (str[2*i] >= '0' && str[2*i] <= '9' &&
					str[2*i+1] >= '0' && str[2*i+1] <='9') {
				byte left = (byte)((str[2*i] - '0') << 4);
				byte right = (byte)(str[2*i+1] - '0');
				temp[i] = (byte)(left + right);
			}
		}
		
		return temp;
	}
	
	/**
	 * 对一个BCD格式的字节数组，转换成正常的字节数组
	 * @param src BCD编码的字节数组
	 * @return String 将bcd编码格式的字节数组转化为 字符串返回。
	 */
	public static String fromBCD(byte[] src) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < src.length; i++) {
			byte left = (byte)(src[i] & 0xf0 >>> 4 );
			byte right = (byte)(src[i] & 0x0f);
			sb.append(left);
			sb.append(right);
		}
		return sb.toString();
	}
	
}
