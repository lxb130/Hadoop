package com.lxb.ch07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReaderTest {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("E:\\bigdata3\\test\\test.txt");
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();

		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		System.out.println("test.txt 文件内容为"+sb);
		br.close();
	}

}
