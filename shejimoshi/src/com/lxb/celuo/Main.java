package com.lxb.celuo;

public class Main {

	public static void main(String[] args) {
		MagazineSubject magazine = new MagazineSubject();
		// 创建三个不同的观察者
		CustomerObserver a = new CustomerObserver("A");
		CustomerObserver b = new CustomerObserver("B");
		CustomerObserver c = new CustomerObserver("C");
		// 将观察者注册到主题中
		magazine.addObserver(a);
		magazine.addObserver(b);
		magazine.addObserver(c);
		magazine.publish();
	}

}
