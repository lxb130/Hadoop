package com.lxb.ch15;

public class Prime {

	// ��ӡ100���ڵ�����
	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			if(isPrime(i)){
				System.out.println(i);
			}
		}
	}

	public static boolean isPrime(int num) {
		if (num == 1) {
			return false;
		}
		long sqrtNum = (long) Math.sqrt(num);

		for (int i = 2; i <= sqrtNum; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
