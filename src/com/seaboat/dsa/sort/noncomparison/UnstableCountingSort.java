package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月21日 上午9:25:48
* 
*/

public class UnstableCountingSort {

	private int[] b;
	
	public void sort (int[] a){
		
		int min = a[0];
		int max = a[0];
		int index = 0;
		
		for(int i = 1; i < a.length; i++){   //获取待排序数组的最大值与最小值
			if(a[i] > max){
				max = a[i];
			}
			if(a[i] < min){
				min = a[i];
			}
		}
		
		b = new int[max-min+1];           //通过最大值与最小值来初始化计数数组
		
		for(int i = 0; i< a.length; i++){     //通过待排序数组为计数数组赋值
			b[a[i]-min]++;
		}
		
		for(int i = 0; i < max-min+1; i++){     //通过遍历计数数组来获取排序好的各元素
			if(b[i] != 0){
				a[index++] = i + min;
				b[i]--;
				i--;
			}
		}
		
	}
	
}
