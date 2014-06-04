package com.l.mk.utils;

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
		return null;
	}
	
	/**
	 * 将字符处转化问字节
	 * 例如：1A -> 0x1A
	 * @param cipher  字符串表示的密文
	 * @return byte[] 压缩后的密文
	 */
	public static byte[] compress(String cipher) {
		return null;
	}
}
