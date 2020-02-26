package com.lxb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TxtSort {

	public static void main(String[] args) throws IOException {
		String path = "E:\\bigdata3\\yuangong.txt";
		// 创建文件读入流
		FileInputStream in = new FileInputStream(path);
		// 将字节流转化为字符流
		InputStreamReader is = new InputStreamReader(in, "utf-8");
		// 转化为缓存模式
		BufferedReader br = new BufferedReader(is);
		String s = "";
		int count = 0;

		Set<String> set1 = new HashSet<>();
		Map<String, Integer> map = new HashMap<>();
		while ((s = br.readLine()) != null) {
			String[] s1 = s.split("\\n");
			String[] s2 = s.split("\\t");
			String name = s2[0];
			String time = s2[2];
			Integer tmp = map.get(time);
			if (tmp != null) {
				map.put(time, map.get(time) + 1);
			} else {
				map.put(time, 1);
			}
		}
		System.out.println(map.toString());

		Set set2 = map.keySet();
		// value-sort
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				map.entrySet());
		// list.sort()
		list.sort(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
	}

}
