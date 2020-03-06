package com.lxb.ch10;

public class Chat {

	public static void main(String[] args) {
		new ReceviceThread(1001).start();

		new SendThread(1002, 1003).start();
		
	}

}
