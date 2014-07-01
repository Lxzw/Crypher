package com.l.mk.crypher.format;

import static org.junit.Assert.*;

import org.junit.Test;

public class PaddingTest {

	@Test
	public void test() {
		String string = "13223";
		String string2 = Padding.inLeft(string, 10, "0");
		System.out.println(string2 + ":" + string2.length());
		assertTrue(string2.length()==10);
		String string3 = Padding.inRight(string, 10, "0");
		System.out.println(string3 + ":" + string3.length());
		assertTrue(string3.length()==10);
	}

}
