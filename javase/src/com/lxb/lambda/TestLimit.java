package com.lxb.lambda;

import java.util.Arrays;
import java.util.List;

public class TestLimit {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("111", "222", "3333");
		list.stream().limit(2).forEach(System.out::println);;
	}

}
