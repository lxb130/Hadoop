package com.lxb;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakTest {
	static List<Object> objectList = new ArrayList<Object>(10);

	public static int getSize() {
		for (int i = 0; i < 9999999; i++) {
			Object o = new Object();
			objectList.add(o);
			o = null;
		}
		return objectList.size();
	}

	public static void main(String[] args) {
		System.out.println(getSize());
	}
}
