package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:43:13
* 
*/

public class ShellSort {

	private int[] a;
	
	public ShellSort(int[] a) {
		this.a = a;
	}
	
//	希尔排序通过插入排序实现，插入排序的代码其实就是希尔排序增量为1时的代码。
//	所以只需把插入排序的改变量从1改成不同值就行了。
//	此方法的第一个增量是数组最后指针/2，若是把所有increment改成increment-1的话第一个增量就是数组长度/2
	public void sort(int increment) {
		for(int i = 0; i < increment; i++){    //通过所设定增量限制每组第一个元素指针范围
			for(int j = i + increment; j < a.length; j = j + increment) {   //从第2个开始循环获取每组的后面元素
				for(int k = j; k > i; k = j - increment)     //每组后面的元素分别与已排序序列比较，确定插入位置
					if(a[k-increment] > a[k]) {
						a[k-increment] += a[k]; 
						a[k] = a[k-increment] - a[k]; 
						a[k-increment] -= a[k];
					}else{
						break;
					}
			}
		}
		if(increment != 1){
			sort(increment/2);
		}
	}
	
}
