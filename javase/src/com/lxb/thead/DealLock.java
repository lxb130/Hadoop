package com.lxb.thead;

public class DealLock {

	public static Object a1 = new Object();
	public static Object b1 = new Object();

	public static class MyThead extends Thread {
		private int id;

		public MyThead(int id) {
			this.id = id;
		}

		public void run() {
			if (id == 1) {
				synchronized (a1) {
					System.out.println("我是线程" + id + "已经拿到了资源a1，正在等在资源b1");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (b1) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("我是线程" + id + "已经拿到了资源b1，正在等在资源a1");
					}
				}
			} else if (id == 2) {
				synchronized (b1) {
					System.out.println("我是线程" + id + "已经拿到了资源b1，正在等在资源a1");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (a1) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("我是线程" + id + "已经拿到了资源a1，正在等在资源b1");
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		MyThead t1 = new MyThead(1);
		t1.start();
		MyThead t2 = new MyThead(2);
		t2.start();
		System.out.println("done");
	}

}
