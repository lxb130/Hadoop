package com.lxb.thead;

public class K {
	// ״̬��
	private Object lock;
	// ��������
	private int now, need;

	public void produce(int num) {
		// ͬ��
		synchronized (lock) {
			// ��ǰ�еĲ�������Ҫ�����еȴ���ֱ����������
			while (now < need) {
				try {
					// �ȴ�����
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("�ұ������ˣ�");
			}
			// ������������
		}
	}
}