package com.seaboat.dsa.heap;

/**

* @author awang
* @version 1.0
* @date 2019年4月3日 下午6:43:19
* 
*/

public class TestBinomialHeap {

	public static void main(String[] args) {
		BinomialHeap b = new BinomialHeap();
		b.insert(24);
		b.insert(23);
		b.insert(22);
		b.insert(16);
		b.insert(11);
		b.insert(19);
		b.insert(12);
		b.insert(10);
		b.insert(34);
		b.insert(21);
		int[] a = b.getArray();
		System.out.println("Min:" + b.getMin());
		System.out.print("Array:	");
		for(int i =0; i < a.length; i++){
			System.out.print(a[i] + "	");
		}
		System.out.println();
		System.out.println("Length:	" + b.getLength());
		System.out.println("----------------------");
		System.out.print("DeleteMin's Array:	");
		b.deleteMin();
		for(int i =0; i < a.length; i++){
			System.out.print(a[i] + "	");
		}
		System.out.println();
		System.out.println("Length:	" + b.getLength());
	}
}
