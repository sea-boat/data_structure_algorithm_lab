package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月4日 下午12:58:45
* 
*/

public class TestBST {

	public static void main(String[] args){
		BinarySearchTree t = new BinarySearchTree();
		t.insert(2);
		t.insert(5);
		t.insert(4);
		t.insert(3);
		System.out.println(t.getRoot());
		System.out.println(t.delete(2));
		System.out.println(t.getSum());
		System.out.println(t.getTree());
		System.out.println(t.search(3));
	}
	
}
