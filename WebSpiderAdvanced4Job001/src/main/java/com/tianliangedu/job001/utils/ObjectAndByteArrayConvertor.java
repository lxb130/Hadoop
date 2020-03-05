package com.tianliangedu.job001.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.tianliangedu.job001.pojos.UrlTaskPojo;

/**
 * java对象与字节数组转换工具类
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class ObjectAndByteArrayConvertor {
	public static byte[] convertObjectToByteArray(Object obj)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.flush();

		byte[] objByteArray = baos.toByteArray();
		return objByteArray;
	}

	public static Object convertByteArrayToObject(byte[] byteArray)
			throws ClassNotFoundException, IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object obj = ois.readObject();
		return obj;
	}
	
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		UrlTaskPojo urlTaskPojo = new UrlTaskPojo();
		urlTaskPojo.setTitle("我是用来做测试的");
		urlTaskPojo.setUrl("http://www.myhope365.com/");
		
		byte[] objByteArray = convertObjectToByteArray(urlTaskPojo);
		
		UrlTaskPojo recoveryObj = (UrlTaskPojo) convertByteArrayToObject(objByteArray);

		System.out.println(recoveryObj);

	}
}
