package com.lxb.ch15;

public class NineNineMulitTable {

	//核心思想：一行一行实现
	
	public static void main(String[] args) {
		for (int i = 1, j = 1; j <= 9; i++) {
			System.out.print(i+"*"+j+"="+i*j+"\t");
			if(i==j){
				i=0;
				j++;
				System.out.println();
			}
		}

	}

}
