package com.lxb;

public class CalculatorUtil {
	public static String brand = "ÃÏ¡¡";

	private String name;

	public static String getBrand() {
		return brand;
	}

	public static void setBrand(String brand) {
		CalculatorUtil.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int add(int a, int b) {
		return a + b;
	}

	public int minus(int a, int b) {
		return a - b;
	}
}
