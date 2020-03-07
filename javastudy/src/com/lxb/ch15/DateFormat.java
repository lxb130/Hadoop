package com.lxb.ch15;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(date2Format(now));
	}

	private static String date2Format(Date now) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(now);
		return str;
	}

}
