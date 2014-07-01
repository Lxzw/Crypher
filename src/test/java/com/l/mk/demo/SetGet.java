package com.l.mk.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class SetGet {

	@Test
	public void test() {
		byte[] b = new byte[10];
		byte[] c = {1,2,3};
		System.out.println(b.length);
		System.out.println(c.length);
		b=c;
		System.out.println(b.length);
	}

}
