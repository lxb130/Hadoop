package com.lxb.ch05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionSort {

	public static class Student implements Comparable<Student> {

		private String name;
		private int age;

		public Student(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setAge(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}

		@Override
		public int compareTo(Student o) {

			return this.age - o.getAge();
		}

	}

	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();
		Student s1 = new Student("a", 20);
		Student s2 = new Student("b", 25);
		Student s3 = new Student("c", 26);
		Student s4 = new Student("d", 27);
		stuList.add(s1);
		stuList.add(s2);
		stuList.add(s3);
		stuList.add(s4);
		System.out.println("-------------------Ô­Ë³Ğò--------------------");
		for (Student student : stuList) {
			System.out.println(student.toString());
		}

		Collections.sort(stuList);
		System.out.println("-------------------ÉıĞò--------------------");
		for (Student student : stuList) {
			System.out.println(student.toString());
		}

		Comparator comp = Collections.reverseOrder();
		Collections.sort(stuList, comp);

		System.out.println("-------------------½µĞò--------------------");
		for (Student student : stuList) {
			System.out.println(student.toString());
		}

		System.out.println("-------------------°´ÕÕÃû×ÖÖØĞÂÅÅĞò--------------------");
		Collections.sort(stuList, new Comparator<Student>() {

			@Override
			public int compare(Student s1, Student s2) {

				return s2.getName().compareTo(s1.getName());
			}

		});

		for (Student student : stuList) {
			System.out.println(student.toString());
		}
	}

}
