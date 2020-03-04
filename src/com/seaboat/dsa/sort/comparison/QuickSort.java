package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:42:59
* 
*/

public class QuickSort extends SortMethod{

	private int[] array;
	private int change;
	private int time = 1;
	
	public QuickSort(int[] array) {
		this.array = array;
	}
	
	public int sort(int left, int right) {       //left是排序区间的第一个指针，right是最后一个指针
		int findMin = right;
		if(left == right) {      //当快速排序的区间第一个和最后一个指针相同的时候不操作。也就是区间内只有一个元素的时候
			return time;
		} else {
			for(int i = left + 1; i <= right; i++) {      //从基准数往后开始寻找大于等于基准数的数
				if(array[i] > array[left]) {
					for(int j = findMin ; j >= i; j--) {  //当找到比基准数小的数时开始从最后指针往前寻找比基准数小的数
						if(j==i){            	//当j与i相等时
							if(i == left + 1){			//当没有找到过比基准数小的时候，直接把区间左坐标加1再排序
								return sort(left+1, right) + 1;  
							}else{					//当找到过时就用i==j时的指针把区间分成两半分别进行排序
								return sort(left, j-1) + sort(j+1, right);
							}
						}
						if(array[j] < array[left]) {   //当找到比基准数小的数时，把指针为i与j的元素对换
							change = array[i];
							array[i] = array[j];
							array[j] = change;
							findMin = j-1;         //然后下次从j-1开始往前寻找比基准数小的元素
							break;
						}
					}
				}
				if(i == right  && array[left] > array[i] ){  //当没有找到比基准数大的元素时
					change = array[left];          //直接首尾元素对换
					array[left] = array[right];
					array[right] = change;
					return sort(left,right-1);      //然后再把区间右指针减1再排序
				}
			}
			return time-1;
		}
		
	}
	
}
