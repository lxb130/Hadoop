package com.lxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * �ļ�����
 * @author lanbing
 * @date 2018��8��29��
 *
 */
public class DirClone {
	
	//Դ·��
	public static String sourse = "E:\\eclipse\\workspace\\javaTest";
	//Ŀ��·��
	public static String target = "E:\\eclipse\\workspace\\javaTest2";
 
	/**
	 * ������
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Clone(sourse);
		long endTime = System.currentTimeMillis();
		System.out.println("�ܹ���ʱ��"+(endTime-startTime));
	}
 
	/**
	 * �����ļ��в�����
	 * @param sourse
	 * @param target
	 */
	public static void Clone(String url){
		//��ȡĿ¼�������ļ�
		File f = new File(url);
		File[] allf = f.listFiles();
		
		//���������ļ�
		for(File fi:allf) {
			try {
				//ƴ��Ŀ��λ��
				String URL = target+fi.getAbsolutePath().substring(sourse.length());
 
				//����Ŀ¼���ļ�
				if(fi.isDirectory()) {
					Createflies(URL);
				}else {
					fileInputOutput(fi.getAbsolutePath(),URL);
				}
 
				//�ݹ����
				if(fi.isDirectory()) {
					Clone(fi.getAbsolutePath());
				}
 
			}catch (Exception e) {
				System.out.println("error");
			}
		}
	}
 
	/**
	 * �����ļ�
	 * @param sourse
	 * @param target
	 */
	public static void fileInputOutput(String sourse,String target) {
		try {
			File s = new File(sourse);
			File t = new File(target);
			
			FileInputStream fin = new FileInputStream(s);
			FileOutputStream fout = new FileOutputStream(t);
 
			byte[] a = new byte[1024*1024*4];	
			int b = -1;
			
			//�߶���д
			while((b = fin.read(a))!=-1) {
				fout.write(a,0,b);
			}
 
			fout.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * ����Ŀ¼
	 * @param name
	 * @return
	 */
	public static boolean Createflies(String name) {
		boolean flag=false;
		File file=new File(name);
		//����Ŀ¼
		if(file.mkdir() == true){
			System.out.println("�ļ��д����ɹ���");
			flag=true;
		}else {
			System.out.println("�ļ��д���ʧ�ܣ�");
			flag=false;
			
		}
		
		return flag;
	}
}
 
 
 