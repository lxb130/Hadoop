package com.lxb.ch07;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RanAccFileTest {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(
				"E:\\bigdata3\\test\\test.txt", "rw");

		for (int i = 0; i < raf.length(); i++) {
			byte b = (byte) raf.read();
			char c = (char) b;		
			if (c == 'a') {
//				System.out.println(c);
				raf.seek(i);
				raf.write('c');
			}
		}
		raf.close();
		System.out.println("done");
	}
}
