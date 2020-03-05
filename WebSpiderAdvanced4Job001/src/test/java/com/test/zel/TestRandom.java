package com.test.zel;

public class TestRandom {
	public static void main(String[] args) {
		String[] arr = { "one", "two", "three", "four", "five" };

		StringBuilder stringBuilder = new StringBuilder();

		int eleCounter = 0;
		for (String ele : arr) {
			if (eleCounter > 0) {
				stringBuilder.append("#");
			}
			stringBuilder.append(ele);
			eleCounter++;
		}

		System.out.println(stringBuilder.toString());
	}
}
