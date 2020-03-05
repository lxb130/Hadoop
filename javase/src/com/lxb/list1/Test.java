package com.lxb.list1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxb.list1.Student;

public class Test {
	public static void main(String[] args) {
		List<Student> testList = new ArrayList<Student>();
		testList.add(new Student("张一"));
		testList.add(new Student("张二"));
		testList.add(new Student("张三"));
		testList.add(new Student("老王"));
		testList.add(new Student("张四"));
		testList.add(new Student("张五"));
		testList.add(new Student("张六"));
		testList.add(new Student("张七"));
		testList.add(new Student("老王"));
		testList.add(new Student("张八"));
		testList.add(new Student("张九"));
		testList.add(new Student("老王"));

		List<Student> repeatList = new ArrayList<Student>();// 用于存放重复的元素的list

		// 以一种方法：两个循环(最蠢的方法)
		for (int i = 0; i < testList.size() - 1; i++) {
			for (int j = testList.size() - 1; j > i; j--) {
				if (testList.get(j).getStuName()
						.equals(testList.get(i).getStuName())) {
					repeatList.add(testList.get(j));// 把相同元素加入list(找出相同的)
					testList.remove(j);// 删除重复元素
				}
			}
		}
		System.out.println(testList.size());
		for (Student s : repeatList) {
			System.out.println("相同的元素:" + s.getStuName());
		}

		/*
		 * //第二种方法：利用map.containsKey() Map<String, Integer> map = new
		 * HashMap<>(); for(Student s : testList){ //1:map.containsKey()
		 * 检测key是否重复 if(map.containsKey(s.getStuName())){
		 * repeatList.add(s);//获取重复的学生名称
		 * 
		 * Integer num = map.get(s.getStuName()); map.put(s.getStuName(),
		 * num+1); }else{ map.put(s.getStuName(), 1); } //2:
		 * 这个key是不是存在对应的value(key是否在map中) // Integer count =
		 * map.get(s.getStuName());//这种写法也可以，异曲同工 // if (count == null) { //
		 * map.put(s.getStuName(), 1); // } else { // map.put(s.getStuName(),
		 * (count + 1)); // } } // for(Student s : repeatList){ //
		 * System.out.println("相同的元素:" + s.getStuName()); // } //
		 * for(Map.Entry<String, Integer> entry : map.entrySet()){ //
		 * System.out.println("学生:" + entry.getKey() + "的名字出现了：" +
		 * entry.getValue() + "次"); // }
		 * 
		 * 
		 * //第三种方法：contains()方法 这个个人认为有一定的局限性，个人理解哈 List<Integer> repeatList1 =
		 * new ArrayList<>(); List<Integer> list = new ArrayList<>();
		 * list.add(1); list.add(2); list.add(1); list.add(3); list.add(4);
		 * list.add(1); list.add(5); for(int i=0;i<list.size();i++){
		 * if(!repeatList1.contains(list.get(i))){ repeatList1.add(list.get(i));
		 * } } for(Integer s : repeatList1){ System.out.println(s); }
		 */
	}
}
