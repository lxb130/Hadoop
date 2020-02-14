package com.lxb.test;

import java.util.StringTokenizer;

public class StringTokenizerTest {

	public static void main(String[] args) {
		String s = "1 2	3\n4\f";
		StringTokenizer itr = new StringTokenizer(s);
		while(itr.hasMoreTokens()){
			System.out.println(itr.nextToken());
		}
	}
}
