package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月4日 上午11:25:21
* 
*/

public class BinarySearchTree {
	private Node root;
	private int sum;
	
	public BinarySearchTree(){
		root = null;
		sum = 0;
	}
	
	/*
	 * 插入节点
	 */
	public void insert(int insertNum){
		if(root == null){      //当树的根节点为空时，直接作为根节点插入
			root = new Node(insertNum,null);
			sum++;
			return;
		}
		Node current = root;
		Node dad = null;
		while(true){
			if(current == null){         //当当前位置的节点为空时，插入到该节点
				current = new Node(insertNum,dad);
				sum++;
				if(insertNum >= dad.value){			//并维护父节点的左右指针指向
					dad.right = current;
				}else{
					dad.left = current;
				}
				break;
			}else{
				dad = current;
				if(insertNum >= current.value){			//当当前位置的节点不为空时，继续往下比较直到找到空节点
					current = current.right;
				}else{
					current = current.left;
				}
			}
		}
	}
	
	/*
	 * 查询
	 */
	public boolean search(int num){
		Node current = root;				//从根节点往下查找
		while(true){
			if(current == null){		//当往下直到空节点都没有找到值时，退出循环
				break;
			}else{
				if(current.value == num){		//当找到时
					return true;
				}else{
					if(num > current.value){    //当当前节点值不相等时，继续往下走
						current = current.right;
					}else{
						current = current.left;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * 删除
	 */
	public boolean delete(int num){
		Node current = root;
		while(true){
			if(current == null){     //当找不到该值时，退出循环
				break;
			}else{
				if(current.value == num){		//找到时
					Node delete = current;
					while(true){		//继续找前驱或者后继
						if(delete.left == null  && delete.right == null){	//当没有子节点时，直接退出循环删除节点
							break;
						}else{
							if(delete.left != null){		//当有左子节点时，寻找前驱
								delete = delete.left;
								while(delete.right != null){	//寻找左子树中的最大值
									delete = delete.right;
								}
							}
							if(delete.right != null){		//当有右子节点时，寻找后继
								delete = delete.right;
								while(delete.left != null){			//寻找右子树中的最小值
									delete = delete.left;
								}
							}
						}
					}
					current.value = delete.value;		//先将找到的替换节点的值替换给当前节点，然后再删除掉替换节点
					if(delete.dad.right != null && delete.dad.right.value == delete.value){		//当替换节点为前驱时
						if(delete.left != null){			//当前驱有左子节点时，把前驱的父节点指向其左子节点
							delete.dad.right = delete.left;
							delete.left.dad = delete.dad;
						}else{							//当前驱没有左子节点时，直接删除前驱
							delete.dad.right = null;							
						}
					}else{					//当替换节点为后继时，操作与为前驱时类似
						if(delete.right != null){
							delete.dad.left = delete.right;
							delete.right.dad = delete.dad;
						}else{
							delete.dad.left = null;
						}
					}
					sum--;
					return true;
				}else{
					if(num > current.value){
						current = current.right;
					}else{
						current = current.left;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * 获取整棵树
	 */
	public String getTree(){
		Node current = root;
		StringBuffer s = new StringBuffer();
		get(s, current);
		return s.toString();
	}
	
	/*
	 * 用于获取树的递归方法
	 */
	private void get(StringBuffer s, Node n) {
		if(n.left != null){
			get(s,n.left);
		}
		if(n.right != null){
			get(s,n.right);
		}
		s.append(n.value + "  ");
	}
	
	/*
	 * 获取根
	 */
	public int getRoot(){
		return root.value;
	}
	
	/*
	 * 获取节点总数
	 */
	public int getSum(){
		return sum;
	}

	/* 
	 * 内部节点类
	 */
	class Node{
		private int value;
		private Node left;
		private Node right;
		private Node dad;
		
		Node(int value, Node dad) {
			this.value = value;
			this.dad = dad;
			this.left = null;
			this.right = null;
		}
	}
	
}
