package com.lxb.celuo;

public class Main {

	public static void main(String[] args) {
		MagazineSubject magazine = new MagazineSubject();
		// ����������ͬ�Ĺ۲���
		CustomerObserver a = new CustomerObserver("A");
		CustomerObserver b = new CustomerObserver("B");
		CustomerObserver c = new CustomerObserver("C");
		// ���۲���ע�ᵽ������
		magazine.addObserver(a);
		magazine.addObserver(b);
		magazine.addObserver(c);
		magazine.publish();
	}

}
