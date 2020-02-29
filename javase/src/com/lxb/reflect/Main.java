package com.lxb.reflect;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		// 根据一个字符串得到一个累
//		String name="LiXiaobin";
//		System.out.println(name.getClass());
//		System.out.println(name.getClass().getName());
		
		String className="com.lxb.reflect.People";
		Class<People> c1=(Class<People>) Class.forName(className);
		System.out.println(c1.toString());
	}

}
