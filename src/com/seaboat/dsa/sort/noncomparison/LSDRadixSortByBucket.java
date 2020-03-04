package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月22日 上午9:22:53
* 
*/

public class LSDRadixSortByBucket {

	private int[] bucket;      //桶
	private int index = 0;       //待排序数组参加排序的开始指针
	
	public void sort(int[] a, int radix){
		
		int max = a[0];   //最大值
		int bitValue = 0;      //对应位上的值
		int bit = 0;            //位数
		int index2 = 0;
		bucket = new int[radix * a.length];      //用一个长度为进制数乘以数组长度的数组代替进制数个长度为数组长度的数组
		
		for(int i = 0; i < a.length; i++){
			if(a[i] == 0){                               //当遇到元素为0时，先把它调到数组前端，然后下面从0后继续排序
				a[index] = a[index] + a[i];
				a[i] = a[index] - a[i];
				a[index] = a[index] - a[i];
				index++;
			}
			if(a[i] > max){               //找最大值
				max = a[i];
			}
		}
		
		for(int i = 1; i > 0; i++){        //计算最大值的位数
			if(max/(int)Math.pow(radix, i) == 0){
				bit = i;
				break;
			}
		}
		
		for(int i = 0; i < bit; i++){            //从低位到高位
			index2 = index;
			for(int j = index2; j < a.length; j++){
				bitValue = a[j];
				for(int k = bit - 1; k >= i; k--){     //计算出对应位上的值
					if(k != i){
						bitValue = bitValue%(int)Math.pow(radix, k);
					}else{
						bitValue = bitValue/(int)Math.pow(radix, k);
					}
				}
				for(int k = a.length * bitValue; k < a.length * (bitValue+1); k++){ //把元素都放进桶数组中相应的位置
					if(bucket[k] == 0){
						bucket[k] = a[j];
						break;
					}
				}
			}
			for(int j = 0; j < radix; j++){     //从低区间开始查询桶中每个长度为数组长度的区间是否存在不等于0的元素
				for(int k = j * a.length; k < (j+1) * a.length; k++){
					if(bucket[k] == 0){      //当元素等于0时直接退出所属区间的查找
						break;
					}else{             //当元素不等于0时把元素值赋给待排序数组
						a[index2++] = bucket[k];
						bucket[k] = 0;
					}
				}
			}
		}
	}
}
