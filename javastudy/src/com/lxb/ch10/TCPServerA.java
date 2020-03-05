package com.lxb.ch10;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServerA {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(1111);
		Socket sk = ss.accept();
		OutputStream os = sk.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.print("now time" + new Date());
		pw.flush();
		pw.close();
		os.close();
		sk.close();
		ss.close();
	}
}
