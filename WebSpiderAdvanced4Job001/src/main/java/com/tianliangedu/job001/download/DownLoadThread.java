package com.tianliangedu.job001.download;

public class DownLoadThread extends Thread {

	@Override
	public void run() {
		int i=0;
		for(i=0;i<10;i++){
			System.out.println("我正在运行i="+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("线程run方法即将结束!");
	}

	public static void main(String[] args) {
		DownLoadThread downLoadThread=new DownLoadThread();
		downLoadThread.start();
	}
}
