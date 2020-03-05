package com.tianliangedu.job001.parser;

import java.text.ParseException;
import java.util.List;

import com.tianliangedu.job001.iface.download.DownLoadInterface;
import com.tianliangedu.job001.iface.parser.NewsItemParserInterface;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.utils.WebpageDownloadUtil4HttpClient;

public class HtmlParserManager {
	// 多态使用，将解析的接口指向具体实现接口的实例-此处为正则解析
	public static NewsItemParserInterface parserInterface = new NewsItemParser4JsoupImpl();

	public static NewsItemEntity parserHtmlSource4CrawkTaskUrl(String htmlSource)
			throws ParseException {
		return parserInterface.parserHtmlSource4CrawkTaskUrl(htmlSource);
	}

	public static List<UrlTaskPojo> parserHtmlSource4RootUrl(String htmlSource)
			throws ParseException {
		return parserInterface.parserHtmlSource4RootUrl(htmlSource);
	}

	public static void main(String[] args) throws ParseException {
		// 将数据准备好
		String url = "http://news.youth.cn/gn/";
		DownLoadInterface download = new WebpageDownloadUtil4HttpClient();
		String htmlSource = download.download(url);

//		List<NewsItemEntity> itemList = parserHtmlSource(htmlSource);
//
//		for (NewsItemEntity entity : itemList) {
//			System.out.println(entity);
//		}
	}
}
