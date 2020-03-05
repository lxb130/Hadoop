package com.lxb.ch07;

import java.io.File;
import java.io.IOException;

public class FileDirTest {

	public static void main(String[] args) {
//		File FilePath=new File("E:\\bigdata3\\test\\test.txt");
//		if(!FilePath.exists()){
//			try {
//				FilePath.createNewFile();
//				System.out.println("文件创建成功");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		
		File dirPath=new File("E:\\bigdata3\\test");
		if(dirPath.isDirectory()){
			String[] subPath=dirPath.list();
			for (String string : subPath) {
				System.out.println(string);
			}
		}
	}

}
