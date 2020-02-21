package com.lxb.sort;

import java.util.Arrays;

public class BubbleSort {

	public static void BubbleSort(int[] list) {
		int tmp;
		for (int i = 0; i < list.length - 1; i++) {
			for (int j = 0; j < list.length - 1 - i; j++) {
				if (list[j] > list[j + 1]) {
					tmp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = tmp;
				}
			}
		}

	}

	public static void main(String[] args) {
		int[] a = { 1, 7, 3, 3, 5, 4, 6, 2, 8 };
		BubbleSort(a);
		System.out.println(Arrays.toString(a));
	}

}
