package com.lxb.list1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxb.list1.Student;

public class Test {
	public static void main(String[] args) {
		List<Student> testList = new ArrayList<Student>();
		testList.add(new Student("��һ"));
		testList.add(new Student("�Ŷ�"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("����"));
		testList.add(new Student("�Ű�"));
		testList.add(new Student("�ž�"));
		testList.add(new Student("����"));

		List<Student> repeatList = new ArrayList<Student>();// ���ڴ���ظ���Ԫ�ص�list

		// ��һ�ַ���������ѭ��(����ķ���)
		for (int i = 0; i < testList.size() - 1; i++) {
			for (int j = testList.size() - 1; j > i; j--) {
				if (testList.get(j).getStuName()
						.equals(testList.get(i).getStuName())) {
					repeatList.add(testList.get(j));// ����ͬԪ�ؼ���list(�ҳ���ͬ��)
					testList.remove(j);// ɾ���ظ�Ԫ��
				}
			}
		}
		System.out.println(testList.size());
		for (Student s : repeatList) {
			System.out.println("��ͬ��Ԫ��:" + s.getStuName());
		}

		/*
		 * //�ڶ��ַ���������map.containsKey() Map<String, Integer> map = new
		 * HashMap<>(); for(Student s : testList){ //1:map.containsKey()
		 * ���key�Ƿ��ظ� if(map.containsKey(s.getStuName())){
		 * repeatList.add(s);//��ȡ�ظ���ѧ������
		 * 
		 * Integer num = map.get(s.getStuName()); map.put(s.getStuName(),
		 * num+1); }else{ map.put(s.getStuName(), 1); } //2:
		 * ���key�ǲ��Ǵ��ڶ�Ӧ��value(key�Ƿ���map��) // Integer count =
		 * map.get(s.getStuName());//����д��Ҳ���ԣ�����ͬ�� // if (count == null) { //
		 * map.put(s.getStuName(), 1); // } else { // map.put(s.getStuName(),
		 * (count + 1)); // } } // for(Student s : repeatList){ //
		 * System.out.println("��ͬ��Ԫ��:" + s.getStuName()); // } //
		 * for(Map.Entry<String, Integer> entry : map.entrySet()){ //
		 * System.out.println("ѧ��:" + entry.getKey() + "�����ֳ����ˣ�" +
		 * entry.getValue() + "��"); // }
		 * 
		 * 
		 * //�����ַ�����contains()���� ���������Ϊ��һ���ľ����ԣ��������� List<Integer> repeatList1 =
		 * new ArrayList<>(); List<Integer> list = new ArrayList<>();
		 * list.add(1); list.add(2); list.add(1); list.add(3); list.add(4);
		 * list.add(1); list.add(5); for(int i=0;i<list.size();i++){
		 * if(!repeatList1.contains(list.get(i))){ repeatList1.add(list.get(i));
		 * } } for(Integer s : repeatList1){ System.out.println(s); }
		 */
	}
}
