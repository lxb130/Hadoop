package com.tianliangedu.job001.ui;

import java.util.ArrayList;
import java.util.List;

import com.tianliangedu.job001.download.DownLoadManager;
import com.tianliangedu.job001.iface.download.DownLoadInterface;
import com.tianliangedu.job001.monitor.MonitorManager;
import com.tianliangedu.job001.parser.HtmlParserManager;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.schedule.TaskScheduleManager;
import com.tianliangedu.job001.utils.IOUtil;
import com.tianliangedu.job001.utils.StaticValue;
import com.tianliangedu.job001.utils.WebpageDownloadUtil4HttpClient;

/**
 * 该类作用为用户接口的管理类，包括种子添加接口、种子添加的不同方式和来源
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class UIManager {
	// 实始化一个网页下载接口的实现类，此处为httpclient下载方式
	public static DownLoadInterface downloadInterface = new WebpageDownloadUtil4HttpClient();

	public static UrlTaskPojo getRootUrlByDirect() {
		return new UrlTaskPojo("中国青年网-国内新闻", "http://news.youth.cn/gn/");
	}

	public static UrlTaskPojo getRootUrlByStaticValue() {
		return new UrlTaskPojo(StaticValue.rootTitle, StaticValue.rootUrl);
	}

	public static List<UrlTaskPojo> getRootUrlBySeedFile(String dataFilePath,
			boolean isClasspath) throws Exception {
		List<String> lineList = IOUtil.readFileToList(dataFilePath,
				isClasspath, StaticValue.defaultEncoding);
		List<UrlTaskPojo> resultTaskPojo = new ArrayList<UrlTaskPojo>();

		for (String line : lineList) {
			line = line.trim();
			if (line.length() > 0 && !line.startsWith("#")) {
				String[] columnArray = line.split("\\s");
				if (columnArray.length == 2) {
					UrlTaskPojo tempPojo = new UrlTaskPojo(
							columnArray[0].trim(), columnArray[1].trim());
					resultTaskPojo.add(tempPojo);
				} else {
					System.err.println("错误行:" + line);
					throw new Exception("存在不规范行，请检查!");
				}
			}
		}
		return resultTaskPojo;
	}

	public static void addSeedUrlsToTaskSchedule() throws Exception {
		// 定义种文件路径
		String dataFilePath = "seeds.txt";
		// 将种子任务从种子文件中读取出来，形成种子任务集合
		List<UrlTaskPojo> seedUrlPojoList = UIManager.getRootUrlBySeedFile(
				dataFilePath, false);
		TaskScheduleManager.addUrlTaskPojoList(seedUrlPojoList);
	}

	/**
	 * 将给定的种子任务，先进行采集和解析，将二级任务传递给任务管理器，去接受任务调度
	 * 
	 * @throws Exception
	 */
	public static void parseSeedUrlsTaskToSchedule() throws Exception {
		// 定义种文件路径
		String dataFilePath = "seeds.txt";
		// 将种子任务从种子文件中读取出来，形成种子任务集合
		List<UrlTaskPojo> seedUrlPojoList = UIManager.getRootUrlBySeedFile(
				dataFilePath, false);
		// 此时做与中级版的改动：将任务不再直接放到任务调度管理器，而是先逐个种子URL进行采集和解析，将解析出来的二级任务(子
		// 任务)再添加到TaskSchedule中去
		for (UrlTaskPojo urlTaskPojo : seedUrlPojoList) {
			// 遍历集合，拿到每一页的URL封装的对象
			String htmlSource = downloadInterface
					.download(urlTaskPojo.getUrl());
			List<UrlTaskPojo> urlTaskPojoList = HtmlParserManager
					.parserHtmlSource4RootUrl(htmlSource);
			TaskScheduleManager.addUrlTaskPojoList(urlTaskPojoList);
		}
	}

	public static void main(String[] args) throws Exception {
		// 第1步、起动uiManager，注入种子任务
		// addSeedUrlsToTaskSchedule();
		// 第2步、启动下载线程，并在起内完成解析工作
		// DownLoadManager.start();
		// 第3步、启动系统监控管理器
		// MonitorManager.start();

		// 做部分测试
		parseSeedUrlsTaskToSchedule();

	}
}
