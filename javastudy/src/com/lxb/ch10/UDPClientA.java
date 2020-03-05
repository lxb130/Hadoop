package com.lxb.ch10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClientA {

	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket();

		String str = "abc";
		DatagramPacket dp = new DatagramPacket(str.getBytes(), 0, str.length(),
				InetAddress.getByName("localhost"), 9999);
		ds.send(dp);
		ds.close();
	}

}
