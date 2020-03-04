package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月21日 下午7:35:34
* 
*/

public class LSDRadixSortByCountingSort {

	private int[] count;      //计数数组
	private int[] store;      //存储数组
	private int bit = 0;      //最大位数
	private int radix = 0;
	
	public void sort (int[] a, int radix){
		
		int max = a[0];   //最大值
		int bitValue = 0;      //对应位上的值
		count = new int[radix];          //通过进制数对计数数组初始化
		store = new int[a.length];         //通过待排序数组长度对存储数组初始化
		this.radix = radix;
		
		for (int i = 1; i < a.length; i++){    //寻找最大值
			if(a[i] > max){
				max = a[i];
			}
		}
		
		for(int i = 1; i > 0; i++){        //计算最大值的位数
			if(max/(int)Math.pow(radix, i) == 0){
				bit = i;
				break;
			}
		}
		
		for(int i = 0; i <= bit - 1; i++){     //从低位到高位，分别通过各待排序元素不同的位上的值对其进行排序
			for(int j = 0; j < a.length; j++){
				bitValue = a[j];
				bitValue = findBitValue(bitValue, i);    //取相应位上的值
				count[bitValue]++;        //通过计数数组记录待排序中所有元素相应位上的值
			}
			for(int j = 1; j < radix; j++){       //计算计数数组的前缀和
				count[j] = count[j] + count[j-1];
			}
			for(int j = a.length - 1; j >= 0; j--){     //把待排序元素通过对应位上的值排好序后存到store数组
				bitValue = a[j];
				bitValue = findBitValue(bitValue, i);   //取相应位上的值
				store[--count[bitValue]] = a[j];        //通过位上的值把元素存到store数组中
			}
			for(int j = 0; j < radix; j++){     //每次对相应位上的值排好序后把计数数组重置
				count[j] = 0;
			}
			for(int j = 0; j < a.length; j++){   //把每次排好序后的所有元素重新存进待排序数组a，进入下一轮更低位的排序
				a[j] = store[j];
			}
		}
	}
	
	private int findBitValue(int v, int low){    //计算待排序中每个元素相应位上的值
		for(int i = bit - 1; i >= low; i--){      
			if(i != low){
				v = v%(int)Math.pow(radix, i);
			}else{
				v = v/(int)Math.pow(radix, i);
			}
		}
		return v;
	}
}
