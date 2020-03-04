package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:41:48
* 
*/

public class SelectionSort extends SortMethod{
	
	private int[] array; 
	private int min;          //用来存放每轮的最小值的指针
	private int change;       //在交换值的时候使用的中间变量
	private int length;       //数组长度

	public SelectionSort(int[] array){
		this.array = array;
	}
	
	public void sort() {
		length = array.length;
		for(int i=length; i>1; i--) {      //一共length-1轮
			min = length-i;                    //先把未排序子序列第一个元素当成最小值
			for(int j=min; j<length-1; j++){        //每轮中的多次比较
				if(array[min]>array[j+1]){        //每个元素与最小值比较
					min = j+1;                 //当遇到比最小值还小的元素时把该元素指针设为最小值的指针
				}
			}
			 //每一轮比较完，所得出最后的最小值的指针后，把最小值与未排序子序列第一个元素对调
			change = array[length-i];      
			array[length-i] = array[min];
			array[min] = change;
		}
	}
}
