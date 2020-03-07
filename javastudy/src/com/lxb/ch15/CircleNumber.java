package com.lxb.ch15;

public class CircleNumber {

	/*
	 * �ж�һ�����ǲ��ǻ���
	 * 
	 * ˼·��
	 */

	public static void main(String[] args) {
		for (int i = 10; i < 10000; i++) {
			if (isCircleNumber(i)) {
				System.out.println(i);
			}
		}
	}

	public static boolean isCircleNumber(int num) {
		int oldNum = num;
		int tmp = 0;
		while (num > 0) {
			tmp = tmp * 10 + num % 10;
			num /= 10;
		}
		return tmp == oldNum;
	}
}
