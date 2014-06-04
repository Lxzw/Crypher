package com.l.mk.utils;

public class ByteUtil {
	
	/**
	 * �������ֽ�������кϲ�
	 * @param head �ϲ�֮��,����ʾ�Ĳ���
	 * @param end  �ϲ�֮��, ����ƴ�ӵĲ���
	 * @return byte[]  ����һ���ֽ��������
	 */
	public static byte[] merge(byte[] head, byte[] end) {
		byte[] t = new byte[head.length+end.length];
		System.arraycopy(head, 0, t, 0, head.length);
		System.arraycopy(end, 0, t, head.length, end.length);
		return t;
	}
	
	/**
	 * ��ciper��һ���ֽ���չ�������ַ���<br>
	 * ���磺�ֽ� 0x1A -> �ַ��� 1A
	 * @param cipher ��Ҫ��չ������
	 * @return byte[] ��չ�������
	 */
	public static String extend(byte[] cipher) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * ���ַ���ת�����ֽ�
	 * ���磺1A -> 0x1A
	 * @param cipher  �ַ�����ʾ������
	 * @return byte[] ѹ���������
	 */
	public static byte[] compress(String cipher) {
		return null;
	}
}
