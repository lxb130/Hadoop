package com.tianliangedu.job001.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.tianliangedu.job001.iface.download.DownLoadInterface;
import com.tianliangedu.job001.iface.parser.NewsItemParserInterface;
import com.tianliangedu.job001.persistence.DataPersistManager;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.UrlTaskPojo.TaskTypeEnum;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.utils.JsoupUtil;
import com.tianliangedu.job001.utils.JsoupUtil.ContentSelectType;
import com.tianliangedu.job001.utils.RegexUtil;
import com.tianliangedu.job001.utils.StaticValue;
import com.tianliangedu.job001.utils.WebpageDownloadUtil4HttpClient;

public class NewsItemParser4JsoupImpl implements NewsItemParserInterface {
	public static Logger logger = Logger
			.getLogger(NewsItemParser4JsoupImpl.class);

	@Override
	public List<UrlTaskPojo> parserHtmlSource4RootUrl(String htmlSource) {
		// 存储最终的新闻条目结果集
		List<UrlTaskPojo> crawlTaskList = new ArrayList<UrlTaskPojo>();

		// 要取得所有li集合元素
		String liSelector = "ul.tj3_1>li";
		Elements liElements = JsoupUtil.getElementsBySelector(htmlSource,
				liSelector);

		// 遍历li element，通过child和attribute获取3个字段的值
		UrlTaskPojo urlTaskPojo = null;
		String title = null;
		String postTime = null;
		String href = null;
		for (Element ele : liElements) {
			title = JsoupUtil.getChildElementValue(ele, 1,
					ContentSelectType.TEXT);
			postTime = JsoupUtil.getChildElementValue(ele, 0,
					ContentSelectType.TEXT);
			href = JsoupUtil.getAttributeValue(ele.child(1), "href");

			// 对href进行加头和去掉链接中的相对路径.处理
			if (href.startsWith("../")) {
				href = StaticValue.indexUrl + href.substring(3);
			} else {
				href = StaticValue.rootUrl + href.substring(2);
			}

			urlTaskPojo = new UrlTaskPojo(title, href, postTime,
					TaskTypeEnum.CRAWL_TASK);
			crawlTaskList.add(urlTaskPojo);
		}

		return crawlTaskList;
	}

	@Override
	public NewsItemEntity parserHtmlSource4CrawkTaskUrl(String htmlSource)
			throws ParseException {
		// 首先拿到doc对象
		Document doc = JsoupUtil.getDoc(htmlSource);

		// 取得title的文本值
		String titleSelector = "p.pbt";
		Elements elements = doc.select(titleSelector);

		// 发布时间由于不一致，采用外部的发布时间为准，不做正文页发布时间的抽取!
		// 抽取来源
		String sourceNameRegex = "[\\s]+来源：[\\s\\S]*?>([\\s\\S]*?)<";
		String sourceNameRegexBak = "[\\s]+来源：[\\s\\S]*?[>]*([\\s\\S]*?)<";
		String sourceName = RegexUtil.getMatchText(htmlSource, sourceNameRegex,
				1).trim();
		if (StringUtil.isBlank(sourceName)) {
			sourceName = RegexUtil.getMatchText(htmlSource, sourceNameRegexBak,
					1).trim();
		}
		// 提取正文
		String bodySelector = "div.page_text";
		elements = doc.select(bodySelector);
		String body = null;
		if (elements.size() > 0) {
			body = elements.get(0).text();
		} else {
			bodySelector = "#container";
			elements = doc.select(bodySelector);
			if (elements.size() > 0) {
				body = elements.get(0).text();
			} else {
				bodySelector = "div.container";
				elements = doc.select(bodySelector);
				body = elements.get(0).text();
			}
		}
		body = body.trim();

		NewsItemEntity itemEntity = new NewsItemEntity();
		itemEntity.setSourceName(sourceName);
		itemEntity.setBody(body);

		return itemEntity;
	}

	public static void main(String[] args) throws ParseException {
		// 将数据准备好
		// String url = "http://news.youth.cn/gn/";
		// String url = "http://news.youth.cn/gn/201808/t20180801_11686506.htm";
		// String url = "http://news.youth.cn/gn/201808/t20180801_11686408.htm";
		// String url = "http://news.youth.cn/gn/201808/t20180801_11686375.htm";

		// String url="http://news.youth.cn/gn/201807/t20180731_11684119.htm";
		// String url = "http://news.youth.cn/gn/201807/t20180731_11684027.htm";

		String url = "http://news.youth.cn/gn/201807/t20180723_11677060.htm";
		DownLoadInterface download = new WebpageDownloadUtil4HttpClient();
		String htmlSource = download.download(url);

		NewsItemParserInterface parserInterface = new NewsItemParser4JsoupImpl();
		NewsItemEntity itemEntity = parserInterface
				.parserHtmlSource4CrawkTaskUrl(htmlSource);
		itemEntity.setTitle("title");
		itemEntity.setSourceURL("my_url");
		itemEntity.setPostTimeString("2018-08-01 12:12:12");

		// DataPersistManager.persist(itemEntity);
		System.out.println(htmlSource);
		System.out.println(itemEntity.getSourceName());
		System.out.println("done");
	}

}
