package com.lxb.ch09;

import java.lang.reflect.Field;

public class FieldTest {

	public static void main(String[] args) {
		FieldTestClass obj1 = new FieldTestClass("a", 25);
		FieldTestClass obj2 = new FieldTestClass("b", 30);
		System.out.println(compare(obj1, obj2).name+"  age is biger");
	}

	public static FieldTestClass compare(FieldTestClass obj1,
			FieldTestClass obj2) {

		try {
			Field field = obj1.getClass().getDeclaredField("age");
			field = FieldTestClass.class.getDeclaredField("age");

			int val1 = (Integer) field.get(obj1);
			int val2 = (Integer) field.get(obj2);
			if (val1 > val2) {
				return obj1;
			} else {
				return obj2;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

		
	}
}
