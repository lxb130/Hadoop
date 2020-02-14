package com.lxb.thead;

public class CopyOfFruitPlateDemo1 {

	private final static String LOCK = "lock";

	private int count = 0;

	private static final int FULL = 10;

	public static void main(String[] args) {

		CopyOfFruitPlateDemo1 fruitDemo1 = new CopyOfFruitPlateDemo1();

		for (int i = 1; i <= 5; i++) {
			new Thread(fruitDemo1.new Producer(), "������-" + i).start();
			new Thread(fruitDemo1.new Consumer(), "������-" + i).start();
		}

	}

	class Producer implements Runnable {

		@Override
		public void run() {

			for (int i = 0; i < 10; i++) {

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (LOCK) {

					if (count == FULL) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					count++;
					System.out.println("������ "
							+ Thread.currentThread().getName() + " �ܹ��� "
							+ count + " ����Դ");
					LOCK.notifyAll();

				}

			}

		}

	}

	class Consumer implements Runnable {

		@Override
		public void run() {

			for (int i = 0; i < 10; i++) {

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (LOCK) {

					if (count == 0) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					count--;
					System.out.println("������ "
							+ Thread.currentThread().getName() + " �ܹ��� "
							+ count + " ����Դ");
					LOCK.notifyAll();

				}

			}

		}

	}

}
