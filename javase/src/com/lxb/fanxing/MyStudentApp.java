package com.lxb.fanxing;

public class MyStudentApp {

	public static void main(String[] args) {
		MyStudent<Integer> myStudent = new MyStudent<Integer>(80);
		System.out.println(myStudent.getScore());
	}

}
