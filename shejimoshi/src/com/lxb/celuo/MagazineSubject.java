package com.lxb.celuo;

import java.util.ArrayList;
import java.util.List;

public class MagazineSubject implements Subject {

	private List<Observer> observerList = new ArrayList<>();
	private int version;

	@Override
	public void addObserver(Observer os) {
		observerList.add(os);
	}

	@Override
	public void deleteObserver(Observer os) {
		int i = observerList.indexOf(os);
		if (i >= 0) {
			observerList.remove(i);
		}
	}

	@Override
	public void notifyObserver() {
		for (Observer observer : observerList) {
			observer.update(version);
		}
	}

	// 该杂志发行了新版本
	public void publish() {
		// 新版本
		this.version++;
		// 信息更新完毕，通知所有观察者
		notifyObserver();
	}
}
