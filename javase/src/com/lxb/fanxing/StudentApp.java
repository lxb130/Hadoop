package com.lxb.fanxing;

public class StudentApp {

	public static void main(String[] args) {
		Student s=new Student("80");
		Integer score=(Integer) s.getScore();
		System.out.println(score);
	}

}
