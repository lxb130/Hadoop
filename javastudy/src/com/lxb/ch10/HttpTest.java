package com.lxb.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpTest {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://www.baidu.com");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Connection", "keep-Alive");
		conn.connect();
		Map<String, List<String>> header = conn.getHeaderFields();
		for (String key : header.keySet()) {
			System.out.println(key + "=" + header.get(key));
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		String str = null;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}
		conn.disconnect();
	}
}
