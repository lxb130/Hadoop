package com.tianliangedu.job001.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.tianliangedu.job001.iface.download.DownLoadInterface;

public class WebpageDownloadUtil4HttpClient implements DownLoadInterface{
	public static String parseEntity(HttpEntity entity, String defaultCharset)
			throws UnsupportedOperationException, IOException {
		String findCharset = null;
		String htmlSource = null;
		// 不管哪个编码，这个字节数组均要形成，故直接先转成字节数组
		byte[] contentByteArray = IOUtil.convertInputStreamToByteArray(entity
				.getContent());

		// 通过entity获取http header中的charset值
		findCharset = EntityUtils.getContentCharSet(entity);
		if (findCharset == null) {
			htmlSource = new String(contentByteArray, defaultCharset);
			StringReader sr = new StringReader(htmlSource);
			BufferedReader br = new BufferedReader(sr);
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim().toLowerCase();
				if (line.contains("<meta")) {
					findCharset = RegexUtil.getMatchText(line,
							WebCharsetDetectorUtil.meta_charset_regex, 1);
					if (findCharset != null) {
						break;
					}
				} else if (line.contains("</head>")) {
					// 如果到了</head>还没有找到， 则无需再往下进行匹配查找
					break;
				}
			}
			br.close();
		}
		// 无论如何得有个编码
		findCharset = (findCharset == null ? defaultCharset : findCharset);
		// 只有如下情况需要转化
		if (htmlSource == null || findCharset != defaultCharset) {
			htmlSource = new String(contentByteArray, findCharset);
		}

		return htmlSource;
	}

	public static String downloadStatic(String url) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String htmlSource = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				HttpEntity entity1 = response1.getEntity();
				htmlSource = parseEntity(entity1, StaticValue.defaultEncoding);
				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
		return htmlSource;
	}

	@Override
	public String download(String url) {
		try {
			return downloadStatic(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
			// String url = "http://www.sohu.com/";
			String url = "http://news.youth.cn/gn/";
			// String url = "http://www.baidu.com/";
			// String url = "http://www.qq.com/";
			// String url="http://news.qq.com/";
			// String url = "https://www.163.com/";
			
			String htmlSource=downloadStatic(url);
			System.out.println(htmlSource);
	}

	
}
