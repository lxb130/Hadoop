package com.lxb.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StudenDis {

	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();
		Student s1 = new Student(1, "a", "1班");
		Student s2 = new Student(2, "b", "2班");
		Student s3 = new Student(1, "c", "3班");
		Student s4 = new Student(2, "f", "4班");
		Student s5 = new Student(5, "a", "5班");
		stuList.add(s1);
		stuList.add(s2);
		stuList.add(s3);
		stuList.add(s4);
		stuList.add(s5);

		System.out.println("去重前--------------------------------");
		for (Student student : stuList) {
			System.out.println(student.toString());
		}
		System.out.println("去重后--------------------------------");
		List<Student> newStuList = removeDuplicateOutputField(stuList);
		for (Student student : newStuList) {
			System.out.println(student.toString());
		}
	}

	public static List<Student> removeDuplicateOutputField(List<Student> list) {
		Set<Student> set = new TreeSet<Student>(new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getId().compareTo(s2.getId());
			}
		});
		set.addAll(list);
		return new ArrayList<>(set);
	}
}
