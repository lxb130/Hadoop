package com.test.zel;

public class TestThreadsV2 {
	public static class Main {
		public static Object obj1 = new Object();

		public static void synOne(String message) throws InterruptedException {
			System.out.println("我在synOne里!");
			Thread.sleep(2000);
			synchronized (obj1) {
				synThree(message);
			}
		}

		public static void synTwo(String message) throws InterruptedException {
			System.out.println("我在synTwo里!");
			Thread.sleep(2000);
			synchronized (obj1) {
				synThree(message);
			}
		}

		public static void synThree(String message) throws InterruptedException {
			synchronized (obj1) {
				Thread.sleep(2000);
				System.out.println(message);
			}
		}
	}

	public static class PrintRunnable implements Runnable {
		public int flag;

		public PrintRunnable(int flag) {
			this.flag = flag;
		}

		@Override
		public void run() {
			while (true) {
				if (flag % 2 != 0) {
					try {
						Main.synOne("我是从one方法中出发的!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						Main.synTwo("我是从two方法中出发的!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 4; i++) {
			new Thread(new PrintRunnable(i)).start();
		}
	}
}
