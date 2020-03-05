package com.tianliangedu.job001.pojos.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.tianliangedu.job001.utils.DateUtil;

/**
 * 存储解析下来的新闻条目对象
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class NewsItemEntity implements Serializable{
	private String title;
	private String postTimeString;
	private String sourceURL;
	private Date insertDate;
	private Date postDateObj;

	private String sourceName;
	private String body;

	public NewsItemEntity() {
		// 手动处理生成insertDate对象
		this.insertDate = DateUtil.getDate();
	}

	public NewsItemEntity(String title, String postTimeString,
			String sourceURL, String sourceName, String body)
			throws ParseException {
		super();
		this.title = title;
		this.postTimeString = postTimeString;
		this.sourceURL = sourceURL;
		this.sourceName = sourceName;
		this.body = body;

		// 手动处理生成insertDate和postDateObj对象
		this.insertDate = DateUtil.getDate();
		this.postDateObj = DateUtil.parserStringToDate(this.postTimeString);
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "NewsItemEntity [title=" + title + ", postTimeString="
				+ postTimeString + ", sourceURL=" + sourceURL + ", insertDate="
				+ insertDate + ", postDateObj=" + postDateObj + ", sourceName="
				+ sourceName + ", body=" + body + "]";
	}

	public String toUniqString() {
		return title + sourceURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostTimeString() {
		return postTimeString;
	}

	public void setPostTimeString(String postTimeString) throws ParseException {
		this.postTimeString = postTimeString;
		this.postDateObj = DateUtil.parserStringToDate(this.postTimeString);
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getPostDateObj() {
		return postDateObj;
	}

	public void setPostDateObj(Date postDateObj) {
		this.postDateObj = postDateObj;
	}
}
