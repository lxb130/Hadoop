package com.lxb.ch08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

	public static int produceTaskSleepTime = 2000;// 间歇时间 ms

	public static void main(String[] args) {
		// 构建一个线程池
		ThreadPoolExecutor producePool = new ThreadPoolExecutor(1, 1, 0,
				TimeUnit.SECONDS, new ArrayBlockingQueue(3),
				new ThreadPoolExecutor.DiscardOldestPolicy());

		int i = 1;
		while (true) {
			try {
				Thread.sleep(produceTaskSleepTime);
				String task = "task@" + i;
				System.out.println("put....." + task);
				producePool.execute(new ThreadPoolTask(task));
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
