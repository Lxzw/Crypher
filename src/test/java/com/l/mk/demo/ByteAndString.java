package com.l.mk.demo;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ByteAndString {

	@Test
	public void test() throws UnsupportedEncodingException {
		String s = "你好";
		System.out.println(s.getBytes().length);
		System.out.println(s.getBytes("UTF-8").length);
		System.out.println(s.getBytes("ISO8859-1").length);
	}

}
