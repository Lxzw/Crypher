package com.l.mk.crypher.format;

import java.util.HashMap;
import java.util.Map;

public class ByteUtil {
	
	/**
	 * 将两个字节数组合并成一个字节数组
	 * @param head
	 * @param end
	 * @return
	 */
	public static byte[] merge(byte[] head, byte[] end) {
		byte[] t = new byte[head.length+end.length];
		System.arraycopy(head, 0, t, 0, head.length);
		System.arraycopy(end, 0, t, head.length, end.length);
		return t;
	}
	
	
	/**
	 * ��ciper��һ���ֽ���չ�������ַ�<br>
	 * ���磺�ֽ� 0x1A -> �ַ� 1A
	 * @param cipher ��Ҫ��չ������
	 * @return byte[] ��չ�������
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
	 * ��һ���ֽ�ת��Ϊ�����ֽ�
	 * @param b һ���ֽ�
	 * @return byte[2] �����ֽڵ�����
	 */
	private static String byte22(byte b) {
		char[] template = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		int right = (b & 0x0f);
		int left = ((b & 0xf0) >> 4);
		StringBuffer s = new StringBuffer();
		s.append(template[left]);
		s.append(template[right]);
		return s.toString();	
	}
	
	/**
	 * ���ַ�ת�����ֽ�
	 * ���磺1A -> 0x1A
	 * @param cipher  �ַ��ʾ������
	 * @return byte[] ѹ���������
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
