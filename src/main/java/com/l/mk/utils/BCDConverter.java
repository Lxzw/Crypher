package com.l.mk.utils;

/**
 * 
 * @author Administrator
 * 
 * BCD��-8421
 * ��10������ֵ����BCDѹ��
 * ��BCD�ֽ�ת��ʮ�����ֽ�����
 */
public class BCDConverter {
	/**
	 * ��һ���ֽ��������BCDѹ����ת������һ���ֽ�����
	 * @param src ��Ҫȥת�����ֽ�����
	 * @return byte[]  ת������ֽ�����
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
	 * ��һ��BCD��ʽ���ֽ����飬ת�����������ֽ�����
	 * @param src BCD������ֽ�����
	 * @return byte[] 
	 */
	public static byte[] fromBCD(byte[] src) {
		return null;
	}
	
}
