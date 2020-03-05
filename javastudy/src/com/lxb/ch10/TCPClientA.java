package com.lxb.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientA {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket s = new Socket("localhost", 1111);
		InputStream is = s.getInputStream();

		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String str = br.readLine();
		System.out.println(str);
	}
}
