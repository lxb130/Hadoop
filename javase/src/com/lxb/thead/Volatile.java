package com.lxb.thead;

public class Volatile {
	
	private static Integer num = 0;

	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[10];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						increase();
					}
				}

			});
			threads[i].start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		System.out.println(num);
	}

	public synchronized  static void increase() {
		num++;
	}
}
