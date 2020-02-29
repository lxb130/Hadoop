package com.lxb.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByName {

	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();
		Student s1 = new Student(1, "a", "1°à");
		Student s2 = new Student(2, "b", "2°à");
		Student s3 = new Student(3, "c", "3°à");
		Student s4 = new Student(4, "f", "4°à");
		Student s5 = new Student(5, "a", "5°à");
		stuList.add(s1);
		stuList.add(s2);
		stuList.add(s3);
		stuList.add(s4);
		stuList.add(s5);

		// Collections.sort(stuList, new Comparator<Student>(){
		//
		// @Override
		// public int compare(Student s1, Student s2) {
		// return s2.getName().compareTo(s1.getName());
		// }});

		for (Student student : stuList) {
			System.out.println(student.toString());
		}
	}

}
