package com.lxb.ch05;

import java.util.Comparator;

public class CompartorTest {

	/*
	 * 比较器的使用不仅限于元素的比较，往往还用于集合元素的排序
	 * */
	public static class User {
		private String id;
		private int age;

		public User(String id, int age) {
			this.id = id;
			this.age = age;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", age=" + age + "]";
		}

	}

	public static class UserCompartor implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			User u1 = (User) o1;
			User u2 = (User) o2;
			return u1.getAge() - u2.getAge();
		}
	}

	public static void main(String[] args) {
		User user1 = new User("1", 1);
		User user2 = new User("2", 2);
		Comparator<User> comp = new UserCompartor();
		int rst=comp.compare(user1, user2);
		System.out.println(rst);
	}
}
