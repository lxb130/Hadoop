package com.lxb.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaDistinct {
	public static void main(String[] args) {
//		List<String> list = Arrays.asList("111", "111", "222", "3333");
//		list.stream().distinct().forEach(l -> System.out.println(l));

		Student s1 = new Student(1L, "Фս", 15, "�㽭");
		Student s2 = new Student(2L, "��һ��", 15, "����");
		Student s3 = new Student(3L, "����", 17, "����");
		Student s4 = new Student(4L, "����", 17, "�㽭");
		Student s5 = new Student(4L, "����", 17, "�㽭");
		Student s6 = new Student(1L, "Фս", 15, "�㽭");
		List<Student> students = new ArrayList<>();
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		students.add(s5);
		students.add(s6);
		students.stream().distinct().forEach(System.out::println);
	}
}
