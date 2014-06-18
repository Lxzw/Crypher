package com.l.mk.crypher.format;

import static org.junit.Assert.*;

import org.junit.Test;

public class BCDConverterTest {

	private String string = "20131101";
	
	@Test
	public void testToBCD() {
		byte[] bytes = BCDConverter.toBCD(string);
		for (byte b : bytes) {
			System.out.printf("%d", b);
		}
	}

	@Test
	public void testFromBCD() {
		byte[] bytes = BCDConverter.toBCD(string);
		System.out.println(BCDConverter.fromBCD(bytes));
	}

}
