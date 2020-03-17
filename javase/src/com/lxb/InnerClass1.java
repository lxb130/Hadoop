package com.lxb;

public class InnerClass1 {

	public static void main(String[] args) {
//		Chouxiang chouxiang = new Chouxiang() {
//			String name = "Geek Song too";
//
//			public void say() {
//				System.err.println("这是匿名内部类中的方法，重写了抽象类的方法");
//				System.out.println(name);
//			}
//		};
//		chouxiang.say();
		Chouxiang2 chouxiang2=new Chouxiang2();
		chouxiang2.say();
	}

}

abstract class Chouxiang {
	String name = "Geek Song";

	public void say() {
		System.err.println("这是抽象类当中的方法，抽象类中是允许有具体方法来实现的，接口不行");
	}
}

class Chouxiang2 extends Chouxiang {
	public void say() {
		System.err.println(name);
	}
}