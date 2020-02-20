package com.lxb.Adaptee.method;


public class Adapter extends Adaptee implements Target {

	@Override
	public void method2() {
		System.out.println("method2");
	}

}
