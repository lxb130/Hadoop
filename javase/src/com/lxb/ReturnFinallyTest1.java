package com.lxb;

public class ReturnFinallyTest1 {

	public static StringBuilder stringBuilder = new StringBuilder();

	public static StringBuilder ret() {
		try {
			stringBuilder.append("a");
			return stringBuilder.append("b");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stringBuilder.append("c");
		}
		return stringBuilder;
	}

	public static void main(String[] args) {
		System.out.println("ret·µ»ØµÄÖµ=" + ret());
	}

}
