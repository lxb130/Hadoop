package com.lxb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteartorTest {

	/*
	 * ���������ṩһ�з���java�����и���Ԫ�ص�һ��;����ͬʱ�ֲ���Ҫ��¶�ü��϶�����ڲ�ϸ��
	 * 
	 * javaͨ���ṩiterator �� iteratable�����ӿ���ʵ�ּ�����Ŀɵ�����
	 * ��hasnext��Ϊѭ����������next�����õ�ÿһ��Ԫ��
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
