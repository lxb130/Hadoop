package com.lxb.ch07;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterTest {

	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(
				"E:/bigdata3/test/test2.txt"));
		pw.write("a");
		pw.write("b");
		pw.write("c");
		pw.write("d");
		pw.close();
		System.out.println("done");
	}

}
