package com.lxb.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.xml.sax.InputSource;

public class SendThread extends Thread {
	private DatagramSocket ds;
	private int sendPort;

	public SendThread(int port,int sendPort) {
		this.sendPort = sendPort;
		try {
			this.ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public void run() {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String str = null;
			while ((str = br.readLine()) != null) {
				DatagramPacket dp = new DatagramPacket(str.getBytes(), 0,
						str.length(),InetAddress.getByName("localhost"),sendPort);
				
				ds.send(dp);
				System.out.println("send:"+str);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
