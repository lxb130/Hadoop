package com.lxb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadConfigUtil {
	public InputStream in = null;
	public BufferedReader br = null;
	private Properties config = null;
	/**
	 * ����������ļ�Ϊ�ı����Ͷ�Ϊkey/value��ʽ�Ļ���ֱ��get�����Լ���
	 */
	private String lineConfigTxt;

	public String getLineConfigTxt() {
		return lineConfigTxt;
	}

	public void setLineConfigTxt(String lineConfigTxt) {
		this.lineConfigTxt = lineConfigTxt;
	}

	// ��ʱ��configFilePath���Ƿ���ͨ�ļ�����properties�ļ��Ļ���Ҫ���д���
	public ReadConfigUtil(String configFilePath, boolean isConfig) {
		in = ReadConfigUtil.class.getClassLoader().getResourceAsStream(
				configFilePath);
		try {
			if (isConfig) {
				config = new Properties();
				config.load(in);
				// config.load(new InputStreamReader(in,
				// StaticValue.default_encoding));
				in.close();
			} else {
				// br = new BufferedReader(new InputStreamReader(in,
				// StaticValue.default_encoding));
				br = new BufferedReader(new InputStreamReader(in));
				this.lineConfigTxt = getTextLines();
			}
		} catch (IOException e) {
			System.out.println("���������ļ�ʱ����������!");
		}
	}

	public String getValue(String key) {
		try {
			String value = config.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());
			return null;
		}
	}

	private String getTextLines() {
		StringBuilder sb = new StringBuilder();
		String temp = null;
		try {
			while ((temp = br.readLine()) != null) {
				temp = temp.trim();
				if (temp.length() > 0 && (!temp.startsWith("#"))) {
					sb.append(temp);
					sb.append("\n");
				}
			}
			br.close();
			in.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡslaves�ļ�ʱ����������!");
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		
	}

}
