package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月23日 上午11:08:44
* 
*/

public class BucketSortByLinkedList {
	
	private BucketLinkedList bucket = new BucketLinkedList();

	public void sort(int[] a){
		int max = a[0];
		int min = a[0];
		int average = 0;
		int index = 0;
		int size = 0;
		int[] b = new int[a.length];
		
		b[0] = a[0];
		for(int i = 1; i < a.length; i++){
			b[i] = a[i];
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
		for(int i = 0; i < 4; i++){    //分成四次把数据存进链表，每组数据用完后都把链表清空，然后下一轮重新使用同一链表
			for(int j = 0; j < a.length; j++){  
				if(a[j] >= min + i * average && a[j] < min + ( i + 1 ) * average){
					bucket.insert(a[j]);
				}
			}
			size = bucket.getSize();
			if(size != 0){
				for(int k = 0; k < size; k++){
					b[index++] = bucket.getHead();
					bucket.deleteHead();
				}
			}
		}
		for(int i = 0; i < a.length; i++){
			a[i] = b[i];
		}
	}
}
