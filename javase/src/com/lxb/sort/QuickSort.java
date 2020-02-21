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
		// Լ��ִ������
		while (low < high) {
			// ���������ߣ��ҵ�С�ڻ�׼ֵ��ͣ��
			while (low < high && intArray[high] >= base) {
				high--;
			}
			// ���������ߣ��ҵ����ڻ�׼ֵ��ͣ��
			while (low < high && intArray[low] <= base) {
				low++;
			}

			// ����low��highֵ
			if (low < high) {
				int temp = intArray[low];
				intArray[low] = intArray[high];
				intArray[high] = temp;
			}
		}

		// ����low�ͻ�׼ֵ
		if (low_temp != low) {
			intArray[low_temp] = intArray[low];
			intArray[low] = base;
		}

		// ���Ҹ��Եݹ�ѭ��
		quickSort(intArray, low_temp, low - 1);
		quickSort(intArray, low + 1, high_temp);
	}

	public static void main(String[] args) {
		int[] a = { 5, 7, 3, 1, 4, 6, 2, 8 };
		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}

}
