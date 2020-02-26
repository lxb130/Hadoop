package com.lxb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 
/**
 * 将一个文件中英文，中文，数字，其他字符以及字符总数统计出来
 * 
 * @author xcx
 * @time 2017年6月24日下午4:12:53
 */
public class Statistics {
 
	public static void main(String[] args) throws IOException {
		String E1 = "[\u4e00-\u9fa5]";// 中文
		String E2 = "[a-zA-Z]";// 英文
		String E3 = "[0-9]";// 数字
 
		String file = "E:\\bigdata3\\1.txt";// 要读的文件路径
		// 创建读入字节流
		FileInputStream fis = new FileInputStream(file);
		// 将字节流转化为字符流
		InputStreamReader isr = new InputStreamReader(fis);
		// 转化为缓存模式
		BufferedReader br = new BufferedReader(isr);
 
		int numSum = 0;// 记录数字字符
		int letSum = 0;// 记录英文字符
		int punSum = 0;// 记录标点字符
		int chineseSum = 0;// 记录中文字符
		int totle = 0;// 记录总字符
 
		// 将读出来的字符复制到ss
		String ss = "";
		String s;
		while ((s = br.readLine()) != null) {
			ss += s;
		}
		// 遍历字符串
		String temp;
		for (int i = 0; i < ss.length(); i++) {
			temp = String.valueOf(ss.charAt(i));
			if (temp.matches(E1)) {// 如果该字符匹配中文
				chineseSum++;
			} else if (temp.matches(E2)) {// 如果该字符匹配英文
				letSum++;
			} else if (temp.matches(E3)) {// 如果该字符匹配数字
				numSum++;
			} else
				punSum++;// 其他
		}
		
		// 总的字符数
		totle = numSum + letSum + punSum + chineseSum;
 
		// 输出
		System.out.println("数字有：" + numSum + "个");
		System.out.println("英文有：" + letSum + "个");
		System.out.println("其他字符有：" + punSum + "个");
		System.out.println("中文有：" + chineseSum + "个");
		System.out.println("总的字符有：" + totle + "个");
	}
 
}