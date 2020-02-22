package com.lxb.binaraySearch;

public class BinaraySearch {
	public static void main(String[] args) {
		String[] strArr = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	}

	public static boolean BinaraySearch(String[] strArr, String target) {
		if (Integer.parseInt(target) > strArr.length) {
			System.out.println("该字符串不在数组中");
			return false;
		}

		int moddle = (strArr.length - 1) / 2;
		if (strArr[moddle].equals(target)) {
			return true;
		}
		return true;
	}
}
