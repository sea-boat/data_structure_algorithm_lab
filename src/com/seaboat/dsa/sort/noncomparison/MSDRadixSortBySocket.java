package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月22日 上午10:12:28
* 
*/

public class MSDRadixSortBySocket {

	private int index = 0;       //待排序数组参加排序的开始指针
	private int bit = 0;            //位数
	private int radix = 0;
	
	public void sort(int[] a, int radix) {        //待排序数组，进制数，数组赋值元素数
		
		int max = a[0];   //最大值
		this.radix = radix;
		int[] a2 ;
		
		for(int i = 0; i < a.length; i++){
			if(a[i] == 0){                          //当遇到元素为0时，先把它调到数组前端，然后下面对0后子序列继续排序
				a[index] = a[index] + a[i];
				a[i] = a[index] - a[i];
				a[index] = a[index] - a[i];
				index++;
			}
			if(a[i] > max){               //找最大值
				max = a[i];
			}
		}
		
//		int maxL = String.valueOf(max).length();  //获取数组中最大元素长度
		for(int i = 1; i > 0; i++){        //计算最大值的位数
			if(max/(int)Math.pow(radix, i) == 0){
				bit = i;
				break;
			}
		}
		
		a2 = new int[a.length-index];      //用a中的除0外的元素进行排序
		for(int i = 0; i < a2.length; i++){
			a2[i] = a[index + i];
		}
		sort(a2, bit, a);
		
	}
	
	private void sort(int[] bucket, int nextBit, int[] a){
		int length = 0;
		int[] subBucket;
		
		for(int i = 0; i < bucket.length; i++){         //确定bucket中值的数
			if(bucket[i] != 0){
				length++;
			}else{
				break;
			}
		}
		if(nextBit == 0 || length == 1){         //当到最低位或者只有一个元素赋值时，把子桶的元素赋值给待排序数组
			for(int i = 0; i < length; i++){     
				a[index++] = bucket[i];
			}
		}else{
			int bitValue = 0;
			subBucket = new int[length*radix];
			int nextIndex = 0;
			int[] next = new int[length]; 
			
			for(int i = 0; i < length; i++){
				bitValue = bucket[i];
				for(int j = bit - 1; j >= nextBit - 1; j--){     //计算出对应位上的值
					if(j != nextBit - 1){
						bitValue = bitValue%(int)Math.pow(radix, j);
					}else{
						bitValue = bitValue/(int)Math.pow(radix, j);
					}
				}
				
				for(int j = length * bitValue; j < length * (bitValue+1); j++){ //把元素都放进子桶数组中相应的位置
					if(subBucket[j] == 0){
						subBucket[j] = bucket[i];
						break;
					}
				}
			}
			
			for(int i = 0; i < radix; i++){        //遍历子桶所有区间，若存在元素则继续对次高位基数排序
				if(subBucket[length * i] != 0){
					for(int j = length * i; j < length * (i+1); j++){
						if(subBucket[j] != 0){
							next[nextIndex++] = subBucket[j];    //把某个存在元素的区间的所有元素赋给next
						}
					}
					sort(next, nextBit - 1, a);
					for(int j = 0; j < length; j++){     //重置next数组
						if(next[j] == 0){
							break;
						}else{
							next[j] = 0;
						}
					}
					nextIndex = 0;
				}
			}
		}
	}
}
