package com.lxb.ch09;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NewInstanceTest {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<Student> clazz = Student.class;
		Student obj = clazz.newInstance();
		System.out.println(obj);

		Constructor<Student> con = clazz
				.getConstructor(String.class, int.class);
		
		obj=con.newInstance("test1",2);
		System.out.println(obj);
	}

}
