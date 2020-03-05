package com.lxb.ch10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServerA {

	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
		byte[] buff = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buff, 1024);
		ds.receive(dp);
		String str = new String(dp.getData(), 0, dp.getLength());
		System.out.println(str);
		ds.close();
	}

}
