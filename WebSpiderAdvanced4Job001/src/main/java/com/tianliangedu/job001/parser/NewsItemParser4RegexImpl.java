package com.tianliangedu.job001.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tianliangedu.job001.iface.parser.NewsItemParserInterface;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.UrlTaskPojo.TaskTypeEnum;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.utils.RegexUtil;
import com.tianliangedu.job001.utils.StaticValue;

public class NewsItemParser4RegexImpl implements NewsItemParserInterface {

	@Override
	public List<UrlTaskPojo> parserHtmlSource4RootUrl(String htmlSource)
			throws ParseException {
		// 存储最终的新闻条目结果集
		List<UrlTaskPojo> urlTaskPojoList = new ArrayList<UrlTaskPojo>();
		// 第1步、拿到整个数据块的大块数据,一般会略大于完整数据块
		String blockRegex = "<ul class=\"tj3_1\">([\\s\\S]*?)</ul>";
		String matchBlockContent = RegexUtil.getMatchText(htmlSource,
				blockRegex, 0);

		// 第2步、开始逐条拿匹配内容块
		String itemRegex = "<li>[\\s\\S]*?</li>";
		Pattern pattern = Pattern.compile(itemRegex);
		Matcher matcher = pattern.matcher(matchBlockContent);

		UrlTaskPojo urlTaskPojo = null;
		while (matcher.find()) {
			String liHtmlContent = matcher.group(0);
			// 第1步、先获取准确标题
			String titleRegex = "<a[\\s\\S]*?>([\\s\\S]*?)</a>";
			String title = RegexUtil.getMatchText(liHtmlContent, titleRegex, 1);

			// 第2步、获取链接
			String hrefRegex = "<a href=\"([\\s\\S]*?)\">";
			String href = RegexUtil.getMatchText(liHtmlContent, hrefRegex, 1);
			// 对href进行加头和去掉链接中的相对路径.处理
			if (href.startsWith("../")) {
				href = StaticValue.indexUrl + href.substring(3);
			} else {
				href = StaticValue.rootUrl + href.substring(2);
			}

			// 第3步、获取发布时间
			String postTimeRegex = "<font>([\\s\\S]*?)</font>";
			String postTime = RegexUtil.getMatchText(liHtmlContent,
					postTimeRegex, 1);

			urlTaskPojo = new UrlTaskPojo(title, href, TaskTypeEnum.CRAWL_TASK);
			urlTaskPojoList.add(urlTaskPojo);
		}

		return urlTaskPojoList;
	}

	@Override
	public NewsItemEntity parserHtmlSource4CrawkTaskUrl(String htmlSource)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
