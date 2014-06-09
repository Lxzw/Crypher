package com.l.mk.crypher.format;

import static org.junit.Assert.*;

import org.junit.Test;

public class ByteUtilTest {

	//测试compress和extend方法
	@Test
	public void test() {
		String s = "1F2F3F4F";
		byte[] b = ByteUtil.compress(s);
		String s1 = ByteUtil.extend(b);
		assertTrue(s1.equals(s));
	}

}
