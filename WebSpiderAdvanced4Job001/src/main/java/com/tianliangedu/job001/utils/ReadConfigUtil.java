package com.tianliangedu.job001.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * 读取配置文件的工具类，既支持直接读取classpath下的，也支持读取外置配置文件
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public class ReadConfigUtil {
	// 初始化javase自带的配置文件读取工具类
	private Properties configObj = new Properties();

	public ReadConfigUtil(String configFilePath) throws IOException {
		// 配置文件读取顺序，第一是系统文件路径，第二是classpath路径
		File configFile = new File(configFilePath);
		InputStream is = null;
		Reader reader = null;
		if (configFile.exists()) {
			is = new FileInputStream(configFile);
		} else {
			is = ReadConfigUtil.class.getClassLoader().getResourceAsStream(
					configFilePath);
		}
		reader = new InputStreamReader(is);
		configObj.load(reader);
		reader.close();
	}

	public String getValue(String key) {
		return configObj.getProperty(key);
	}

	public int getIntValue(String key) {
		return Integer.parseInt(getValue(key));
	}
	
	public boolean getBooleanValue(String key) {
		return Boolean.parseBoolean(getValue(key));
	}

	public static void main(String[] args) throws IOException {
		String configFilePath = "spider.properties";
		ReadConfigUtil readConfigUtil = new ReadConfigUtil(configFilePath);
		System.out.println(readConfigUtil.getValue("init_consumer_number"));
	}
}
