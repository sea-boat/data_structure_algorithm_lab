package com.seaboat.dsa.heap;

/**

* @author awang
* @version 1.0
* @date 2019年4月8日 下午2:24:38
* 
*/

public class TestFibonacciHeap {

	public static void main(String[] args){
		FibonacciHeap fh = new FibonacciHeap();
		fh.insertTree(0, new int[]{5});
		fh.insertTree(1, new int[]{3,7});
		fh.insertTree(1, new int[]{5,7});
		fh.insertTree(1, new int[]{4,6});
		fh.insertTree(3, new int[]{5,6,6,7,6,7,7,8});
		FibonacciHeap fh2 = new FibonacciHeap();
		fh2.insertTree(0, new int[]{6});
		fh2.insertTree(0, new int[]{10});
		fh.mergeHeap(fh2);
		System.out.println("获取头树。。。。");
		System.out.println(fh.getHead());
		System.out.println("获取最小树。。。。");
		System.out.println(fh.getMin());
		fh.getAll(fh);
		System.out.println("删除最小节点。。。。");
		fh.deleteMinNode();
		fh.getAll(fh);
		System.out.println("减小节点值。。。。。");
		fh.decreaseNode(1, 5, 3);
		fh.decreaseNode(2, 9, 4);
		fh.getAll(fh);
		System.out.println("删除最小节点。。。。");
		fh.deleteMinNode();
		fh.getAll(fh);
		System.out.println("获取最小树。。。。");
		System.out.println(fh.getMin());
	}
	
}
