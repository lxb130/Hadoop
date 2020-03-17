package com.lxb.ch15;

public class Factory {

	public static void main(String[] args) {
		CarFactory carFactory=new CarFactory();
		carFactory.createAuto();
		TruckFactory truckFactory=new TruckFactory();
		truckFactory.createAuto();

	}

}

abstract class AbstractAutoFactory {
	public abstract Auto createAuto();
}

class CarFactory extends AbstractAutoFactory{

	@Override
	public Auto createAuto() {
			return new Car();
	}
	
}

class TruckFactory extends AbstractAutoFactory{

	@Override
	public Auto createAuto() {
		// TODO Auto-generated method stub
		return new Truck();
	}
	
}