package com.l.mk.demo;

import static org.junit.Assert.*;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

public class DESedeCoderTest {

	/**
	 * 测试
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		String inputStr = "DESede";
		byte[] inputData = inputStr.getBytes();
		System.out.println("原文：\t" + inputStr);
		
		//初始化密钥
		byte[] key = DESedeCoder.initKey();
		System.out.println("密钥：\t" + Base64.encodeBase64String(key));
		
		//加密
		inputData = DESedeCoder.encrypt(inputData, key);
		System.out.println("加密后：\t" + Base64.encodeBase64String(inputData));
		
		//解密
		byte[] outputData = DESedeCoder.decrypt(inputData, key);
		System.out.println("解密后\t" + Base64.encodeBase64String(outputData));
		String outputStr = new String(outputData);
		System.out.println("解密后:\t" + outputStr);
		
		//校验
		assertTrue(inputStr.equals(outputStr));
	}
	
	/**
	 * 验证是否可以用3个key来对密文进行加密
	 * @throws Exception 
	 */
	@Test
	public void test3key() throws Exception {
		String inputStr = "DESede";
		byte[] inputData = inputStr.getBytes();
		//输出原文
		System.out.println("原文：\t" + inputStr);
		
		byte[] key = {
				0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15,
			    0x00,0x0f,0x0e,0x11,0x12,0x13,0x14,0x15
        };
		System.out.println("密钥：\t" + Base64.encodeBase64String(key));
		
		inputData = DESedeCoder.encrypt(inputData, key);
		System.out.println("加密后：\t" + Base64.encodeBase64String(inputData));
		
		//初始化密钥 3个密钥
//		byte[] key1 = DESedeCoder.initKey();
//		byte[] key2 = DESedeCoder.initKey();
//		byte[] key3 = DESedeCoder.initKey();
//		
//		//加密 （key1）+ 解密 （key2）+加密（key3）
//		inputData = DESedeCoder.encrypt(inputData, key1);
//		inputData = DESedeCoder.decrypt(inputData, key2);
//		inputData = DESedeCoder.encrypt(inputData, key3);
	}

}
