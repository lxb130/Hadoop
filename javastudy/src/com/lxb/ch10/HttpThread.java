package com.lxb.ch10;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpThread extends Thread {
	private Socket socket;

	public HttpThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println("<html>");
			pw.println("<body>");
			pw.println("hello this is my page");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
