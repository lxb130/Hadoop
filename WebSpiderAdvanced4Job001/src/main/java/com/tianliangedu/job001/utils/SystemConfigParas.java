package com.tianliangedu.job001.utils;

import java.io.IOException;

/**
 * 系统配置参数工具类，集中读取
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class SystemConfigParas {
	// 初始化参数读取的工具类实例
	public static ReadConfigUtil configUtil = null;
	public static ReadConfigUtil configUtil_db = null;
	static {
		try {
			configUtil = new ReadConfigUtil("spider.properties");
			configUtil_db = new ReadConfigUtil("jdbc.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取爬虫节点角色
	 */
	public static boolean is_master = configUtil.getBooleanValue("is_master");
	
	/**
	 * 集中读取download相关的参数
	 */
	// 初始化时候的下载消费线程的数量
	public static int init_download_consumer_number = Integer.parseInt(configUtil
			.getValue("init_download_consumer_number"));
	// 每次下载线程获取到空任务时候的，一次睡眠时间长度，单位为秒
	public static int once_sleep_time_for_empty_task = Integer
			.parseInt(configUtil.getValue("once_sleep_time_for_empty_task")) * 1000;

	/**
	 * 集中读取persist相关的参数
	 */
	// 初始化时候的下载消费线程的数量
	public static int init_persist_consumer_number = Integer.parseInt(configUtil
			.getValue("init_persist_consumer_number"));
	
	/**
	 * 读取配置数据库的参数
	 */
	public static String db_driver = configUtil_db.getValue("db_driver");
	public static String db_url = configUtil_db.getValue("db_url");
	public static String db_username = configUtil_db.getValue("db_username");
	public static String db_password = configUtil_db.getValue("db_password");

	/**
	 * 读取配置redis的参数
	 */
	public static String redis_ip = configUtil.getValue("redis_ip");
	public static int redis_passport = configUtil.getIntValue("redis_passport");
	public static String redis_auth = configUtil.getValue("redis_auth");

	/**
	 * 读取配置es的参数
	 */
	public static String es_cluster_name = configUtil.getValue("es_cluster_name");
	public static String hostname = configUtil.getValue("hostname");
	public static int admin_port = configUtil.getIntValue("admin_port");
	
}
