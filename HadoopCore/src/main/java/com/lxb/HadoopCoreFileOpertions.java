package com.lxb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.client.HdfsDataInputStream;
import org.apache.log4j.Logger;

import com.google.common.io.ByteArrayDataOutput;

public class HadoopCoreFileOpertions {

	static Configuration hadoopConf = new Configuration();

	Logger logger = Logger.getLogger(HadoopCoreFileOpertions.class);

	public static void main(String[] args) throws Exception {
		String filePath = "/tmp/tianliangedu/input.txt";
		String result = readFile(filePath);
		System.out.println(result);
	}

	private static String readFile(String filePath) throws Exception {
		// 若文件不存在则抛出异常
		if (filePath.trim().length() == 0 || filePath == null) {
			throw new Exception("所要读取的文件" + filePath + "不存在请检查");
		}
		// 将文件内容转成字节数组
		byte[] byteArray = readFileToByteArray(filePath);
		if (byteArray == null || byteArray.length == 0) {
			return null;
		}
		return new String(byteArray, "utf-8");
	}

	private static byte[] readFileToByteArray(String filePath) throws Exception {
		// 若文件不存在则抛出异常
		if (filePath.trim().length() == 0 || filePath == null) {
			throw new Exception("所要读取的文件" + filePath + "不存在请检查");
		}
		FileSystem fs = FileSystem.get(hadoopConf);
		Path hdfsPath = new Path(filePath);
		FSDataInputStream hdfsInStream = fs.open(hdfsPath);

		byte[] byteArray = new byte[65536];

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int readLen = 0;
		while ((readLen = hdfsInStream.read(byteArray)) > 0) {
			bos.write(byteArray);
			byteArray = new byte[65536];
		}
		byte[] resultByteArray = bos.toByteArray();
		bos.close();
		return resultByteArray;
	}
}
