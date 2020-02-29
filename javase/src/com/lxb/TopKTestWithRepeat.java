package com.lxb;

import java.util.Arrays;

public class TopKTestWithRepeat {
	/**
	 * ����ʣ���1995�����飬ȡÿ���������ֵkmax��������int[1000]��Сֵ�Ƚ� ��kmax>min,�򽻻�
	 * ��kmax<min,�򲻸�������
	 * 
	 * @param matrix
	 * @param objectSort
	 * @param low
	 * @param matrixHigh
	 */
	public static void sortTopK(int[][] matrix, int[] objectSort, int low,
			int matrixHigh) {

		for (int i = 5; i <= matrix.length - 1; i++) {
			// ��һ��whileѭ����ֻҪĳ�������ͷ��λ�ô���֮ǰ��top1000����Сֵ������и�������ǰ�ƶ�1��λ�ã��������бȽ�
			while (matrix[i][matrixHigh] > objectSort[low]) {
				objectSort[low] = matrix[i][matrixHigh];
				selectionSort(objectSort);
				// �����ֵ���е�λ�ý��м�1�������൱����ǰ�ƶ�һ��λ��
				matrixHigh = matrixHigh - 1;
			}
		}
	}

	/**
	 * �����ȡ��ά����
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
	 * ��ӡ��ά����
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
	 * ǰ5�����鴫��һ���¶����int[1000]
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
	 * �������ѡ������
	 * 
	 * @param objectRes
	 * @return
	 */
	public static int[] selectionSort(int[] objectRes) {
		for (int a = 0; a <= objectRes.length - 1; a++) {
			int temp = a;
			// �ҳ���Сֵ���±�
			for (int b = a + 1; b <= objectRes.length - 1; b++) {
				if (objectRes[temp] < objectRes[b]) {
					temp = b;
				}
			}

			// �����ֵ�ŵ�δ�����¼�ĵ�һ��λ��
			if (temp != a) {
				int t = objectRes[a];
				objectRes[a] = objectRes[temp];
				objectRes[temp] = t;
			}

		}
		return objectRes;

	}

	public static void main(String[] args) {
		// (1)��ȡ��ά����int[2000][200]
		int[][] matrix = generateRandomMatrix(2000, 200, 1000);
		// (2)��ӡ��ȡ�Ķ�ά����,�˴�����ӡ
		// printMatrix(matrix);

		System.out.println("===========================");

		// (3)��ǰ5�����鴫���¶����int[1000]��
		int[] objectRes = prepare(matrix);

		// (4)���������������ѡ������
		int[] objectSort = selectionSort(objectRes);

		// for(int obj:objectSort){
		// System.out.println(obj);
		// }
		// (5)����ʣ���1995�����飬���бȽ�
		sortTopK(matrix, objectSort, objectSort[999], 199);
		
		//��ӡtopK=1000
		for (int cell : objectSort) {
			System.out.println(cell);
		}
	}
}
