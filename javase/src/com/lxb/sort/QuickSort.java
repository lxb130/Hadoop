package com.lxb.sort;

import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int[] intArray, int low, int high) {
		if (low >= high) {
			return;
		}

		int low_temp = low;
		int high_temp = high;
		int base = intArray[low_temp];
		// 约定执行条件
		while (low < high) {
			// 从右往左走，找到小于基准值的停下
			while (low < high && intArray[high] >= base) {
				high--;
			}
			// 从左往右走，找到大于基准值的停下
			while (low < high && intArray[low] <= base) {
				low++;
			}

			// 交换low和high值
			if (low < high) {
				int temp = intArray[low];
				intArray[low] = intArray[high];
				intArray[high] = temp;
			}
		}

		// 交换low和基准值
		if (low_temp != low) {
			intArray[low_temp] = intArray[low];
			intArray[low] = base;
		}

		// 左右各自递归循环
		quickSort(intArray, low_temp, low - 1);
		quickSort(intArray, low + 1, high_temp);
	}

	public static void main(String[] args) {
		int[] a = { 5, 7, 3, 1, 4, 6, 2, 8 };
		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}

}
