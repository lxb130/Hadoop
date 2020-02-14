package com.lxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOCopy {
	// 结合递归、I0等内容,实现任意文件夹的复制。如给定一个文件夹路径E:/datadir, 将其拷贝到指定的另一个文件夹路径中D:/datadir.

	public static void dirCopy(String inputPath, String outputPath) {
		File file = new File(inputPath);
		File out = new File(outputPath);
		if (!out.exists()) {
			out.mkdir();
		}
		if (!file.exists()) {
			System.out.println("文件夹路径不存在");
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
						System.out.println("文件夹创建成功！");
					} else {
						System.out.println("文件夹创建失败！");
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
