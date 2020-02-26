package com.lxb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 
/**
 * ��һ���ļ���Ӣ�ģ����ģ����֣������ַ��Լ��ַ�����ͳ�Ƴ���
 * 
 * @author xcx
 * @time 2017��6��24������4:12:53
 */
public class Statistics {
 
	public static void main(String[] args) throws IOException {
		String E1 = "[\u4e00-\u9fa5]";// ����
		String E2 = "[a-zA-Z]";// Ӣ��
		String E3 = "[0-9]";// ����
 
		String file = "E:\\bigdata3\\1.txt";// Ҫ�����ļ�·��
		// ���������ֽ���
		FileInputStream fis = new FileInputStream(file);
		// ���ֽ���ת��Ϊ�ַ���
		InputStreamReader isr = new InputStreamReader(fis);
		// ת��Ϊ����ģʽ
		BufferedReader br = new BufferedReader(isr);
 
		int numSum = 0;// ��¼�����ַ�
		int letSum = 0;// ��¼Ӣ���ַ�
		int punSum = 0;// ��¼����ַ�
		int chineseSum = 0;// ��¼�����ַ�
		int totle = 0;// ��¼���ַ�
 
		// �����������ַ����Ƶ�ss
		String ss = "";
		String s;
		while ((s = br.readLine()) != null) {
			ss += s;
		}
		// �����ַ���
		String temp;
		for (int i = 0; i < ss.length(); i++) {
			temp = String.valueOf(ss.charAt(i));
			if (temp.matches(E1)) {// ������ַ�ƥ������
				chineseSum++;
			} else if (temp.matches(E2)) {// ������ַ�ƥ��Ӣ��
				letSum++;
			} else if (temp.matches(E3)) {// ������ַ�ƥ������
				numSum++;
			} else
				punSum++;// ����
		}
		
		// �ܵ��ַ���
		totle = numSum + letSum + punSum + chineseSum;
 
		// ���
		System.out.println("�����У�" + numSum + "��");
		System.out.println("Ӣ���У�" + letSum + "��");
		System.out.println("�����ַ��У�" + punSum + "��");
		System.out.println("�����У�" + chineseSum + "��");
		System.out.println("�ܵ��ַ��У�" + totle + "��");
	}
 
}