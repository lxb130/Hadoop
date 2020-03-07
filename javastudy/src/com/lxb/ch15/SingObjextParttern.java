package com.lxb.ch15;

public class SingObjextParttern {
	public static void main(String[] args) {
		ConnectionPoolA cpa1=new ConnectionPoolA().getConnectionPool();
		ConnectionPoolA cpa2=new ConnectionPoolA().getConnectionPool();
		System.out.println(cpa1==cpa2);
		
		ConnectionPoolB cpb1=new ConnectionPoolB().getConnectionPool();
		ConnectionPoolB cpb2=new ConnectionPoolB().getConnectionPool();
		System.out.println(cpb1==cpb2);
	}
}

// ����ʽ
class ConnectionPoolA {
	private static ConnectionPoolA cp = new ConnectionPoolA();

	public ConnectionPoolA() {

	}

	public static ConnectionPoolA getConnectionPool() {
		return cp;
	}

}

// ����ʽ ��Ҫ��ʱ��Ŵ���
class ConnectionPoolB {
	private static ConnectionPoolB cp;

	public ConnectionPoolB() {

	}

	public synchronized static ConnectionPoolB getConnectionPool() {
		if (cp == null) {
			cp = new ConnectionPoolB();
		}
		return cp;
	}
}