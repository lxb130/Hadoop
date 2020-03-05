package com.lxb.ch09;

import java.lang.reflect.Field;

public class PrivateTest {

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		PrivateTestClass ptc = new PrivateTestClass("test");
		Class<PrivateTestClass> clazz = PrivateTestClass.class;
		Field f = clazz.getDeclaredField("field");
		f.setAccessible(true);
		System.err.println(f.get(ptc));
	}

}
