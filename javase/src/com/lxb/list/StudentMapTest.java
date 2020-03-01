package com.lxb.list;

import java.util.HashMap;
import java.util.Map;

public class StudentMapTest {

	public static void main(String[] args) {
		Map<Integer, Student> map = new HashMap<>();
		Student s1 = new Student(1, "a", "1°à");
		Student s2 = new Student(2, "b", "2°à");
		Student s3 = new Student(3, "c", "3°à");
		Student s4 = new Student(4, "f", "4°à");
		Student s5 = new Student(5, "a", "5°à");
		map.put(1, s1);
		map.put(2, s2);
		map.put(3, s3);
		map.put(4, s4);
		map.put(5, s5);
		
		System.out.println(map.get(1));
	}

}
