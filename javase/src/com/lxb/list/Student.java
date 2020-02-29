package com.lxb.list;

public class Student {
	private Integer id;
	private String name;
	private String classname;

	public Student(Integer id, String name, String classname) {
		this.id = id;
		this.name = name;
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", classname="
				+ classname + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
