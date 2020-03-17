package com.lxb;

public class InnerClass {

	public static void main(String[] args) {
		A a = new A();
		a.say();
		A.B ab=new A().new B();
		ab.sayit();
	}

}

class A {
	int waibu = 13;

	public void say() {
		System.out.println("这是外部类当中的方法");
	}

	class B {
		int neibu = 12;

		public void sayit() {
			System.out.println("这是内部类当中的方法");
			System.out.println("使用外部类和内部类的数据相加" + (waibu + neibu));
		}
	}
}