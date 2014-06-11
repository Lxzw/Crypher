package com.l.mk.crypher.format;

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
	 * ��һ��BCD��ʽ���ֽ����飬ת��������ֽ�����
	 * @param src BCD������ֽ�����
	 * @return String ��bcd�����ʽ���ֽ�����ת��Ϊ �ַ��ء�
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
