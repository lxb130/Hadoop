package com.tianliangedu.job001.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public class WebCharsetDetectorUtil {
	public static String meta_charset_regex = "charset=[\"]?([\\s\\S]*?)[\">]";

	public static String getCharsetByHttpHeader(URLConnection urlConnection) {
		Map<String, List<String>> headerMap = urlConnection.getHeaderFields();
		Set<String> keySet = headerMap.keySet();
		String findCharset = null;
		for (String key : keySet) {
			if (key != null && key.equalsIgnoreCase("Content-Type")) {
				List<String> valueList = headerMap.get(key);
				String line = valueList.get(0);
				String[] valueArray = line.split(StaticValue.sep_semicolon);
				if (valueArray.length == 2) {
					String charsetString = valueArray[1];
					charsetString = charsetString.trim();
					String[] charsetArray = charsetString
							.split(StaticValue.sep_equals);
					if (charsetArray.length == 2) {
						findCharset = charsetArray[1];
						break;
					}
				}
			}
		}
		return findCharset;
	}

	public static String getCharsetByHtmlSource(String htmlSource)
			throws IOException {
		StringReader sr = new StringReader(htmlSource);
		BufferedReader br = new BufferedReader(sr);
		String line = null;
		String findCharset = null;
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
		return findCharset;
	}

	public static String getCharset(String url) throws IOException {
		String findCharset = null;
		URL urlObj = new URL(url);
		URLConnection urlConnection = urlObj.openConnection();
		// 直接调用方法，获取http header中的编码值
		findCharset = getCharsetByHttpHeader(urlConnection);
		// 如果findCharset在httpheader当中，没有找到，则启用meta charset方式寻找
		if (findCharset == null) {
			BufferedReader br = IOUtil.getBR(urlConnection,
					StaticValue.defaultEncoding);
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim().toLowerCase();
				if (line.contains("<meta")) {
					findCharset = RegexUtil.getMatchText(line,
							meta_charset_regex, 1);
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
		return findCharset == null ? StaticValue.defaultEncoding : findCharset;
	}

	public static String getCharset(HttpEntity entity, String defaultCharset)
			throws IOException {
		String findCharset = null;
		// 通过entity获取http header中的charset值
		findCharset = EntityUtils.getContentCharSet(entity);
		if (findCharset == null) {
			byte[] contentByteArray = IOUtil
					.convertInputStreamToByteArray(entity.getContent());
			String htmlSource = new String(contentByteArray, defaultCharset);
			StringReader sr = new StringReader(htmlSource);
			BufferedReader br = new BufferedReader(sr);
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim().toLowerCase();
				if (line.contains("<meta")) {
					findCharset = RegexUtil.getMatchText(line,
							meta_charset_regex, 1);
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
		return findCharset == null ? defaultCharset : findCharset;
	}

	public static void main(String[] args) throws IOException {
		// String url = "https://www.baidu.com/";
		// String url = "http://news.youth.cn/gn/";
		// String url = "http://news.youth.cn/";
		// String url = "http://www.qq.com/";
		String url = "http://news.sina.com.cn/";
		String findCharset = getCharset(url);
		System.out.println(findCharset);
		System.out.println("done!");
	}
}
