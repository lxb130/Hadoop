package com.lxb.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class IOStreamReact {

	public static void main(String[] args) throws FileNotFoundException {
		String destFile = "IOStreamReact.txt";
		PrintStream ps = new PrintStream(new File(destFile));

		PrintStream psOrigin = System.out;

		System.setOut(ps);
		System.out.println("输出重定向到文件中");
		ps.close();
		
		System.setOut(psOrigin);
		System.out.println("恢复原来的输出");
		System.out.println("done");
	}
}
