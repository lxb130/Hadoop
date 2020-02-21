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
		System.out.println("����ض����ļ���");
		ps.close();
		
		System.setOut(psOrigin);
		System.out.println("�ָ�ԭ�������");
		System.out.println("done");
	}
}
