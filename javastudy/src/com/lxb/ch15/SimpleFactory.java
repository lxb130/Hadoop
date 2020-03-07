package com.lxb.ch15;

public class SimpleFactory {

	public static void main(String[] args) {
		Auto car = AutoFactory.createAuto(1);
		Auto truck = AutoFactory.createAuto(2);

	}

}

class AutoFactory {
	public static Auto createAuto(int type) {
		if (type == 1) {
			return new Car();
		} else {
			return new Truck();
		}
	}
}

abstract class Auto {
	
}

class Car extends Auto {
	public Car() {
		System.out.println("生产轿车");
	}
}

class Truck extends Auto {
	public Truck() {
		System.out.println("生产卡车");
	}
}