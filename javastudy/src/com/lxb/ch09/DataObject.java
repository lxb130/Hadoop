package com.lxb.ch09;

import java.lang.reflect.Field;

public class DataObject {
	private String name;
	private String desc;
	private int age;
	private String other;

	public DataObject(String name, String desc, int age, String other) {
		this.name = name;
		this.desc = desc;
		this.age = age;
		this.other = other;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			sb.append(field.getName());
			sb.append("=");
			try {
				sb.append(field.get(this));
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
