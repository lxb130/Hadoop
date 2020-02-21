package com.lxb;

public class ReturnFinallyTest {

	public static String test() {
		try {
			return new String("Ö´ÐÐreturn");
		} finally {
			System.out.println("Ö´ÐÐfinally");
			return new String("Ö´ÐÐ");
		}

	}

	public static int test1() {
		int i = 1;
		try {
			return i=5;
		} finally {
			return i=6;
		}
	}

	public static void main(String[] args) {
		int i=test1();	
		System.out.println(i);
	}

}
