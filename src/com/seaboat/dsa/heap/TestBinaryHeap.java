package com.seaboat.dsa.heap;

/**

* @author awang
* @version 1.0
* @date 2019年4月1日 下午7:19:12
* 
*/

public class TestBinaryHeap {
	
	public static void main(String[] args) {
		
		BinaryHeap bh = new BinaryHeap();
		int[] b = {33,24,35,26,19,41};
		bh.insertNum(28);
		bh.insertNum(32);
		bh.insertNum(37);
		bh.insertNum(38);
		bh.insertNum(40);
		bh.deleteMin();
		bh.deleteMin();
		bh.insertArray(b);
		int[] a = new int[bh.getLength()];
		for(int i = 0; i < bh.getLength(); i++){
			a[i] = bh.getArray()[i];
			System.out.println(a[i]);
		}
	}

}
