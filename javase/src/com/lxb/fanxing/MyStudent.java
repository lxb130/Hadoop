package com.lxb.fanxing;

public class MyStudent<T> {
	private T score;

	public MyStudent() {
	}

	public MyStudent(T score) {
		this.score = score;
	}

	public T getScore() {
		return score;
	}

	public void setScore(T score) {
		this.score = score;
	}

}
