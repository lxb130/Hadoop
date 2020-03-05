package com.tianliangedu.job001.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.tianliangedu.job001.iface.download.DownLoadInterface;

/**
 * 用于下载给定任意网址对应的html代码
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class WebpageDownloadUtil4UrlConnection implements DownLoadInterface {
	public static String downloadStatic(String url, String defaultCharset)
			throws IOException {
		// 第1步、声明最终的html源码变量
		String htmlSource = null;

		// 第2步、将url的UrlConnection拿到
		URLConnection urlConnection = IOUtil.getUrlConnection(url);
		
		// 第3步、将流转化成字节数组
		byte[] contentByteArray = IOUtil
				.convertInputStreamToByteArray(urlConnection.getInputStream());

		// 第4步、从http header中获取编码,如果拿到则为最终网页编码
		String findCharset = WebCharsetDetectorUtil
				.getCharsetByHttpHeader(urlConnection);

		// 第5步、编码逻辑判断与使用
		if (findCharset == null) {
			htmlSource = new String(contentByteArray, defaultCharset);
			findCharset = WebCharsetDetectorUtil.getCharsetByHtmlSource(htmlSource);
		}

		// 无论如何得有个编码
		findCharset = (findCharset == null ? defaultCharset : findCharset);
		// 只有如下情况需要转化
		if (htmlSource == null || findCharset != defaultCharset) {
			htmlSource = new String(contentByteArray, findCharset);
		}

		return htmlSource;
	}

	@Override
	public String download(String url) {
		try {
			return downloadStatic(url, StaticValue.defaultEncoding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		// String url = "http://news.youth.cn/gn/";
		// String url = "http://www.myhope365.com/";
		String url = "http://www.baidu.com/";
		// String htmlSource = downloadStatic(url);
		// System.out.println(htmlSource);

		DownLoadInterface download = new WebpageDownloadUtil4UrlConnection();
		System.out.println(download.download(url));

	}
}
