package com.lxb.sort;

import java.util.Arrays;

public class SelectSort {

	public static void SelectSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j <= arr.length - 1; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			int tmp = arr[min];
			arr[min] = arr[i];
			arr[i] = tmp;
		}
	}

	public static void SelectSortV2(int[] arr) {
		int tmp;
		for (int i = 0; i < arr.length - 1; i++) {
			int max = i;
			for (int j = i + 1; j <= arr.length - 1; j++) {
				if (arr[max] < arr[j]) {
					max = j;
				}
			}
			tmp = arr[max];
			arr[max] = arr[i];
			arr[i] = tmp;
		}
	}

	public static void main(String[] args) {
		int[] a = { 9, 7, 3, 5, 4, 6, 2, 8 };
		System.out.println(a[7]);
		SelectSort(a);
		System.out.println(Arrays.toString(a));

		System.out.println(a[7]);
	}

}
