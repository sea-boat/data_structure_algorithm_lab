package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月5日 下午6:43:44
* 
*/

public class TestAVL {
	
	public static void main(String[] args){
		AVLTree t = new AVLTree();
		t.insert(2);
		t.insert(5);
		t.insert(4);
		t.insert(3);
		System.out.println(t.getRoot());
		System.out.println(t.delete(5));
		System.out.println(t.getSum());
		System.out.println(t.getTree());
		System.out.println(t.search(3));
	}
}
