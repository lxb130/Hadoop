package com.tianliangedu.job001.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.tianliangedu.job001.download.DownLoadManager;
import com.tianliangedu.job001.monitor.MonitorManager;
import com.tianliangedu.job001.persistence.DataPersistManager;
import com.tianliangedu.job001.ui.UIManager;
import com.tianliangedu.job001.utils.SystemConfigParas;

/**
 * 系统启动类,入口类
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class SystemController {
	// 将log4j配置文件放到jar包外边的路径更新
	static {
		PropertyConfigurator.configure(System.getProperty("user.dir")
				+ File.separator + "log4j.properties");
	}
	// 添加日声功能
	public static Logger logger = Logger.getLogger(SystemController.class);

	// 主线程开始的地方
	public static void main(String[] args) throws Exception {
		if (SystemConfigParas.is_master) {
			logger.info("即将开启master主节点!");
			// 主节点
			// 启动系统监控管理器
			MonitorManager.start();
			// 启动数据持久化管理
			DataPersistManager.start();

			// 周期执行
			int circleCounter = 1;
			while (true) {
				logger.info("第" + circleCounter + "轮添加种子任务执行开始!");
				UIManager.parseSeedUrlsTaskToSchedule();
				logger.info("第" + circleCounter + "轮添加种子执行结束!");
				circleCounter++;
				logger.info("即将休息30秒!");
				Thread.sleep(60 * 1000);
			}
		} else {
			logger.info("即将开始slave子节点!");
			// 子节点
			// 启动下载线程，并在起内完成解析工作
			DownLoadManager.start();
		}
	}
}
