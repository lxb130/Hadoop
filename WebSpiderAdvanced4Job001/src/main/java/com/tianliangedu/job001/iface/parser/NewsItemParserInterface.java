package com.tianliangedu.job001.iface.parser;

import java.text.ParseException;
import java.util.List;

import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;

public interface NewsItemParserInterface {
	public List<UrlTaskPojo> parserHtmlSource4RootUrl(String htmlSource)
			throws ParseException;

	public NewsItemEntity parserHtmlSource4CrawkTaskUrl(String htmlSource)
			throws ParseException;
}
