package com.test.zel;

public class TestThreads {
	public static class Main {
		public static synchronized void synOne(String message)
				throws InterruptedException {
			System.out.println("我在synOne里!");
			Thread.sleep(2000);
			synThree(message);
		}

		public static synchronized void synTwo(String message)
				throws InterruptedException {
			System.out.println("我在synTwo里!");
			Thread.sleep(2000);
			synThree(message);
		}

		public static synchronized void synThree(String message)
				throws InterruptedException {
			Thread.sleep(2000);
			System.out.println(message);
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
