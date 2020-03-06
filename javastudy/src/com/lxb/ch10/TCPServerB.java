package com.lxb.ch10;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServerB {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(1111);
		Socket sk = null;
		while ((sk = ss.accept()) != null) {
			new MyThread(sk).start();
		}
		ss.close();
	}
}
