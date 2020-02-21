package com.lxb.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileClone {

	public static void FileClone(String src, String dest) throws IOException {
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(dest);

		int byteLength = -1;

		byte[] byteArray = new byte[4096];
		while ((byteLength = fis.read(byteArray)) > 0) {
			fos.write(byteArray, 0, byteLength);
		}

		fos.flush();
		fos.close();
		fis.close();
	}

	public static void main(String[] args) throws IOException {
		String src = "E:/bigdata3/Hadoop/javase/test/src.txt";
		String dest = "E:/bigdata3/Hadoop/javase/test/dest.txt";
		FileClone(src, dest);

	}
}
