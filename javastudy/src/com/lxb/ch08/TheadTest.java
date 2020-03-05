package com.lxb.ch08;

public class TheadTest {
	
	public static void main(String[] args) {
		Store s = new Store(10);
		Producer p1=new Producer(s);
		Producer p2=new Producer(s);
		Consumer c1=new Consumer(s);
		Consumer c2=new Consumer(s);
		p1.setName("Producer1");
		p2.setName("Produce2");
		c1.setName("Consumer1");
		c2.setName("Consumer2");
		p1.start();
		p2.start();
		c1.start();
		c2.start();
	}
}
