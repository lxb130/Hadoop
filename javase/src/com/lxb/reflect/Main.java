package com.lxb.reflect;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		// ����һ���ַ����õ�һ����
//		String name="LiXiaobin";
//		System.out.println(name.getClass());
//		System.out.println(name.getClass().getName());
		
		String className="com.lxb.reflect.People";
		Class<People> c1=(Class<People>) Class.forName(className);
		System.out.println(c1.toString());
	}

}
