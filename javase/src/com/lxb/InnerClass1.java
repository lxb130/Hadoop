package com.lxb;

public class InnerClass1 {

	public static void main(String[] args) {
//		Chouxiang chouxiang = new Chouxiang() {
//			String name = "Geek Song too";
//
//			public void say() {
//				System.err.println("���������ڲ����еķ�������д�˳�����ķ���");
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
		System.err.println("���ǳ����൱�еķ��������������������о��巽����ʵ�ֵģ��ӿڲ���");
	}
}

class Chouxiang2 extends Chouxiang {
	public void say() {
		System.err.println(name);
	}
}