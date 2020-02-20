package com.lxb.celuo;

public class CustomerObserver implements Observer {
	private String name;
	private int version;

	public CustomerObserver(String name) {
		this.name = name;
	}

	@Override
	public void update(int version) {
		this.version = version;
		System.out.println("����־������־��");
		this.buy();
	}

	private void buy() {
		System.out.println(name+"�����˵�"+version+"�ڵ���־!");		
	}
}
