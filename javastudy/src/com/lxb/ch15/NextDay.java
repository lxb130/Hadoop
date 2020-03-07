package com.lxb.ch15;

import java.util.Date;

public class NextDay {

	public static void main(String[] args) {
		Date now = new Date();
		System.err.println(getNextDay(now));
	}

	private static Date getNextDay(Date now) {
		long addtime = 1;
		addtime *= 1;
		addtime *= 24;// 1天24小时
		addtime *= 60;// 一个小时60分钟
		addtime *= 60;// 一分钟60秒
		addtime *= 1000;// 一秒1000 毫秒
		Date date = new Date(now.getTime() + addtime);
		return date;

	}

}
