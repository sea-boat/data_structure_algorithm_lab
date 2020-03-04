package com.seaboat.dsa.sort.noncomparison;

import com.seaboat.dsa.sort.comparison.QuickSort;

/**

* @author awang
* @version 1.0
* @date 2019年3月22日 下午7:16:44
* 
*/

public class BucketSortByArray {

	private int[] bucket; 
	
	public void sort(int[] a){
		int max = a[0];
		int min = a[0];
		int average = 0;
		int count = 0;             //每个桶中的元素数
		int index = 0;
		
		for(int i = 1; i < a.length; i++){
			if(a[i] == 0){                          //当遇到元素为0时，先把它调到数组前端，然后下面对0后子序列继续排序
				a[index] = a[index] + a[i];
				a[i] = a[index] - a[i];
				a[index] = a[index] - a[i];
				index++;
			}
			if(max < a[i]){
				max = a[i];       //找最大值
			}
			if(min > a[i]){
				min = a[i];     //找最小值
			}
		}
		average = (max-min)/4;           //规定每个桶内元素的取值范围，此处直接固定分成4个桶
		if(average*4 < max-min){
			average++;
		}
		bucket = new int[a.length * 4];
		
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < 4; j++){
				if(a[i] >= min + j * average && a[i] < min + (j + 1) * average){   //当元素值在桶的取值范围内时
					for(int k = j * a.length; k < (j + 1) * a.length; k++){
						if(bucket[k] == 0){           //从桶的第一个元素开始存放待排序数组元素
							bucket[k] = a[i];
							break;
						}
					}
				}
			}
		}
		
		QuickSort q = new QuickSort(bucket);       //存放完后直接用桶数组去快速排序
		
		for(int i = 0; i < 4; i++){
			for(int j = i * a.length; j < (i+1) * a.length; j++){   //统计每个桶内的元素个数，用于限制快速排序的区间
				if(bucket[j] != 0){
					count++;
				}else{
					break;
				}
			}
			q.sort(i * a.length, i * a.length + count - 1);       //把每个桶区间内没有存放元素的区间去掉然后进行排序
			count = 0;
		}
		
		for(int i = 0; i < 4; i++){        //获取各桶内排好序后的元素
			for(int j = i * a.length; j < (i+1) * average; j++){
				if(bucket[j] != 0){
					a[index++] = bucket[j];
				}else{
					break;
				}
			}
		}
	}
	
}
