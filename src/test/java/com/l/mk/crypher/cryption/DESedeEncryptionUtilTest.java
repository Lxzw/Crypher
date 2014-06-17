package com.l.mk.crypher.cryption;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.crypto.spec.DESedeKeySpec;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

public class DESedeEncryptionUtilTest {

	
	@Test
	public void test() throws Exception {
	}

	@Test
	public void testInitKey() throws Exception {
		byte[] bytes = new byte[24];
		bytes[0] = 0x12;
		bytes[1] = 0x09;
		DESedeKeySpec key = new DESedeKeySpec(bytes);
		for (byte b : key.getKey()) {
			System.out.printf("%d",b);
		} 
	}

}
