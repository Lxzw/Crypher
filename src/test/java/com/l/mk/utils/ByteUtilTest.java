package com.l.mk.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ByteUtilTest {

	@Test
	public void testMerge() {
		byte[] head = {'a','b'};
		byte[] end ={1,2};
		for (int i=0; i<head.length; i++) {
			System.out.print(head[i]);
		}
		for (int i=0; i<end.length; i++) {
			System.out.print(end[i]);
		}
		byte[] t = ByteUtil.merge(head, end);
		
		for (int i=0; i<t.length; i++) {
			System.out.print(t[i]);
		}
	}

	@Test
	public void testExtend() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompress() {
		fail("Not yet implemented");
	}

}
