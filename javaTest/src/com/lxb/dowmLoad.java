package com.lxb;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class dowmLoad {

	public static String downLoad(String httpUrl) throws IOException {
		URL url = new URL(httpUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Connection", "keep-Alive");
		connection.connect();
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = null;

		try {
			InputStream is = connection.getInputStream();
			bis = new BufferedInputStream(is);
			bos = new ByteArrayOutputStream();
			int b = 0;
			byte[] byteArr = new byte[4096];
			while ((b = bis.read(byteArr)) != -1) {
				bos.write(byteArr, 0, b);
			}
			return new String(bos.toByteArray(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;

	}

	public static void main(String[] args) throws IOException {
		String url = "https://www.baidu.com";
		System.out.println(downLoad(url));
		new Object();
	}

}
