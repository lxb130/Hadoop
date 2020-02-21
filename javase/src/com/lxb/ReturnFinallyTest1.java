package com.lxb;

public class ReturnFinallyTest1 {

	public static void main(String[] args) {
		System.out.println(test1());
	}

	public static int test1() {
		int i = 111;
		try {
			int[] a = new int[2];
			a[3] = 0;
			i++;
			System.out.println("try i=" + i);
			return p1();
		} catch (IndexOutOfBoundsException e) {
			i++;
			System.out.println("catch i=" + i);
			return p2();
		} finally {
			i++;
			System.out.println("finally i=" + i);
			return p3();
		}
	}

	public static int p1() {
		System.out.println("p1");
		return 1;
	}

	public static int p2() {
		System.out.println("p2");
		return 2;
	}

	public static int p3() {
		System.out.println("p3");
		return 3;
	}

}
