package com.tianliangedu.job001.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static String getMatchText(String input, String regex, int groupIndex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(groupIndex);
		}
		return null;
	}

	public static void main(String[] args) {
		String line = "<meta http-equiv=\"content-type\" content=\"text/html; charset=gb2312\" />";
		String regex = "charset=([\\s\\S]*)\"";
		String matchText=getMatchText(line,regex,1);
		System.out.println(matchText);
	}
}
