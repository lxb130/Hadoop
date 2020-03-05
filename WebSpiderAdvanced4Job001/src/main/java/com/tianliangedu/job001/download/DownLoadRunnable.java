package com.tianliangedu.job001.download;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tianliangedu.job001.iface.download.DownLoadInterface;
import com.tianliangedu.job001.parser.HtmlParserManager;
import com.tianliangedu.job001.persistence.DataPersistManager;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.schedule.TaskScheduleManager;
import com.tianliangedu.job001.ui.UIManager;
import com.tianliangedu.job001.utils.ObjectAndByteArrayConvertor;
import com.tianliangedu.job001.utils.RedisOperUtil;
import com.tianliangedu.job001.utils.StaticValue;
import com.tianliangedu.job001.utils.SystemConfigParas;
import com.tianliangedu.job001.utils.WebpageDownloadUtil4HttpClient;

/**
 * 下载线程实现类
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class DownLoadRunnable implements Runnable {
	static Logger logger = Logger.getLogger(DownLoadRunnable.class);

	// 定义存储解析出来的对象的list的key
	public static String toSaveNewsItemEntityListKey = "to_save_news_item_entity_list_key";

	// 线程可以运行的标志位
	private boolean enableRunningFlag = true;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnableRunningFlag() {
		return enableRunningFlag;
	}

	public void setEnableRunningFlag(boolean enableRunningFlag) {
		this.enableRunningFlag = enableRunningFlag;
	}

	public DownLoadRunnable(String name) {
		this.name = name;
	}

	/**
	 * 线程运行的入口方法， run方法一结束，线程就结束
	 */
	@Override
	public void run() {
		DownLoadInterface downloadInterface = new WebpageDownloadUtil4HttpClient();
		RedisOperUtil redisOperUtil = RedisOperUtil.getInstance();

		while (enableRunningFlag) {
			// 从任务调度器获取一个任务对象UrlTaskPojo
			UrlTaskPojo taskPojo = null;
			try {
				taskPojo = TaskScheduleManager.take();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (taskPojo != null) {
				// 第1步 打印下载下来的内容数据
				String htmlSource = downloadInterface.download(taskPojo
						.getUrl());
				if (htmlSource != null) {
					// System.out.println(htmlSource);
					// 将进入解析环节
					logger.info(this.name + "将进入解析环节!");
					try {
						NewsItemEntity itemEnity = HtmlParserManager
								.parserHtmlSource4CrawkTaskUrl(htmlSource);
						itemEnity.setTitle(taskPojo.getTitle());
						itemEnity.setPostTimeString(taskPojo.getPostTime());
						itemEnity.setSourceURL(taskPojo.getUrl());

						// 将解析出来的结果，回传到redis的指定list结构中去
						byte[] byteArray = ObjectAndByteArrayConvertor
								.convertObjectToByteArray(itemEnity);
						redisOperUtil
								.getJedis()
								.lpush(toSaveNewsItemEntityListKey
										.getBytes(StaticValue.defaultEncoding),
										byteArray);

						logger.info(taskPojo.getUrl() + ",网页下载与解析完成，并推送对象数据到redis中!");
					} catch (ParseException e) {
						logger.error("任务解析出现异常，任务详情为=" + taskPojo.toString());
						e.printStackTrace();
					} catch (Exception e2) {
						logger.error("任务非解析处理出现异常，任务详情为=" + taskPojo.toString());
						e2.printStackTrace();
					}
				} else {
					// 如果htmlSource==null,代表下载出错了!
					logger.error(this.name + "下载出错，该任务为=" + taskPojo);
				}
			} else {
				logger.info(this.name
						+ "没有待采集的任务，线程将睡眠"
						+ (SystemConfigParas.once_sleep_time_for_empty_task / 1000)
						+ "秒钟!");
				try {
					Thread.sleep(SystemConfigParas.once_sleep_time_for_empty_task);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.info(this.name + "本次睡眠结束!");
			}
		}
		this.enableRunningFlag = false;
		logger.info(this.name + "线程run方法即将结束!");
	}

	public static void main(String[] args) throws Exception {
		// 模拟测试，将种子地址加入到Task调度管理器当中
		UIManager.addSeedUrlsToTaskSchedule();
		DownLoadRunnable runnable_1 = new DownLoadRunnable("runnable-1");
		DownLoadRunnable runnable_2 = new DownLoadRunnable("runnable-2");
		new Thread(runnable_1).start();
		new Thread(runnable_2).start();
	}
}
