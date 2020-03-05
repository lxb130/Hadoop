package com.lxb.ch08;

public class Producer extends Thread {
	private Store s;

	public Producer(Store s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			s.add();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
