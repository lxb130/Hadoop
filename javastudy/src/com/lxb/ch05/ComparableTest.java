package com.lxb.ch05;

public class ComparableTest {

	
	/*
	 * comparable �ӿڣ�ֻ��һ��comparrto������ֻ��һ������������ֵ����0ʱ���ʾ��������ڲ������
	 * */
	
	public static class ComparableUser implements Comparable<ComparableUser> {

		private int age;
		private String name;

		public ComparableUser(int age, String name) {
			this.age = age;
			this.name = name;
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

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "ComparableUser [age=" + age + ", name=" + name + "]";
		}

		@Override
		public int compareTo(ComparableUser o) {
			return this.age - o.getAge();
		}

	}

	public static void main(String[] args) {
		ComparableUser user1 = new ComparableUser(1, "a");
		ComparableUser user2 = new ComparableUser(2, "b");
		if (user1.compareTo(user2) > 0) {
			System.out.println("user1���������user2");
		} else if (user1.compareTo(user2) == 0) {
			System.out.println("user1���������user2");
		} else {
			System.out.println("user1������С��user2");
		}
	}

}
