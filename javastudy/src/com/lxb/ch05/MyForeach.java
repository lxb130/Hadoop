package com.lxb.ch05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyForeach {

	public static class MyList implements Iterable<String>, Iterator<String> {

		private int loc = 0;// 当前下标
		List<String> list = new ArrayList<>();

		public void remove() {
			list.remove(loc);
		}

		public void setList(List<String> list) {
			this.list = list;
		}

		private List<String> getList() {
			return list;
		}

		@Override
		public Iterator<String> iterator() {

			return this;
		}

		@Override
		public boolean hasNext() {

			return list.size() > loc;
		}

		@Override
		public String next() {
			return list.get(loc++);
		}

	}

	public static void main(String[] args) {
		MyList myList = new MyList();
		myList.getList().add("1");
		myList.getList().add("2");
		myList.getList().add("3");
		myList.getList().add("4");
		
		for (String string : myList) {
			System.out.println(string);
		}
	}

}
