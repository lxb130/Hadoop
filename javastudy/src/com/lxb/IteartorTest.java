package com.lxb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteartorTest {

	/*
	 * 迭代器，提供一中访问java集合中各个元素的一种途径，同时又不需要暴露该集合对象的内部细节
	 * 
	 * java通过提供iterator 和 iteratable两个接口来实现集合类的可迭代性
	 * 用hasnext作为循环条件，用next方法得到每一个元素
	 * */
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		Iterator<String> itr=list.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}

}
