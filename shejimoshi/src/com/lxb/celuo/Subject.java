package com.lxb.celuo;

public interface Subject {
	void addObserver(Observer os);

	void deleteObserver(Observer os);

	void notifyObserver();
}
