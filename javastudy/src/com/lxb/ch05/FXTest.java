package com.lxb.ch05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FXTest {

	public static void main(String[] args) {
		//未使用泛型|
		List list1=new ArrayList();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.add(4);
		Iterator itr=list1.iterator();
		while(itr.hasNext()){
//			System.out.println(itr.next());
			System.out.println(itr.next().getClass().getName());//自动转换
		}
	}

}
