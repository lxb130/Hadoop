package com.lxb.ch09;

import java.lang.reflect.Method;

public class CallMethonTest {
	
	public static void main(String[] args) {
		
		String methodName = "m1";
		
		Class<MethodTestClass> clazz = MethodTestClass.class;
		if (clazz != null) {
			try {
				Method m = clazz.getDeclaredMethod(methodName);
				if (m != null) {
					MethodTestClass obj = clazz.newInstance();
					m.invoke(obj);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}
