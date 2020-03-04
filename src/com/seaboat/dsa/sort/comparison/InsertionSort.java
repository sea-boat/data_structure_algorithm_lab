package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:42:45
* 
*/

public class InsertionSort {
	
	private int[] array;  
	private int insertValue;   //所要插入元素的值
	private int insertIndex;   //所要插入的位置指针
	
	public InsertionSort(int[] array){
		this.array = array;
	}
	
	public void sort(){
		
//		老哥的高级代码
//		for(i=1;i<a.length;i++) {
//			for(j=i;j>0;j--)
//				if(a[j-1]>a[j]) {
//					a[j-1]+=a[j]; 
//					a[j]=a[j-1]-a[j]; 
//					a[j-1]-=a[j]
//				}else{
//					break;
//				}
//		}
		
		try{
		
			for(int i = 1; i < array.length; i++){      //length-1次插入，其中i为所要插入元素的指针
			
				insertValue = array[i];
				insertIndex = 0;            //先假设已排序序列中不存在比插入元素小的元素
			
				for(int j = i-1; j >= 0; j--){           //从i-1开始往前分别与所要插入元素比较
					if(insertValue < array[j]){          //当有序子序列中的元素比插入元素小时，就把该元素推到后一位置
						array[j+1] = array[j];
					}else if(insertValue >= array[j]){  //当有序序列中元素大于等于插入元素时，把插入元素放到该位置后面
						insertIndex = j+1;           //把插入位置指针存到insertIndex中
						break;
					}else{
						throw new Exception("未知错误！");
					}
				}
				
				array[insertIndex] = insertValue;
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
