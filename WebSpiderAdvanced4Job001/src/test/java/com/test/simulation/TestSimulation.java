package com.test.simulation;

import java.io.IOException;

import com.tianliangedu.job001.utils.WebpageDownloadUtil4HttpClient;

public class TestSimulation {
	public static void main(String[] args) throws IOException {
		String loginUrl = "http://www.tianya.cn/";
		String username = "skylight2015";
		String password = "tianliang123..";
		
		String htmlSource = WebpageDownloadUtil4HttpClient
				.downloadStatic(loginUrl);

		System.out.println(htmlSource);
	}
}
