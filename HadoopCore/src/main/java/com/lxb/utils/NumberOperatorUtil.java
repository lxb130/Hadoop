package com.lxb.utils;

import java.text.DecimalFormat;

public class NumberOperatorUtil {
	public static DecimalFormat df=new DecimalFormat("#.##");
	public static String format2Dot(double input){
		return df.format(input);
	}
}
