package com.lxb;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Boos {

	public static class CalculatorUtil {
		public static String brand = "天亮";

		private String name;

		public CalculatorUtil(String name) {
			super();
			this.name = name;
		}

		public CalculatorUtil() {

		}

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

	public static void test1() {
		CalculatorUtil calculator = new CalculatorUtil();
		System.out.println("包名" + calculator.getClass().getPackage().getName());
		System.out.println("类名" + calculator.getClass().getName());
	}

	public static void test2() {
		Class calculator = CalculatorUtil.class;
		System.out.println("包名" + calculator.getClass().getPackage().getName());
		System.out.println("类名" + calculator.getClass().getName());
	}

	public static void test3() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Class calculator = Class.forName("com.lxb.Boos$CalculatorUtil");
		CalculatorUtil calculatorUtil = (CalculatorUtil) calculator
				.newInstance();
		System.out.println("包名"
				+ calculatorUtil.getClass().getPackage().getName());
		System.out.println("类名" + calculatorUtil.getClass().getName());
	}

	public static void test4() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Class calculator = Class.forName("com.lxb.Boos$CalculatorUtil");
		CalculatorUtil calculatorUtil = (CalculatorUtil) calculator
				.newInstance();
		System.out.println("包名"
				+ calculatorUtil.getClass().getPackage().getName());
		System.out.println("类名" + calculatorUtil.getClass().getName());
		System.out.println(calculatorUtil.name);
		System.out.println(calculatorUtil.brand);
		System.out.println(calculatorUtil.add(3, 2));
		System.out.println(calculatorUtil.minus(3, 2));
	}

	public static void test5() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		Class calculator = Class.forName("com.lxb.Boos$CalculatorUtil");

		// 得到参数为空的构造方法
		Constructor<?> ConstructorEmpty = calculator.getDeclaredConstructor();
		CalculatorUtil cal_1 = (CalculatorUtil) ConstructorEmpty.newInstance();
		cal_1.setName("蓝米");

		// 得到参数为字符串的构造方法
		Constructor<?> ConstructorString = calculator
				.getDeclaredConstructor(String.class);
		CalculatorUtil cal_2 = (CalculatorUtil) ConstructorString
				.newInstance("红米");

		System.out.println(cal_1.getName());
		System.out.println(cal_2.getName());
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		test5();
	}
}
