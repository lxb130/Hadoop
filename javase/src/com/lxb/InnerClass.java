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
		System.out.println("�����ⲿ�൱�еķ���");
	}

	class B {
		int neibu = 12;

		public void sayit() {
			System.out.println("�����ڲ��൱�еķ���");
			System.out.println("ʹ���ⲿ����ڲ�����������" + (waibu + neibu));
		}
	}
}