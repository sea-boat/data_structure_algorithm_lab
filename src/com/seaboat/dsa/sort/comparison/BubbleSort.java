package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:42:28
* 
*/

public class BubbleSort extends SortMethod{
	
	private int[] array;  
	private int change;   //交换时的中间变量

	public BubbleSort(int[] array){
		this.array = array;
	}
	
	public void sort() {
		for(int i=array.length; i>1; i--) {       //一共length-1轮
			//每一轮都从第一个元素开始轮流与后面一个元素比较，当前面的元素比较大时就与后面一个元素换值
			for(int j=0; j<i-1; j++){      //每经过一轮减少一个元素进入轮流比较环节        
				if(array[j]>array[j+1]){
					change = array[j];
					array[j] = array[j+1];
					array[j+1] = change;
				}
			}
		}
	}
}
