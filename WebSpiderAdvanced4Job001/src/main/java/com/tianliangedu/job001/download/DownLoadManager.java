package com.tianliangedu.job001.download;

import java.util.ArrayList;
import java.util.List;

import com.tianliangedu.job001.utils.ReadConfigUtil;
import com.tianliangedu.job001.utils.SystemConfigParas;

/**
 * 网页下载管理器
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class DownLoadManager {
	// 线程组初始化
	public static ThreadGroup tGroup = new ThreadGroup("download_threadGroup");
	// 线程组之Runnable管理的集合对象
	public static List<DownLoadRunnable> runnableList = new ArrayList<DownLoadRunnable>();
	

	/**
	 * 开启多少个下载线程
	 */
	public static void start() {
		List<Runnable> runnableList = new ArrayList<Runnable>();

		for (int i = 1; i <= SystemConfigParas.init_download_consumer_number; i++) {
			DownLoadRunnable oneRunnable = new DownLoadRunnable(
					"download_consumer_" + i);
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
		return SystemConfigParas.init_download_consumer_number;
	}

	// 停止掉所有线程
	public static void stopAllDownloadThreads() {
		for (DownLoadRunnable runnable : runnableList) {
			runnable.setEnableRunningFlag(false);
		}
	}

	// 停止掉某个runnable对象
	public static void stopOneDownloadThread(DownLoadRunnable runnable) {
		runnable.setEnableRunningFlag(false);
	}
	
	public static void main(String[] args) throws InterruptedException {
		start();
	}
}
