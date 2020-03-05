package com.lxb.ch07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(
				"E:\\bigdata3\\test\\test.txt");
		FileOutputStream fos = new FileOutputStream(
				"E:\\bigdata3\\test\\test1.txt");

		byte[] byteArray = new byte[4096];
		int len = 0;
		while ((len = fis.read(byteArray)) > 0) {
			fos.write(byteArray, 0, len);
		}
		fos.close();
		fis.close();
		System.out.println("done");
	}

}
