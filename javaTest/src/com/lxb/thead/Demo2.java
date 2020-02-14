package com.lxb.thead;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Demo2 {

	private int count = 0;

	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

	public static void main(String[] args) {

		Demo2 demo2 = new Demo2();
		for (int i = 1; i <= 5; i++) {
			new Thread(demo2.new Producer(), "������-" + i).start();
			new Thread(demo2.new Consumer(), "������-" + i).start();
		}

	}

	class Producer implements Runnable {

		@Override
		public void run() {

			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					queue.put(1);
					count++;
					System.out.println("������ "
							+ Thread.currentThread().getName() + " �ܹ��� "
							+ count + " ����Դ");
				} catch (InterruptedException e) {
					e.printStackTrace();
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
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					queue.take();
					count--;
					System.out.println("������ "
							+ Thread.currentThread().getName() + " �ܹ��� "
							+ count + " ����Դ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
