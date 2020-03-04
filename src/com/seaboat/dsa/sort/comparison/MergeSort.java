package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:43:26
* 
*/

public class MergeSort {

	private int[] a;
	private int[] b;
	private int time=1;
	
	public MergeSort(int[] a){
		this.a = a;
		this.b = new int[a.length];
	}
	
	public void merge(int left, int right){
		
		int index = left;
		int l = (left+right)/2;
		boolean findout = false;
		
		time++;
		for(int i = left; i <= (left+right)/2; i++){
			if(findout){                     //当右边数组寻找完直接把左边数组的元素全部装入b中
				for(int k = i; k <= (left+right)/2; k++){
					b[index++] = a[k];
				}
				break;
			}
			for(int j = l + 1; j <= right; j++){
				if(a[j] < a[i]){     //当右边元素比较小时，把右边元素装入b中，并把j加1，再把左右两边元素进行比较。
					b[index++] = a[j];
					l++;
					if(j == right){
						findout = true;
						i--;        //由于当j寻找完时，左边的i并没有被取，所以得让外面层循环的i保持当前值。
					}
				}else{
					b[index++] = a[i];
					if(i == (left+right)/2){          //当左边数组寻找完直接把右边数组的元素全部装入b中
						for(int k = j; k <= right; k++){
							b[index++] = a[k];
						}
					}
					break;
				}
			}
		}
		for(int i = left; i <= right; i++){
			a[i] = b[i];
		}
	}
	
	public void sort(int left, int right){
		
		if(left < right){
			sort(left, (left+right)/2); 
			sort((left+right)/2 + 1, right);      //先执行完这两行的“分”后才会执行下面行的“合”
			merge(left,right);
		}
		System.out.println("time:" + time);

	}	

//	    public static void merge(int[]a,int low,int mid,int high){//对两组已经排序的数组进行合并
//	        int[]b=new int[high-low+1]; //临时数组，存储个数为high - low + 1个数据
//	        int s=low;
//	        int t=mid+1;
//	        int k=0;
//	        while(s<=mid&&t<=high){   //直至前半部或后半部数据完全录入暂存
//	            if(a[s]<=a[t])        //如果前半部的数据小于后半部的，前半部数据暂存
//	                b[k++]=a[s++];
//	            else                   //否则后半部数据暂存，并下标自加
//	                b[k++]=a[t++];
//	        }
//	        while(s<=mid)
//	            b[k++]=a[s++];
//	        while(t<=high)
//	            b[k++]=a[t++];
//	        for(int i=0;i<b.length;i++){     //将暂存的数据重新填充至array[low]--array[high]中
//	            a[low+i]=b[i];
//	        }
//	    }
//	    public static void mergesort(int a[],int low,int high){//对数组进行递归排序
//	        int mid;
//	        if(low<high){
//	            mid=(low+high)/2;
//	            mergesort(a,low,mid);
//	            mergesort(a,mid+1,high);
//	            merge(a,low,mid,high);
//	        }
//	    }
	
}
