package com.l.mk.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

public class DESCoderTest {

	/**
	 * ����
	 * @throws Exception 
	 * */
	@Test
	public void test() throws Exception {
		String inputStr = "DES";
		byte[]  inputData = inputStr.getBytes();
		System.out.println(inputData);
		System.out.println("ԭ�ģ�\t" + inputStr);
		//��ʼ����Կ
		byte[] key = DESCoder.initKey();
		System.out.println("��Կ��\t" + Base64.encodeBase64String(key));
		//System.out.println(new String(key));
		//����
		inputData = DESCoder.encrypt(inputData, key);
		System.out.println("���ܺ�\t" + Base64.encodeBase64String(inputData));
		//����
		byte[] outputData = DESCoder.decrypt(inputData, key);
		String outputString = new String(outputData);
		System.out.println("���ܺ�\t" + outputString);
		
	}

}
/**
 *���á�java��������������� ������ �������ݼ���-�ԳƼ���
 * */

