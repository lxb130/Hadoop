package com.tianliangedu.job001.persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tianliangedu.job001.download.DownLoadRunnable;
import com.tianliangedu.job001.iface.persistence.DataPersistenceInterface;
import com.tianliangedu.job001.monitor.MonitorManager;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.schedule.TaskScheduleManager;
import com.tianliangedu.job001.utils.SystemConfigParas;

/**
 * 数据持久化管理器
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class DataPersistManager {
	// 线程组初始化
		public static ThreadGroup tGroup = new ThreadGroup("dataPersist_threadGroup");
		// 线程组之Runnable管理的集合对象
		public static List<DataPersistRunnable> runnableList = new ArrayList<DataPersistRunnable>();
		

		/**
		 * 开启多少个下载线程
		 */
		public static void start() {
			List<Runnable> runnableList = new ArrayList<Runnable>();

			for (int i = 1; i <= SystemConfigParas.init_persist_consumer_number; i++) {
				DataPersistRunnable oneRunnable = new DataPersistRunnable(
						"data_persist_consumer_" + i);
				new Thread(tGroup, oneRunnable, "thread_" + i).start();
				runnableList.add(oneRunnable);
			}
		}

		// 获取线程的状态信息-多少个还活着的下载线程
		public static int getActiveDownLoadThreads() {
			return tGroup.activeCount();
		}

		// 一共初始始了多少个线程
		public static int getInitDownLoadThreads() {
			return SystemConfigParas.init_persist_consumer_number;
		}

		// 停止掉所有线程
		public static void stopAllDownloadThreads() {
			for (DataPersistRunnable runnable : runnableList) {
				runnable.setEnableRunningFlag(false);
			}
		}

		// 停止掉某个runnable对象
		public static void stopOneDownloadThread(DataPersistRunnable runnable) {
			runnable.setEnableRunningFlag(false);
		}
}
