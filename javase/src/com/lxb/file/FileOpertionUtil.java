package com.lxb.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileOpertionUtil {

	public static List<String> getAllFileList4Standard(String localDirPath) {
		File fileObject = new File(localDirPath);
		if (!fileObject.exists()) {
			return null;
		}
		List<String> resultList = new ArrayList<>();
		if (fileObject.isDirectory()) {
			File[] subFileArray = fileObject.listFiles();
			for (File oneFile : subFileArray) {
//				System.out.println(oneFile.toString());
				resultList.addAll(getAllFileList4Standard(oneFile.toString()));

			}
			resultList.add(fileObject.toString());
		} else {
			resultList.add(fileObject.toString());
		}
		return resultList;

	}

	public static void main(String[] args) {
		// String localDirPath="E:/������/99_����Ա����/01��������������/��������_ȫջ�������������";
		String localDirPath = "E:/bigdata";
		List<String> resultList = getAllFileList4Standard(localDirPath);
		System.out.println(resultList.toString());
		 for (String result : resultList) {
		 System.out.println(result+"		");
		 }
	}

}
