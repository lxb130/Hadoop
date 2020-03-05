package com.lxb.ch08;

import java.io.IOException;
import java.io.Serializable;
import java.nio.CharBuffer;

public class ThreadPoolTask implements Serializable, Runnable {

	private static final long serialVersionUID = 0;

	private static int consumTaskSleepTime = 2000;

	private String threadPoolData;

	public ThreadPoolTask(String task) {
		this.threadPoolData = task;
	}

	@Override
	public void run() {
		System.out.println("start...." + threadPoolData);
		try {
			Thread.sleep(consumTaskSleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadPoolData = null;
	}

}
