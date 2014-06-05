package com.l.mk.utils;

import java.util.HashMap;
import java.util.Map;

public class ByteUtil {
	
	/**
	 * 将两个字节数组进行合并
	 * @param head 合并之后,先显示的部分
	 * @param end  合并之后, 后面拼接的部分
	 * @return byte[]  返回一个字节数组对象
	 */
	public static byte[] merge(byte[] head, byte[] end) {
		byte[] t = new byte[head.length+end.length];
		System.arraycopy(head, 0, t, 0, head.length);
		System.arraycopy(end, 0, t, head.length, end.length);
		return t;
	}
	
	/**
	 * 在ciper中一个字节扩展成连个字符串<br>
	 * 例如：字节 0x1A -> 字符串 1A
	 * @param cipher 需要扩展的密文
	 * @return byte[] 扩展后的密文
	 */
	public static String extend(byte[] cipher) {
		// TODO Auto-generated method stub
		StringBuffer s = new StringBuffer();
		for (int i=0; i < cipher.length; i++) {
			s.append(byte22(cipher[i]));
		}
		return s.toString();
	}
	/**
	 * 将一个字节转化为两个字节
	 * @param b 一个字节
	 * @return byte[2] 两个字节的数字
	 */
	private static String byte22(byte b) {
		char[] template = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		int right = (b & 0x0f);
		int left = (b >> 4);
		StringBuffer s = new StringBuffer();
		s.append(template[left]);
		s.append(template[right]);
		return s.toString();	
	}
	
	/**
	 * 将字符处转化问字节
	 * 例如：1A -> 0x1A
	 * @param cipher  字符串表示的密文
	 * @return byte[] 压缩后的密文
	 */
	public static byte[] compress(String cipher) {
		Map<Character,Byte> m = new HashMap<Character,Byte>();
		m.put('0', (byte) 0x00);
		m.put('1', (byte) 0x01);
		m.put('2', (byte) 0x02);
		m.put('3', (byte) 0x03);
		m.put('4', (byte) 0x04);
		m.put('5', (byte) 0x05);
		m.put('6', (byte) 0x06);
		m.put('7', (byte) 0x07);
		m.put('8', (byte) 0x08);
		m.put('9', (byte) 0x09);
		m.put('A', (byte) 0x0A);
		m.put('B', (byte) 0x0B);
		m.put('C', (byte) 0x0C);
		m.put('D', (byte) 0x0D);
		m.put('E', (byte) 0x0E);
		m.put('F', (byte) 0x0F);
		byte[] temp = new byte[cipher.length()/2];
		for (int i=0; i<cipher.length()/2; i++) {
			byte left = (byte) ((m.get(cipher.charAt(2*i)) << 4));
			byte right = m.get(cipher.charAt(2*i + 1 ));
			temp[i] =(byte)(left + right);
		}
		return temp;
	}
}
