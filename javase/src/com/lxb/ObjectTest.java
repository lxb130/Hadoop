package com.lxb;

public class ObjectTest {
	public static void main(String[] args) {
		Object o = new Object();
		
//		Object o2 = o.notify();
//		Object o2 = o.notifyAll();
//		Object o2 = o.wait();
		
		Object o1 = new Object();

		System.out.println("o.getClass=" + o.getClass());
		System.out.println("o.hashCode=" + o.hashCode());
		System.out.println("o=" + o);

		System.out.println(o == o1);
		System.out.println(o.equals(o1));
	}
}
