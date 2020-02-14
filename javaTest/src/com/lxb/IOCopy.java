package com.lxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOCopy {
	// ��ϵݹ顢I0������,ʵ�������ļ��еĸ��ơ������һ���ļ���·��E:/datadir, ���俽����ָ������һ���ļ���·����D:/datadir.

	public static void dirCopy(String inputPath, String outputPath) {
		File file = new File(inputPath);
		File out = new File(outputPath);
		if (!out.exists()) {
			out.mkdir();
		}
		if (!file.exists()) {
			System.out.println("�ļ���·��������");
		}
		if (file.isDirectory()) {
			File[] subFileList = file.listFiles();
			for (File file2 : subFileList) {
				// System.out.println(file2);
				if (file2.isDirectory()) {
					System.out.println("file=" + file2);
					String dirtarget=out
							   
							+ "\\"
							+ file2.getAbsolutePath().substring(
									outputPath.length());
					File file3=new File(dirtarget);
					if (file3.mkdirs() == true) {
						System.out.println("�ļ��д����ɹ���");
					} else {
						System.out.println("�ļ��д���ʧ�ܣ�");
					}				
					dirCopy(file2.toString(), outputPath);
				} else {
					// System.out.println(file2.toString());
					String target = out
							+ "\\"
							+ file2.getAbsolutePath().substring(
									outputPath.length());
					
					System.out.println("target=" + target);
					copy(file2.toString(), target);
				}

			}
		}
		// System.out.println(file.toString());
	}

	public static boolean copy(String src, String dest) {
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dest);

			byte[] byteArray = new byte[4096];
			int byteLength = -1;
			while ((byteLength = fis.read(byteArray)) > 0) {
				fos.write(byteArray, 0, byteLength);
			}

			fos.flush();
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		String inputPath = "E:\\eclipse\\workspace\\javaTest";

		String outputPath = "E:\\eclipse\\workspace\\javaTest1";
		dirCopy(inputPath, outputPath);
		System.out.println("done");
	}
}
