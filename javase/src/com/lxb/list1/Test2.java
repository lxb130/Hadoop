package com.lxb.list1;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		List<Student> testList = new ArrayList<>();
		testList.add(new Student("��һ"));
		testList.add(new Student("�Ŷ�"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		List<Student> repeatList = new ArrayList<>();
		// System.out.println(testList.size());
		// System.out.println(testList.get(100));
		for (int i = 0; i < testList.size() - 1; i++) {
			if (i == 2) {
				testList.remove(2);
				// System.out.println(testList.size());
			}
			System.out.println(testList.get(i).getStuName() + "\t"
					+ testList.size());

		}
		// System.out.println(testList.size());
		for (int i = 0; i < testList.size() - 1; i++) {
			for (int j = testList.size() - 1; j > i; j--) {
				if (testList.get(j).getStuName()
						.equals(testList.get(i).getStuName())) {
					repeatList.add(testList.get(j));
					testList.remove(j);
				}
			}
		}
		// System.out.println(testList.size());
		// for (Student student : repeatList) {
		// System.out.println(student.getStuName());
		// }
	}
}
