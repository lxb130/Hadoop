package com.lxb;

public class ReturnFinallyTest {
	public static int i= 1;

	public static int ret() {
		try {
			i = 2;
			return (++i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			i = 10;
			System.out.println("finally i=" + i);
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println("ret���ص�ֵ=" + ret());
		System.out.println("����iֵ=" + i);
	}

}
