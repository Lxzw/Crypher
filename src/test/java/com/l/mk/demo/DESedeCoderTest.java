package com.l.mk.demo;

import static org.junit.Assert.*;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

public class DESedeCoderTest {

	/**
	 * ����
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		String inputStr = "DESededdddddddd";
		byte[] inputData = inputStr.getBytes();
		System.out.println("原文：\t" + inputStr);
		
		//��ʼ����Կ
		byte[] key = DESedeCoder.initKey();
		System.out.println("密钥：\t" + Base64.encodeBase64String(key));
		
		//����
		inputData = DESedeCoder.encrypt(inputData, key);
		System.out.println("密文：\t" + Base64.encodeBase64String(inputData));
		
		//����
		byte[] outputData = DESedeCoder.decrypt(inputData, key);
		System.out.println("解密：\t" + Base64.encodeBase64String(outputData));
		String outputStr = new String(outputData);
		System.out.println("���ܺ�:\t" + outputStr);
		
		//У��
		assertTrue(inputStr.equals(outputStr));
	}
	
	/**
	 * ��֤�Ƿ������3��key�������Ľ��м���
	 * @throws Exception 
	 */
	@Test
	public void test3key() throws Exception {
		String inputStr = "DESede";
		byte[] inputData = inputStr.getBytes();
		//���ԭ��
		System.out.println("ԭ�ģ�\t" + inputStr);
		
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
		System.out.println("��Կ��\t" + Base64.encodeBase64String(key));
		
		inputData = DESedeCoder.encrypt(inputData, key);
		System.out.println("���ܺ�\t" + Base64.encodeBase64String(inputData));
		
		//��ʼ����Կ 3����Կ
//		byte[] key1 = DESedeCoder.initKey();
//		byte[] key2 = DESedeCoder.initKey();
//		byte[] key3 = DESedeCoder.initKey();
//		
//		//���� ��key1��+ ���� ��key2��+���ܣ�key3��
//		inputData = DESedeCoder.encrypt(inputData, key1);
//		inputData = DESedeCoder.decrypt(inputData, key2);
//		inputData = DESedeCoder.encrypt(inputData, key3);
	}

}
