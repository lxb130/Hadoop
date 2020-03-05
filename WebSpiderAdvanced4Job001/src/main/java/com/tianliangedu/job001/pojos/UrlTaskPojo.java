package com.tianliangedu.job001.pojos;

import java.io.Serializable;

/**
 * 对url任务的封装类
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class UrlTaskPojo implements Serializable{
	private String title;
	private String url;
	private TaskTypeEnum taskType=TaskTypeEnum.ROOT_URL;
	
	private String postTime;
	
	
	public static enum TaskTypeEnum {
		ROOT_URL, CRAWL_TASK
	}

	public UrlTaskPojo() {

	}

	public UrlTaskPojo(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	
	public UrlTaskPojo(String title, String url,TaskTypeEnum taskType) {
		this(title,url);
		this.taskType=taskType;
	}
	
	public UrlTaskPojo(String title, String url,String postTime,TaskTypeEnum taskType) {
		this(title,url);
		this.taskType=taskType;
		this.postTime=postTime;
	}
	
	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlTaskPojo [title=" + title + ", url=" + url + ", taskType="
				+ taskType + "]";
	}

}
