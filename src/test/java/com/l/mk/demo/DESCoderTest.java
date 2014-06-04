package com.l.mk.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

public class DESCoderTest {

	/**
	 * 测试
	 * @throws Exception 
	 * */
	@Test
	public void test() throws Exception {
		String inputStr = "DES";
		byte[]  inputData = inputStr.getBytes();
		System.out.println(inputData);
		System.out.println("原文：\t" + inputStr);
		//初始化密钥
		byte[] key = DESCoder.initKey();
		System.out.println("密钥：\t" + Base64.encodeBase64String(key));
		//System.out.println(new String(key));
		//加密
		inputData = DESCoder.encrypt(inputData, key);
		System.out.println("加密后：\t" + Base64.encodeBase64String(inputData));
		//解密
		byte[] outputData = DESCoder.decrypt(inputData, key);
		String outputString = new String(outputData);
		System.out.println("解密后：\t" + outputString);
		
	}

}
/**
 *引用《java加密与解密艺术》 第七章 初等数据加密-对称加密
 * */

