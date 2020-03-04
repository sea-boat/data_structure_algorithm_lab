package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月21日 上午9:41:24
* 
*/

public class Main {
	
	public static void main(String[] args) {
	
		int[] a = {99, 32, 44, 75, 56, 44, 23, 21};
		
//		UnstableCountingSort s = new UnstableCountingSort();
//		s.sort(a);
//		StableCountingSort s = new StableCountingSort();
//		a = s.sort(a);
//		LSDRadixSortByCountingSort s = new LSDRadixSortByCountingSort();
//		s.sort(a, 10);
//		LSDRadixSortByBucket s = new LSDRadixSortByBucket();
//		s.sort(a, 10);
//		MSDRadixSortBySocket s = new MSDRadixSortBySocket();
//		s.sort(a, 10);
//		BucketSortByArray s = new BucketSortByArray();
//		s.sort(a);
		BucketSortByLinkedList s = new BucketSortByLinkedList();
		s.sort(a);
		
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
	}
	
}
