package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月19日 上午1:22:59
* 
*/

public class Test23Tree {

	public static void main(String[] args){
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(6);
		t.insert(7);
		t.insert(4);
		t.insert(5);
		t.insert(8);
		t.insert(9);
		System.out.println(t.getSum());
		System.out.println(t.getTree());
		System.out.println(t.search(9));
		System.out.println(t.delete(4));
		System.out.println(t.search(3));
		System.out.println(t.getTree());
	}
}
