package com.lxb;

import java.util.HashSet;
import java.util.Set;

public class MemoryLeakTest1 {

	static class Person {
		private String name;
		private String password;
		private int age;

		public Person(String name, String password, int age) {
			super();
			this.name = name;
			this.password = password;
			this.age = age;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((password == null) ? 0 : password.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (age != other.age)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			return true;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

	public static void main(String[] args) {
		Set<Person> set = new HashSet<>();
		Person p1 = new Person("用户1","密码1",21);
		Person p2 = new Person("用户2","密码2",22);
		Person p3 = new Person("用户3","密码4",23);
		set.add(p1);
		set.add(p2);
		set.add(p3);
		System.out.println("set的元素个数为:"+set.size());
		p3.setAge(24);
		set.remove(p3);
		set.add(p3);
		System.out.println("set的元素个数为:"+set.size());
		for (Person person : set) {
			System.out.println(person);
		}
	}

}
