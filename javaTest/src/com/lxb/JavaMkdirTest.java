package com.lxb;

import java.io.File;

public class JavaMkdirTest {

	public static void main(String[] args) {
		String path = "E:\\eclipse\\workspace\\javaTest\\bin\\com\\lxb";
		File file = new File(path);
		file.mkdir();
	}

}
