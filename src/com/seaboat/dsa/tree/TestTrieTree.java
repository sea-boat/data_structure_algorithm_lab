package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月22日 下午3:47:31
* 
*/

public class TestTrieTree {

	public static void main(String[] args){
		TrieTree t = new TrieTree();
		t.insert("awang");
		t.insert("awan");
		t.getResult();
		System.out.println(t.search("awang"));
		System.out.println(t.search("awan"));
		System.out.println(t.delete("awang"));		
		System.out.println(t.search("awang"));
		t.getResult();
	}
}
