package com.lxb;

public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		 int temp=0;
	        int i=0,j=0;
	        int[] result=new int[2];
	        for( i=0;i<nums.length;i++){
	            temp=target-nums[i];
	            for( j=i+1;j<nums.length;j++){
	                if(temp==nums[j]){
	                   result[0]=i;
	                    result[1]=j;
	                    return result;
	                }
	            }
	        }
	        return result;
	}

	public static void main(String[] args) {

	}

}
