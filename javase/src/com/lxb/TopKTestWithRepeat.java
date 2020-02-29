package com.lxb;

import java.util.Arrays;

public class TopKTestWithRepeat {
	/**
	 * 遍历剩余的1995个数组，取每个数组最大值kmax与新数组int[1000]最小值比较 若kmax>min,则交换
	 * 若kmax<min,则不更改数组
	 * 
	 * @param matrix
	 * @param objectSort
	 * @param low
	 * @param matrixHigh
	 */
	public static void sortTopK(int[][] matrix, int[] objectSort, int low,
			int matrixHigh) {

		for (int i = 5; i <= matrix.length - 1; i++) {
			// 用一个while循环，只要某个数组的头部位置大于之前的top1000的最小值，则进行该数组向前移动1个位置，继续进行比较
			while (matrix[i][matrixHigh] > objectSort[low]) {
				objectSort[low] = matrix[i][matrixHigh];
				selectionSort(objectSort);
				// 将最高值进行的位置进行减1操作，相当于向前移动一个位置
				matrixHigh = matrixHigh - 1;
			}
		}
	}

	/**
	 * 随机获取二维数组
	 * 
	 * @param row
	 * @param col
	 * @param maxValue
	 * @return
	 */
	public static int[][] generateRandomMatrix(int row, int col, int maxValue) {
		if (row < 0 || col < 0) {
			return null;
		}
		int[][] matrix = new int[row][col];
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[i].length; j++) {
				matrix[i][j] = (int) (Math.random() * maxValue);
			}
			Arrays.sort(matrix[i]);
		}

		return matrix;

	}

	/**
	 * 打印二维数组
	 * 
	 * @param matrix
	 */
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

	}

	/**
	 * 前5个数组传入一个新定义的int[1000]
	 * 
	 * @param matrix
	 * @return
	 */
	public static int[] prepare(int[][] matrix) {
		int[] objectArr = new int[1000];
		int tmp = 0;
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= matrix[i].length - 1; j++) {
				objectArr[tmp++] = matrix[i][j];
			}
		}

		return objectArr;
	}

	/**
	 * 新数组的选择排序
	 * 
	 * @param objectRes
	 * @return
	 */
	public static int[] selectionSort(int[] objectRes) {
		for (int a = 0; a <= objectRes.length - 1; a++) {
			int temp = a;
			// 找出最小值的下标
			for (int b = a + 1; b <= objectRes.length - 1; b++) {
				if (objectRes[temp] < objectRes[b]) {
					temp = b;
				}
			}

			// 将最大值放到未排序记录的第一个位置
			if (temp != a) {
				int t = objectRes[a];
				objectRes[a] = objectRes[temp];
				objectRes[temp] = t;
			}

		}
		return objectRes;

	}

	public static void main(String[] args) {
		// (1)获取二维数组int[2000][200]
		int[][] matrix = generateRandomMatrix(2000, 200, 1000);
		// (2)打印获取的二维数组,此处不打印
		// printMatrix(matrix);

		System.out.println("===========================");

		// (3)将前5个数组传入新定义的int[1000]中
		int[] objectRes = prepare(matrix);

		// (4)将新数组的数进行选择排序
		int[] objectSort = selectionSort(objectRes);

		// for(int obj:objectSort){
		// System.out.println(obj);
		// }
		// (5)遍历剩余的1995个数组，进行比较
		sortTopK(matrix, objectSort, objectSort[999], 199);
		
		//打印topK=1000
		for (int cell : objectSort) {
			System.out.println(cell);
		}
	}
}
