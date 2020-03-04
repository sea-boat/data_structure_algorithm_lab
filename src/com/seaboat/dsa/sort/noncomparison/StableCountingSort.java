package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月21日 上午9:28:07
* 
*/

public class StableCountingSort {

	private int[] b;
	private int[] c;
	
	public int[] sort (int[] a){
		
		int min = a[0];
		int max = a[0];
		c = new int[a.length];
		
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
		
		for(int i = 1; i < max-min+1; i++){   //计算计数数组的前缀和
			b[i] = b[i] + b[i-1];
		}
		
		for(int i = a.length-1; i >= 0; i--){    //从待排序数组后面往前分别把元素插入到数组c中对应的位置
			//取出计数数组中对应的值并把它减1作为数组c的指针
			c[--b[a[i]-min]] = a[i]; 
			//由于把待排序元素的值赋值到数组c后还要把计数数组中对应的元素值减1，所以把--放在前面
		}
		
		return c;
		
	}
	
}
