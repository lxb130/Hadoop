package com.tianliangedu.job001.persistence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.tianliangedu.job001.download.DownLoadRunnable;
import com.tianliangedu.job001.iface.persistence.DataPersistenceInterface;
import com.tianliangedu.job001.monitor.MonitorManager;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.pojos.UrlTaskPojo.TaskTypeEnum;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.schedule.TaskScheduleManager;
import com.tianliangedu.job001.utils.ObjectAndByteArrayConvertor;
import com.tianliangedu.job001.utils.RedisOperUtil;
import com.tianliangedu.job001.utils.StaticValue;
import com.tianliangedu.job001.utils.SystemConfigParas;

public class DataPersistRunnable implements Runnable {
	static Logger logger = Logger.getLogger(DataPersistRunnable.class);

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

	public DataPersistRunnable(String name) {
		this.name = name;
	}

	// 将实现类初始化
//	 public static DataPersistenceInterface persistenceInterface = new
//	 DataPersist4MysqlImpl();
	public static DataPersistenceInterface persistenceInterface = new DataPersist4EsImpl();

	@Override
	public void run() {
		// 初始化redis工具类
		RedisOperUtil redisOperUtil = RedisOperUtil.getInstance();
		while (enableRunningFlag) {
			byte[] byteArray = null;
			try {
				byteArray = redisOperUtil.getJedis().rpop(
						DownLoadRunnable.toSaveNewsItemEntityListKey
								.getBytes(StaticValue.defaultEncoding));
				if (byteArray != null) {
					try {
						NewsItemEntity itemEntity = (NewsItemEntity) ObjectAndByteArrayConvertor
								.convertByteArrayToObject(byteArray);
						boolean isSaveOK = persist(itemEntity);
						if (!isSaveOK) {
							TaskScheduleManager.cleanTodoTaskList();
							logger.info("已发现重复采集的数据，将清空本轮的待采集URL任务列表，等待下一轮任务采集!");
						}
						// 将已成功采集完成的URL加入到done task url中
						TaskScheduleManager.addDoneUrlTaskPojo(new UrlTaskPojo(
								itemEntity.getTitle(), itemEntity
										.getSourceURL(),
								TaskTypeEnum.CRAWL_TASK));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					logger.info(this.name
							+ "没有待存储的任务，线程将睡眠"
							+ (SystemConfigParas.once_sleep_time_for_empty_task / 1000)
							+ "秒钟!");
					try {
						Thread.sleep(SystemConfigParas.once_sleep_time_for_empty_task);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					logger.info(this.name + "持久化线程，本次睡眠结束!");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	// 该返回值boolean，若为true则代表发现重复采集出来的结果，若为false,则代表本次无重复
	public static synchronized boolean persist(NewsItemEntity itemEntity) {
		if (!TaskScheduleManager.isInSavedNewsEntityUrlSet(itemEntity
				.toUniqString())) {
			persistenceInterface.persist(itemEntity);
			// 对数据监控管理器进行打点上报数据的操作
			// 因为历史统计，也是基于当天的，故打点上报当天后，由该方法内部实现历史数据统计
			MonitorManager.AddNewsEntityNumber4CurrentDay(1);

			TaskScheduleManager.addSavedNewsEntityUrlSet(itemEntity
					.toUniqString());
			return true;
		}
		return false;
	}
}
