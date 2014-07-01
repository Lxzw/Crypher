package com.l.mk.crypher.format;

/**
 * @功能 填充器
 * @author L
 *
 */
public class Padding {
	
	/**
	 * @功能 将字符串右边填充字符串
	 * @param src 需要被填充的字符串
	 * @param size 字符串长度
	 * @param c 用来填充的字符
	 * @return
	 */
	public static String inRight(String src, int size, String c) {
		
		//字符串大于指定长度时，截取后面的长度
		if (src.length() > size) {
			return src.substring(src.length()-size,src.length());
		}
		
		int delta = size - src.length();
		for (int i=0; i<delta; i=i+c.length()) {
			src = src + c;
		}
		
		return src;
	} 
	/**
	 * @功能 在字符串左边填充字符
	 * @param src 需要被填充的字符串
	 * @param size 被填充后字符串长度
	 * @param c 被填充的字符
	 * @return
	 */
	public static String inLeft(String src, int size, String c) {
		
		//字符串大于指定长度时，截取后面的长度
		if (src.length() > size) {
			return src.substring(src.length()-size,src.length());
		}
		
		//字符长度左边填充
		int delta = size - src.length();
		for (int i=0; i<delta; i=i+c.length()) {
			src = c + src;
		}
		
		return src;
	}
	
//填充字符大于两个时，字符误差为 c.length() - 1; src.length() = size - 1 + c.length()
}
