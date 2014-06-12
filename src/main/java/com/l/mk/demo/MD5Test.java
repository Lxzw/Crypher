package com.l.mk.demo;

import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
	/**
	 * 将字节数组进行MD5处理，并返回生成的最后16个字节
	 * 
	 * @param src
	 * @return
	 */
	public static byte[] getMD5Last8(byte[] src) {
		byte[] b = new byte[8];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(src);
			byte[] s = md.digest();
			for (byte i : s) {
				System.out.printf("%x", i);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}
	
	public static void main(String[] args) {
		MD5Test.getMD5Last8(new byte[] {0x11,0x10});
		System.out.println();
		MD5Test.getMD5Last8(new byte[] {0x11,0x11});
		MD5Test.getMD5Last8(new byte[] {0x11,0x10});
		System.out.println();
		MD5Test.getMD5Last8(new byte[] {0x11,0x11});
	}
}
