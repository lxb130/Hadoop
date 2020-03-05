package com.lxb.ch05;

public class ComparableTest {

	
	/*
	 * comparable 接口，只有一个comparrto方法，只有一个参数，返回值大于0时则表示本对象大于参与对象
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
			System.out.println("user1的年龄大于user2");
		} else if (user1.compareTo(user2) == 0) {
			System.out.println("user1的年龄等于user2");
		} else {
			System.out.println("user1的年龄小于user2");
		}
	}

}
