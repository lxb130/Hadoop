package com.lxb;

public class Fei {

	public static int fei(int n) {
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fei(n - 1) + fei(n - 2);
		}
	}

	public static int JieCheng(int n) {
		if (n == 1) {
			return 1;
		}
		return n * JieCheng(n - 1);
	}

	public static int DoubleJieCheng(int n) {
		int tmp = n % 2;
		if (tmp == 0) {
			if (n == 2) {
				return 2;
			}
			return n * DoubleJieCheng(n - 2);
		} else {
			if (n == 1) {
				return 2;
			}
		}
		return n * DoubleJieCheng(n - 2);
	}

	public static void main(String[] args) {
		int result = DoubleJieCheng(6);
		System.out.println(result);
	}
}
