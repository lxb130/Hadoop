package com.lxb.ch15;

public class Prime {

	// 打印100以内的素数
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
