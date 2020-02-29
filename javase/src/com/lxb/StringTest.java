package com.lxb;

public class StringTest {

	public static void main(String[] args) {
		
		// String s1 = new String();
		// Double d1=new Double(1.02);
		// String s2=String.valueOf(d1);
		// System.out.println(s2);
//		String s1 = "a,b,c";
//		String regex = ",";
//		String[] splitResult = s1.split(regex);
//		for (String string : splitResult) {
//			System.out.println(string);
//		}
		Object o = new Object();
		System.out.println(o.getClass());
		System.out.println(o.getClass().getName());
		System.out.println(o.hashCode());
		System.out.println(o.toString());
		String s2="15db9742";
		System.out.println(Integer.parseInt(s2, 10));
	}

}
