package com.lxb.ch10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(80);
		System.out.println("�������ɹ�����");
		Socket sk = null;
		while ((sk = ss.accept()) != null) {
			new HttpThread(sk).start();
		}

	}

}
