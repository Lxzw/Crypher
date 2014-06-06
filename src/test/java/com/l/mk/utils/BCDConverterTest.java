package com.l.mk.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class BCDConverterTest {

	@Test
	public void testToBCD() {
		String s = "99";
		//System.out.println(s.getBytes()[0]=='9');
		
		byte[] bcd = BCDConverter.toBCD(s);
		for (int i=0; i < BCDConverter.toBCD(s).length; i++) {
			System.out.println(bcd[i]);
			System.out.printf("%04x\n",bcd[i]);
			assertTrue(bcd[i] == -0x67);
		}
	}

	@Test
	public void testFromBCD() {
		byte[] s = {0x11,-0x67};
		System.out.println(BCDConverter.fromBCD(s));
		assertTrue("1199".equals(BCDConverter.fromBCD(s)));
	}

}
