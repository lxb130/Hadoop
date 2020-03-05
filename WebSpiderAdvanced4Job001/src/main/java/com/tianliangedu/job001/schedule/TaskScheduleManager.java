package com.tianliangedu.job001.schedule;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tianliangedu.job001.monitor.MonitorManager;
import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.utils.DateUtil;
import com.tianliangedu.job001.utils.ObjectAndByteArrayConvertor;
import com.tianliangedu.job001.utils.RedisOperUtil;

/**
 * 负责任务的调度，决定什么任务先被采集、什么时候后被采集
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class TaskScheduleManager {
	public static Logger logger = Logger.getLogger(TaskScheduleManager.class);

	// public static LinkedList<UrlTaskPojo> todoTaskPojoList = new
	// LinkedList<UrlTaskPojo>();
	public static LinkedList<UrlTaskPojo> doneTaskPojoList = new LinkedList<UrlTaskPojo>();

	// 新闻实体数据的已采集URL的集合，用于判重和增量采集
	// public static Set<String> savedNewsEntityUrlSet = new HashSet<String>();

	// redis中与savedNewsEntityUrlSet对应的set结构的key声明
	public static String uniqUrlSetKey = "uniq_url_set_key";
	// redis中与todoTaskPojoList对应的list结构的key声明
	public static String todoTaskPojoListKey = "todo_task_pojo_list_key";

	// redis工具类初始化
	public static RedisOperUtil redisOperUtil = RedisOperUtil.getInstance();

	// 在static方法块中，将所有需要从数据库中恢复的数据进行查询及恢复
	static {
		recovery();
	}

	public static void recovery() {
		// 恢复savedNewsEntityUrlSet
		synchronized (redisOperUtil) {
			// 恢复系统统计数据
			MonitorManager
					.setTotalNewsEntityNumber(getSavedNewsEntityUrlSetSize());

			// 用于恢复当天的已采集任务
			String currentDayFreqString = redisOperUtil.getJedis().hget(
					MonitorManager.currentDayStatisticKey,
					DateUtil.getCurrentDay());
			int currentDayFreq = currentDayFreqString == null ? 0 : Integer
					.parseInt(currentDayFreqString);
			MonitorManager.setTotalCurrentDayEntityNumber(currentDayFreq);
		}
	}

	// 将已采集出来的新闻数据条目的url加入到已采信数据实体url set中
	public static void addSavedNewsEntityUrlSet(String savedUrl) {
		synchronized (redisOperUtil) {
			// savedNewsEntityUrlSet.add(savedUrl);
			redisOperUtil.getJedis().sadd(uniqUrlSetKey, savedUrl);
		}
	}

	// 在redis中取得SavedNewsEntityUrlSet的长度
	public static long getSavedNewsEntityUrlSetSize() {
		synchronized (redisOperUtil) {
			return redisOperUtil.getJedis().scard(uniqUrlSetKey);
		}
	}

	public static boolean isInSavedNewsEntityUrlSet(String toSaveUrl) {
		synchronized (redisOperUtil) {
			// return savedNewsEntityUrlSet.contains(toSaveUrl);
			return redisOperUtil.getJedis().sismember(uniqUrlSetKey, toSaveUrl);
		}
	}

	public static synchronized int getDoneTaskSize() {
		return doneTaskPojoList.size();
	}

	public static long getTodoTaskSize() {
		synchronized (redisOperUtil) {
			return redisOperUtil.getJedis().llen(todoTaskPojoListKey);
		}
	}

	public static void addUrlTaskPojoList(List<UrlTaskPojo> todoAddTaskList)
			throws IOException {
		// 分布式后，将该直接进程的对象，转换成对redis list的操作
		// todoTaskPojoList.addAll(todoAddTaskList);
		for (UrlTaskPojo taskPojo : todoAddTaskList) {
			if (!isInSavedNewsEntityUrlSet(taskPojo.getTitle()
					+ taskPojo.getUrl())) {
				addOneUrlTaskPojo(taskPojo);
			}
		}
		logger.info("当前的todoTaskPojoList.size()=" + getTodoTaskSize());
	}

	public static void addOneUrlTaskPojo(UrlTaskPojo todoAddTaskPojo)
			throws IOException {
		synchronized (redisOperUtil) {
			byte[] byteArray = ObjectAndByteArrayConvertor
					.convertObjectToByteArray(todoAddTaskPojo);
			redisOperUtil.getJedis().lpush(
					todoTaskPojoListKey.getBytes("utf-8"), byteArray);
		}
	}

	public static synchronized void addDoneUrlTaskPojo(
			UrlTaskPojo doneAddTaskPojo) {
		doneTaskPojoList.add(doneAddTaskPojo);
	}

	// public static void removeUrlTaskPojoList(
	// List<UrlTaskPojo> todoRemoveTaskList) {
	// redisOperUtil.getJedis().ltrim(todoTaskPojoListKey, 1, 0);
	// }

	public static void removeOneUrlTaskPojo(UrlTaskPojo todoRemoveTaskPojo)
			throws UnsupportedEncodingException, IOException {
		synchronized (redisOperUtil) {
			redisOperUtil.getJedis().lrem(
					todoTaskPojoListKey.getBytes("utf-8"),
					0,
					ObjectAndByteArrayConvertor
							.convertObjectToByteArray(todoRemoveTaskPojo));
		}
	}

	public static UrlTaskPojo take() throws ClassNotFoundException, IOException {
		synchronized (redisOperUtil) {
			byte[] byteArray = redisOperUtil.getJedis().rpop(
					todoTaskPojoListKey.getBytes("utf-8"));
			UrlTaskPojo urlTaskPojo = null;
			if (byteArray != null) {
				urlTaskPojo = (UrlTaskPojo) ObjectAndByteArrayConvertor
						.convertByteArrayToObject(byteArray);
			}
			return urlTaskPojo;
		}
	}

	public static void cleanTodoTaskList() {
		synchronized (redisOperUtil) {
			redisOperUtil.getJedis().ltrim(todoTaskPojoListKey, 1, 0);
		}
	}
}
