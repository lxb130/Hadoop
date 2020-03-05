package com.lxb.ch08;

public class Store {
	private final int MAX_SIZE;
	private int count;

	public Store(int mAX_SIZE) {
		this.MAX_SIZE = mAX_SIZE;
		this.count = 0;
	}

	public synchronized void add() {
		if (count >= this.MAX_SIZE) {
			System.out.println("²Ö¿âÒÑÂúÇëµÈ´ý");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().toString() + "\tput\t"
				+ count);
		this.notifyAll();
	}

	public synchronized void remove() {
		if (count == 0) {
			System.out.println("²Ö¿âÒÑ¿ÕÇëµÈ´ý");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count--;
		System.out.println(Thread.currentThread().toString() + "\tget\t"
				+ count);
		this.notifyAll();
	}

}


