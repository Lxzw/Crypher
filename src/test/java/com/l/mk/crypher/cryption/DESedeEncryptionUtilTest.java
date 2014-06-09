package com.l.mk.crypher.cryption;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class DESedeEncryptionUtilTest {

	
	@Test
	public void test() throws Exception {
		byte[] ori_data = "hello，你好".getBytes();
		//中文不能作为密钥
		byte[] key = DESedeEncryptionUtil.initKey("hello", "1322", "sdfs"); 
		byte[] cipher = DESedeEncryptionUtil.encrypt(ori_data, key);
		byte[] data = DESedeEncryptionUtil.decrypt(cipher, key);
		Arrays.equals(ori_data, data);
		String s1 = new String(ori_data);
		String s2 = new String(data);
		System.out.println(s2);
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testInitKey() throws Exception {
		String key1 = "Hello World";
		String key2 = "How are you";
		String key3 = "12345678";
		
		byte[] k1 = DESedeEncryptionUtil.initKey(key1, key2, key3);
		byte[] k2 = DESedeEncryptionUtil.initKey(key1, key2, key3);
		
		assertTrue(Arrays.equals(k1, k2));
		for (int i=0; i < k1.length; i++) {
			System.out.printf("%x", k1[i]);
		}
		//判断字符串过长时，是否会越界
		String key4 = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
				+ "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
				+ "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
				+ "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
				+ "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
				+ "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
				+ "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		DESedeEncryptionUtil.initKey(key4, key4, key4);
	}

}
