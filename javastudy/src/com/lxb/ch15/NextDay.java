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
		addtime *= 24;// 1��24Сʱ
		addtime *= 60;// һ��Сʱ60����
		addtime *= 60;// һ����60��
		addtime *= 1000;// һ��1000 ����
		Date date = new Date(now.getTime() + addtime);
		return date;

	}

}
