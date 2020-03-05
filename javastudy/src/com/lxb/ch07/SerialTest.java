package com.lxb.ch07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialTest {

	public static class Student implements Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private int age;

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		Student s = new Student();
		s.setAge(21);
		s.setName("lxb");

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"E:\\bigdata3\\test\\obj.txt"));

		oos.writeObject(s);
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"E:\\bigdata3\\test\\obj.txt"));

		Object obj = ois.readObject();
		Student stubak=(Student) obj;
		System.out.println(stubak.toString());
	}

}
