package com.tianliangedu.job001.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianliangedu.job001.iface.download.DownLoadInterface;

/**
 * jsoup操作工具类封装
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class JsoupUtil {
	public static enum ContentSelectType {
		OUTHER_HTML, HTML, TEXT
	}

	public static Document getDoc(String htmlSource) {
		return Jsoup.parse(htmlSource);
	}

	public static Elements getElementsBySelector(String htmlSource,
			String selector) {
		Document docment = getDoc(htmlSource);
		return docment.select(selector);
	}

	public static List<String> getElementsBySelector(String htmlSource,
			String selector, ContentSelectType contentType) {
		Elements elements = getElementsBySelector(htmlSource, selector);
		List<String> eleList = new ArrayList<String>();

		switch (contentType) {
		case OUTHER_HTML:
			for (Element ele : elements) {
				eleList.add(ele.outerHtml());
			}
			break;
		case HTML:
			for (Element ele : elements) {
				eleList.add(ele.html());
			}
			break;
		case TEXT:
			for (Element ele : elements) {
				eleList.add(ele.text());
			}
			break;
		default:
			break;
		}

		return eleList;
	}

	public static List<String> getElementsBySelector(String htmlSource,
			String selector, String selectAttribute) {
		if (StringUtil.isBlank(selectAttribute)) {
			return null;
		}
		Elements elements = getElementsBySelector(htmlSource, selector);
		List<String> eleList = new ArrayList<String>();

		for (Element ele : elements) {
			eleList.add(ele.attr(selectAttribute));
		}

		return eleList;
	}

	public static String getChildElementValue(Element element, int childIndex,
			ContentSelectType contentType) {
		String value = null;
		switch (contentType) {
		case OUTHER_HTML:
			value = element.child(childIndex).outerHtml();
			break;
		case HTML:
			value = element.child(childIndex).html();
			break;
		case TEXT:
			value = element.child(childIndex).text();
			break;
		}
		return value;
	}

	public static String getAttributeValue(Element element, String attributeKey) {
		return element.attr(attributeKey);
	}

	public static void main(String[] args) {
		// 将数据准备好
		String url = "http://news.youth.cn/gn/";
		DownLoadInterface download = new WebpageDownloadUtil4HttpClient();
		String htmlSource = download.download(url);

		// 形成htmlSource对应的dom对象
		Document doc = Jsoup.parse(htmlSource);

		// 解析该dom对象
		// Elements elements=doc.select("title");
		Elements elements = doc.select("ul.tj3_1>li");
		System.out.println(elements.size());
		for (Element ele : elements) {
			String title = getChildElementValue(ele, 1, ContentSelectType.TEXT);
			String postTime = getChildElementValue(ele, 0,
					ContentSelectType.TEXT);
			String href = getAttributeValue(ele.child(1),"href");
			
			System.out.println(title);
			System.out.println(postTime);
			System.out.println(href);
		}
	}
}
