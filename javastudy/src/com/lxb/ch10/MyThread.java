package com.lxb.ch10;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class MyThread extends Thread {
	private Socket socket;

	public MyThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.print("服务器当前时间为"+new Date());
			int count=0;
			System.out.println("接收访问次数为"+count++);
			pw.flush();
			pw.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
