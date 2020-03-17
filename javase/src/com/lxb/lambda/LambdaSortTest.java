package com.lxb.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaSortTest {

	public static void main(String[] args) {
//		List<String> list = Arrays.asList("666", "333", "555");
//		list.stream().sorted().forEach(System.out::println);
	
		Student s1 = new Student(1L, "肖战", 15, "浙江");
		Student s2 = new Student(2L, "王一博", 15, "湖北");
		Student s3 = new Student(3L, "杨紫", 17, "北京");
		Student s4 = new Student(4L, "李现", 17, "浙江");
		
		List<Student> students = new ArrayList<>();
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		
		students.stream();
	}

}
