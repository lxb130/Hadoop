package com.lxb.ch10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceviceThread extends Thread {
	private DatagramSocket ds;

	public ReceviceThread(int port) {
		try {
			this.ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			byte[] buff = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buff, 1024);
			while (true) {
				ds.receive(dp);
				String str = new String(dp.getData(), 0, dp.getLength());
				System.out.println("recevice" + str);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
