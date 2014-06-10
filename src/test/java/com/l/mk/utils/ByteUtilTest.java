package com.l.mk.utils;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;

import com.l.mk.crypher.format.ByteUtil;

public class ByteUtilTest {

	@Test
	public void testMerge() {
	}

	@Test
	public void testExtend() {
		byte[] s= {0x1f,0x1e,0x2d,'a'};
		assertTrue("1F1E2D61".equals(ByteUtil.extend(s)));
	}

	@Test
	public void testCompress() {
		String s = "1F1E2D61";
		byte[] b = ByteUtil.compress(s);
		assertTrue(b.length * 2 == s.length());
		for(int i=0; i<b.length; i++) {
			System.out.print(b[i]);
			System.out.printf("-%x\n", b[i]);
		}
	}
	
	/**
	 * ���Խ��ַ����ֽ�����ʱ�Ĳ�ͬЧ��
	 * 
	 * û�п�����ͬ��Ч��
	 */
	@Test
	public void test() {
		String s = "Hello World"; 
		byte[] sDefault = s.getBytes();
		System.out.print(s.length() + " : " + sDefault.length + ":");
		for (int i=0; i<sDefault.length; i++) {
			System.out.print(" " + sDefault[i]);
		}
		//
		System.out.println(" " + Charset.defaultCharset().name());
		byte[] sUtf8 = s.getBytes(Charset.forName("utf-8"));
		System.out.print(s.length() + " : " + sUtf8.length + ":");
		for (int i=0; i<sUtf8.length; i++) {
			System.out.print(" " + sUtf8[i]);
		}
		System.out.println(" " + Charset.defaultCharset().name());
		
		//���һ���ֽ�������λ������λ��ʮ���Ƶ�ֵ
		byte a = 'a';
		int left = a & 0x0f;
		int right = a >> 4;
		System.out.println("left: " + left + ";" + "right: " + right);
		
	}

}
