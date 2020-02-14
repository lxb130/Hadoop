package com.lxb.thead;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProducerAndConsumer {
	static class CostomerProducer implements Runnable {

		private DealResourcePool dealResourcePool;
		private String CostomerProducerName;

		public DealPojo produce(String content) {
			int id = dealResourcePool.getDealUniqID();
			DealPojo dealPojo = new DealPojo(id, content);
			return dealPojo;
		}

		public CostomerProducer(DealResourcePool dealResourcePool,
				String CostomerProducerName) {
			this.dealResourcePool = dealResourcePool;
			this.CostomerProducerName = CostomerProducerName;
		}

		@Override
		public void run() {
			String content = "个人银行业务";
			for (int i = 0; i < 4; i++) {
				DealPojo dealPojo = produce(content);
				dealResourcePool.add(dealPojo);
				System.out.println("生产者" + CostomerProducerName + "已完成订单+"
						+ dealPojo.toString());
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("生产者"+CostomerProducerName+"已完成任务，即将退出线程");
		}

	}

	public static class DealPojo {
		private int id;
		private String content;

		public DealPojo(int id, String content) {
			this.id = id;
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "DealPojo [id=" + id + ", content=" + content + "]";
		}

	}

	static class DealResourcePool {
		public static int serialID = 0;
		public static Object dealLock = new Object();
		public static Object poolOptionLock = new Object();
		public static LinkedList<DealPojo> dealPojoList = new LinkedList<>();

		public int getDealUniqID() {
			synchronized (dealLock) {
				serialID++;
				return serialID;
			}
		}

		public boolean add(DealPojo dealPojo) {
			boolean addFlag = false;
			synchronized (poolOptionLock) {
				addFlag = dealPojoList.add(dealPojo);
			}
			return addFlag;
		}

		public DealPojo take() {
			DealPojo dealPojo = null;
			synchronized (poolOptionLock) {
				dealPojo = dealPojoList.poll();
			}
			return dealPojo;
		}
	}

	static class BankEmployeeConsumer implements Runnable {
		private String ConsumerName;
		private DealResourcePool dealResourcePool;

		public BankEmployeeConsumer(DealResourcePool dealResourcePool,
				String consumerName) {

			this.ConsumerName = consumerName;
			this.dealResourcePool = dealResourcePool;
		}

		public void consumer() {
			DealPojo dealPojo = this.dealResourcePool.take();
			if (dealPojo == null) {
				System.out.println("暂无订单请等待");
			} else {
				System.out.println(this.ConsumerName + "取走了"
						+ dealPojo.toString());
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			for (int i = 0; i < 12; i++) {
				consumer();
			}
			System.out.println("消费者"+ConsumerName+"已完成任务，即将退出线程");
		}

	}

	public static void main(String[] args) {
		int produterNumber = 3;
		int consumerNumber = 1;
		DealResourcePool dealResourcePool = new DealResourcePool();
		for (int i = 0; i < produterNumber; i++) {
			CostomerProducer producer = new CostomerProducer(dealResourcePool,
					"生产者" + i);
			new Thread(producer).start();
		}

		for (int i = 0; i < consumerNumber; i++) {
			BankEmployeeConsumer consumer = new BankEmployeeConsumer(
					dealResourcePool, "消费者" + i);
			new Thread(consumer).start();
		}
		System.out.println("done");
	}
}
