package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月13日 下午7:54:33
* 
*/

public class Main {

	public static void main(String[] args) {
		int[] a = {99,77,34,25,66,22};
		
		SelectMethod s = new SelectMethod(a);
//		s.sort("SelectionSort");
//		s.sort("BubbleSort");
//		s.sort("InsertionSort");
//		s.sort("QuickSort");
//		s.sort("ShellSort");
		s.sort("MergeSort");
		
		for(int i = 0; i < a.length; i++){

		System.out.println(a[i]);
		
		}
		
	}
	
}
