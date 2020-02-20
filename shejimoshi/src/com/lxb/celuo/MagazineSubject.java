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

	// ����־�������°汾
	public void publish() {
		// �°汾
		this.version++;
		// ��Ϣ������ϣ�֪ͨ���й۲���
		notifyObserver();
	}
}
