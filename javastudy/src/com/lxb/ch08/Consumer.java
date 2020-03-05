package com.lxb.ch08;

public class Consumer extends Thread {
	private Store s;

	public Consumer(Store s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			s.remove();
			try {
				Thread.sleep(2000);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
