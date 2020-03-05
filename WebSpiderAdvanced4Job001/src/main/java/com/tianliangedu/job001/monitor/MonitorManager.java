package com.tianliangedu.job001.monitor;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.tianliangedu.job001.schedule.TaskScheduleManager;
import com.tianliangedu.job001.utils.DateUtil;
import com.tianliangedu.job001.utils.RedisOperUtil;

/**
 * 系统监控管理器
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class MonitorManager {
	public static Logger logger = Logger.getLogger(MonitorManager.class);
	// 定义一共采集入库了多少条新闻数据
	public static long totalNewsEntityNumber = 0;

	// 初始化redis工具类
	public static RedisOperUtil redisOperUtil = RedisOperUtil.getInstance();

	// 定义hashmap对象，在redis中的key值
	public static String currentDayStatisticKey = "current_day_statistic_key";

	public static synchronized void AddNewsEntityNumber4History(
			int newsAddNumber) {
		totalNewsEntityNumber += newsAddNumber;
	}

	// 直接数据值恢复-历史值
	public static synchronized void setTotalNewsEntityNumber(
			long totalNewsEntityNumber) {
		MonitorManager.totalNewsEntityNumber = totalNewsEntityNumber;
	}

	// 直接数据值恢复-当天值
	public static synchronized void setTotalCurrentDayEntityNumber(
			int totalCurrentDayEntityNumber) {
		MonitorManager.totalCurrentDayEntityNumber = totalCurrentDayEntityNumber;
	}

	// 定义当天一共采集了多少条新闻数据
	public static int totalCurrentDayEntityNumber = 0;
	public static String currentDay = DateUtil.getCurrentDay();

	public static synchronized void AddNewsEntityNumber4CurrentDay(
			int newsAddNumber) {
		Jedis jedis = redisOperUtil.getJedis();
		String currentDay = DateUtil.getCurrentDay();
		if (jedis.hexists(currentDayStatisticKey, currentDay)) {
			int oldCurrentDayFreq = Integer.parseInt(jedis.hget(
					currentDayStatisticKey, currentDay));
			totalCurrentDayEntityNumber = oldCurrentDayFreq + newsAddNumber;
			jedis.hset(currentDayStatisticKey, currentDay,
					totalCurrentDayEntityNumber + "");
		} else {
			jedis.hset(currentDayStatisticKey, currentDay, newsAddNumber + "");
			totalCurrentDayEntityNumber = newsAddNumber;
		}
		AddNewsEntityNumber4History(newsAddNumber);
	}

	// 获取已采集URL有多少个
	public static int getTotalDoneUrlNumber() {
		return TaskScheduleManager.getDoneTaskSize();
	}

	// 获取待采集的URL有多少个
	public static long getTodoTaskUrlNumber() {
		return TaskScheduleManager.getTodoTaskSize();
	}

	public static void start() {
		String name = "系统监控线程---";
		new MonitorThread(name).start();
	}

	public static class MonitorThread extends Thread {
		private String name;

		public MonitorThread(String name) {
			super(name);
			this.name = name;
		}

		@Override
		public void run() {
			while (true) {
				try {
					logger.info("监控线程即将休眠5秒----");
					Thread.sleep(5000);
					logger.info("监控线程即将休眠结束----");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("\n");
				stringBuilder.append("一共采集了" + totalNewsEntityNumber + "条数据");
				stringBuilder.append("\n");
				stringBuilder.append("今天共采集了" + totalCurrentDayEntityNumber
						+ "条数据");
				stringBuilder.append("\n");
				stringBuilder.append("已采集完成URL任务" + getTotalDoneUrlNumber()
						+ "个");
				stringBuilder.append("\n");
				stringBuilder.append("待采集URL任务" + getTodoTaskUrlNumber() + "个");

				logger.info(stringBuilder.toString());
			}
		}
	}

	public static void main(String[] args) {
		start();
	}
}
