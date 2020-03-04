package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月10日 下午2:09:53
* 
*/

public class TwoThreeTree {

	private Node root;
	private int sum;
	
	public TwoThreeTree(){
		root = null;
		sum = 0;
	}
	
	/*
	 * 查询
	 */
	public boolean search(int num){
		Node current = root;				//从根节点往下查找
		if(num == 0){
			return false;
		}
		while(true){
			if(current == null){		//当往下直到空节点都没有找到值时，退出循环
				break;
			}else{
				if(current.firstValue == num || current.secondValue == num){		//当找到时
					return true;
				}else{
					current = downOne(current, num);
				}
			}
		}
		return false;
	}
	
	/*
	 * 插入节点，通过一棵树来存储分裂后所改变子树的结构
	 */
	public void insert(int insertNum){
		if(root == null){      //当树的根节点为空时，直接作为根节点插入
			root = new Node(insertNum,null);
			sum++;
			return;
		}
		Node current = root;
		while(true){
			if(hasSon(current)){			//当当前节点拥有子节点时，继续往下
				current = downOne(current, insertNum);
			}else{								//直到到达叶子节点
				if(current.firstValue > current.secondValue){		//当该叶子节点只有一个节点值时，直接插入值
					if(current.firstValue >= insertNum){
						current.secondValue = current.firstValue;
						current.firstValue = insertNum;
					}else{
						current.secondValue = insertNum;
					}
				}else{				//当该叶子节点有两个节点值时，需要往上分解
					Node cache = null;				//通过该缓存节点来暂时存储分裂节点后所构成的树
					if(insertNum < current.firstValue){			//第一次初始化缓存节点
						cache = new Node(current.firstValue, null);
						cache.left = new Node(insertNum, cache);
						cache.right = new Node(current.secondValue, cache);
					}else if(insertNum > current.secondValue){
						cache = new Node(current.secondValue, null);
						cache.left = new Node(current.firstValue, cache);
						cache.right = new Node(insertNum, cache);
					}else{
						cache = new Node(insertNum, null);
						cache.left = new Node(current.firstValue, cache);
						cache.right = new Node(current.secondValue, cache);
					}
					while(true){
						if(current == root && current.firstValue <= current.secondValue){        //当当前为根节点时
							root = cache;
							break;
						}
						if(current.dad.firstValue > current.dad.secondValue){	//当当前节点的父节点只有一个值时，缓存树可以存为父节点的子树中
							
//							记得维护缓存树中各节点的父节点属性
							
							if(current == current.dad.left){
								current.dad.secondValue = current.dad.firstValue;
								current.dad.firstValue = cache.firstValue;
								current.dad.left = cache.left;
								cache.left.dad = current.dad;
								current.dad.mid = cache.right;
								cache.right.dad = current.dad;
							}else{
								current.dad.secondValue = cache.firstValue;
								current.dad.mid = cache.left;
								cache.left.dad = current.dad;
								current.dad.right = cache.right;
								cache.right.dad = current.dad;
							}
							break;
						}
						if(current.dad.secondValue >= current.dad.firstValue){		//当当前节点的父节点拥有两个值时，继续往上维护缓存树。
							Node cache2 = null;
							if(current.dad.left == current){
								cache2 = new Node(current.dad.firstValue, null);
								cache2.left = cache;
								cache.dad = cache2;
								cache2.right = new Node(current.dad.secondValue, cache2);
								cache2.right.left = current.dad.mid;
								current.dad.mid.dad = cache2.right;
								cache2.right.right = current.dad.right;
								current.dad.right.dad = cache2.right;
							}else if(current.dad.mid == current){
								cache2 = new Node(cache.firstValue, null);
								cache2.left = new Node(current.dad.firstValue, cache2);
								cache2.right = new Node(current.dad.secondValue, cache2);
								cache2.left.left = current.dad.left;
								current.dad.left.dad = cache2.left;
								cache2.left.right = cache.left;
								cache.left.dad = cache2.left;
								cache2.right.left = cache.right;
								cache.right.dad = cache2.right;
								cache2.right.right = current.dad.right;
								current.dad.right.dad = cache2.right;
							}else{
								cache2 = new Node(current.dad.secondValue, null);
								cache2.right = cache;
								cache.dad = cache2;
								cache2.left = new Node(current.dad.firstValue, cache2);
								cache2.left.left = current.dad.left;
								current.dad.left.dad = cache2.left;
								cache2.left.right = current.dad.mid;
								current.dad.mid.dad = cache2.left;
							}
							cache = cache2;
							current = current.dad;
						}
					}
				}
				break;
			}
		}
		sum++;
	}
	
	/*
	 * 删除
	 */
	public boolean delete(int num){
		Node current = root;
		while(true){
			if(current == null){     //当找到叶子节点仍然找不到该值时，退出方法返回false
				return false;
			}else{
				if(current.firstValue == num || current.secondValue == num){		//找到时
					if(!hasSon(current)){		//当所要删除的值在叶子节点时
						if(current.firstValue <= current.secondValue){		//当要删除的值所在的节点有两个值时
							if(current.firstValue == num){
								current.firstValue = current.secondValue;
							}
							current.secondValue = 0;
							return true;
						}
					}else{			//当删除值在非叶子节点时
						Node change = findSuccessor(current, num);			//先找到前驱后继节点，优先选择拥有两个值的前驱后继节点
						String a = "successor";
						if(change.firstValue > change.secondValue){
							change = findPrecursor(current, num);
							a = "precursor";
						}
						if(change.firstValue <= change.secondValue){		//当前驱后继节点有两个值时
							if(a == "successor"){			//当是后继节点时
								if(current.firstValue == num){
									current.firstValue = change.firstValue;
								}else{
									current.secondValue = change.firstValue;
								}
								change.firstValue = change.secondValue;
								change.secondValue = 0;
							}else{						//当是前驱节点时
								if(current.firstValue == num){
									current.firstValue = change.firstValue;
								}else{
									current.secondValue = change.secondValue;
								}
								change.secondValue = 0;
							}
							return true;
						}else{									//当前驱后继节点只有一个值时
							if(current.firstValue == num){
								current.firstValue = change.firstValue;
							}else{
								current.secondValue = change.firstValue;
							}
							current = change;				//接着对前驱后继叶子节点进行删除操作
						}
					}
					
					//上面已经排除了最终删除的叶子节点拥有两个值的两种情况
					
					if(current.dad.mid != null && 
							current.dad.mid.firstValue <= current.dad.mid.secondValue){	//当所要删除的叶子节点的中间的兄弟节点拥有两个值时
						if(current.dad.left == current){
							current.firstValue = current.dad.firstValue;
							current.dad.firstValue = current.dad.mid.firstValue;
							current.dad.mid.firstValue = current.dad.mid.secondValue;
							current.dad.mid.secondValue = 0;
						}else{
							current.firstValue = current.dad.secondValue;
							current.dad.secondValue = current.dad.mid.secondValue;
							current.dad.mid.secondValue = 0;
						}
						return true;
					}
					
					if(current.dad.left.firstValue <= current.dad.left.secondValue){			//当所要删除的叶子节点的最左边的兄弟节点拥有两个值时
						if(current.dad.mid == null){
							current.firstValue = current.dad.firstValue;
							current.dad.firstValue = current.dad.left.secondValue;
							current.dad.left.secondValue = 0;
						}else{
							if(current == current.dad.mid){
								current.firstValue = current.dad.firstValue;
								current.dad.firstValue = current.dad.left.secondValue;
								current.dad.left.secondValue = 0;
							}else{
								current.firstValue = current.dad.secondValue;
								current.dad.secondValue = current.dad.mid.firstValue;
								current.dad.mid.firstValue = current.dad.firstValue;
								current.dad.firstValue = current.dad.left.secondValue;
								current.dad.left.secondValue = 0;
							}
						}
						return true;
					}
					
					if(current.dad.right.firstValue <= current.dad.right.secondValue){			//当所要删除的叶子节点的最右边的兄弟节点拥有两个值时
						if(current.dad.mid == null){
							current.firstValue = current.dad.firstValue;
							current.dad.firstValue = current.dad.right.firstValue;
							current.dad.right.firstValue = current.dad.right.secondValue;
							current.dad.right.secondValue = 0;
						}else{
							if(current == current.dad.mid){
								current.firstValue = current.dad.secondValue;
								current.dad.secondValue = current.dad.right.firstValue;
								current.dad.right.firstValue = current.dad.right.secondValue;
								current.dad.right.secondValue = 0;
							}else{
								current.firstValue = current.dad.firstValue;
								current.dad.firstValue = current.dad.mid.firstValue;
								current.dad.mid.firstValue = current.dad.secondValue;
								current.dad.secondValue = current.dad.right.firstValue;
								current.dad.right.firstValue = current.dad.right.secondValue;
								current.dad.right.secondValue = 0;
							}
						}
						return true;
					}
					
					//上面已经排除了所要删除的叶子节点的兄弟节点有两个值可借项的情况
					
					if(current.dad.firstValue <= current.dad.secondValue){		//当最终所要删除的叶子节点的父节点拥有两个值时
						if(current.firstValue <= current.dad.firstValue){
							current.dad.left = new Node(current.dad.firstValue, current.dad.mid.firstValue, current);
							current.dad.firstValue = current.dad.secondValue;
							current.dad.mid = null;
							current.dad.secondValue = 0;
						}else if(current.firstValue > current.dad.secondValue){
							current.dad.right = new Node(current.dad.mid.firstValue, current.dad.secondValue, current);
							current.dad.secondValue = 0;
							current.dad.mid = null;
						}else{
							current.dad.right = new Node(current.dad.secondValue, current.dad.right.firstValue, current);
							current.dad.secondValue = 0;
							current.dad.mid = null;
						}
						return true;
					}
					
					//上面排除了所要删除的叶子节点的父节点拥有两个值可合并的情况，下面就是排除上面所有情况的情况，待删除值所在节点及其兄弟节点、父节点都是单值
					
					Node cache = null;
					if(current == current.dad.left){
						cache = new Node(current.dad.firstValue, current.dad.right.firstValue, null);
					}else{
						cache = new Node(current.dad.left.firstValue, current.dad.firstValue, null);
					}
					if(current.dad == root){		//当待删除值所在节点的父节点为根节点时
						root = cache;
						return true;
					}
					if(current.dad.dad.firstValue > current.dad.dad.secondValue){		//当祖父节点为单值节点时
						if(current.dad.dad.right.firstValue > current.dad.dad.right.secondValue){		//当父节点的兄弟节点为单值节点时
							if(current.dad == current.dad.dad.left){		//当父节点为祖父节点的左子节点时
								current = current.dad.dad;
								current.left = cache;
								cache.dad = current;
								current.secondValue = current.right.firstValue;
								current.right.left.dad = current;
								current.mid = current.right.left;
								current.right.right.dad = current;
								current.right = current.right.right;
							}else{						//当父节点为祖父节点的右子节点时
								current = current.dad.dad;
								current.right = cache;
								cache.dad = current;
								current.secondValue = current.firstValue;
								current.firstValue = current.left.firstValue;
								current.left.right.dad = current;
								current.mid = current.left.right;
								current.left.left.dad = current;
								current.left = current.left.left;
							}
							return true;
						}else{				//当父节点的兄弟节点为双值节点时
							if(current.dad.dad.left == current.dad){
								current = current.dad.dad;
								current.left.left = cache;
								cache.dad = current.left;
								current.right.left.dad = current.left;
								current.left.right = current.right.left;
								current.left.firstValue = current.firstValue;
								current.firstValue = current.right.firstValue;
								current.right.firstValue = current.right.secondValue;
								current.right.secondValue = 0;
								current.right.left = current.right.mid;
								current.right.mid = null;
							}else{
								current = current.dad.dad;
								current.right.right = cache;
								cache.dad = current.right;
								current.left.right.dad = current.right;
								current.right.left = current.left.right;
								current.right.firstValue = current.firstValue;
								current.firstValue = current.left.secondValue;
								current.left.secondValue = 0;
								current.left.right = current.left.mid;
								current.left.mid = null;
							}
							return true;
						}
					}else{			//当祖父节点为双值时
						if(current.dad.dad.mid.mid != null){		//当祖父节点的中间节点为双值时
							if(current.dad == current.dad.dad.left){
								current = current.dad.dad;
								current.left.left = cache;
								cache.dad = current.left;
								current.mid.left.dad = current.left;
								current.left.right = current.mid.left;
								current.left.firstValue = current.firstValue;
								current.firstValue = current.mid.firstValue;
								current.mid.firstValue = current.mid.secondValue;
								current.mid.secondValue = 0;
								current.mid.left = current.mid.mid;
								current.mid.mid = null;
							}else{
								current = current.dad.dad;
								current.right.right = cache;
								cache.dad = current.right;
								current.mid.right.dad = current.right;
								current.right.left = current.mid.right;
								current.right.firstValue = current.secondValue;
								current.secondValue = current.mid.secondValue;
								current.mid.secondValue = 0;
								current.mid.right = current.mid.mid;
								current.mid.mid = null;
							}
							return true;
						}
								//当祖父节点的左右子节点都为双值时
						if(current.dad.dad.left.mid != null && current.dad.dad.right.mid != null){
							current = current.dad.dad;
							current.mid.right = cache;
							cache.dad = current.mid;
							current.left.right.dad = current.mid;
							current.mid.left = current.left.right;
							current.mid.firstValue = current.firstValue;
							current.firstValue = current.left.secondValue;
							current.left.secondValue = 0;
							current.left.right = current.left.mid;
							current.left.mid = null;
						}
						
						//排除了上面两种可能，就可以直接用祖父节点的两个相邻的单值子节点进行合并
						
						if(current.dad.dad.left == current.dad){
							current = current.dad.dad;
							current.mid.mid = current.mid.left;
							current.mid.left = cache;
							cache.dad = current.mid;
							current.mid.secondValue = current.mid.firstValue;
							current.mid.firstValue = current.firstValue;
							current.firstValue = current.secondValue;
							current.secondValue = 0;
							current.left = current.mid;
							current.mid = null;
						}
						
						if(current.dad.dad.right == current.dad){
							current = current.dad.dad;
							current.mid.mid = current.mid.right;
							current.mid.right = cache;
							cache.dad = current.mid;
							current.mid.secondValue = current.secondValue;
							current.secondValue = 0;
							current.right = current.mid;
							current.mid = null;
						}
						
						if(current.dad.dad.mid == current.dad){
							current = current.dad.dad;
							if(current.left.mid == null){
								current.left.mid = current.left.right;
								current.left.right = cache;
								cache.dad = current.left;
								current.left.secondValue = current.firstValue;
								current.firstValue = current.secondValue;
								current.mid = null;
							}else{
								current.right.mid = current.right.left;
								current.right.left = cache;
								cache.dad = current.right;
								current.right.secondValue = current.right.firstValue;
								current.right.firstValue = current.secondValue;
								current.secondValue = 0;
								current.mid = null;
							}
						}
					}
				}else{						//未找到时继续往下寻找
					current = downOne(current, num);
				}
			}
		}
	}
	
	/*
	 * 查找非叶子节点的前驱节点
	 */
	private Node findPrecursor(Node node, int value){
		Node current = null;
		if(value == node.firstValue){
			current = node.left;
		}else{
			if(node.firstValue <= node.secondValue){
				current = node.mid;
			}else{
				current = node.right;
			}
		}
		while(current.right != null){
			current = current.right;
		}
		return current; 
	}
	
	/*
	 * 查找非叶子节点的后继节点
	 */
	private Node findSuccessor(Node node, int value){
		Node current = null;
		if(value == node.firstValue){
			if(node.firstValue <= node.secondValue){
				current = node.mid;
			}else{
				current = node.left;
			}
		}else{
			current = node.right;
		}
		while(current.left != null){
			current = current.left;
		}
		return current;
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
		if(n.mid != null){
			get(s,n.mid);
		}
		if(n.right != null){
			get(s,n.right);
		}
		s.append(n.firstValue + "  ");
		if(n.secondValue != 0){
			s.append(n.secondValue + "  ");
		}
	}
	
	/*
	 * 检查是否存在子节点
	 */
	private boolean hasSon(Node node){
		if(node.left == null && node.mid == null && node.right == null){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * 用于往下走一层
	 */
	private Node downOne(Node current, int num){
		Node next = null;
		if(current.secondValue >= current.firstValue){      //有两个节点值时
			if(num < current.firstValue){    //当当前节点值不相等时，继续往下走
				next = current.left;
			}else if(num > current.firstValue && num < current.secondValue){
				next = current.mid;
			}else{
				next = current.right;
			}
		}else{
			if(num < current.firstValue){
				next = current.left;
			}else{
				next = current.right;
			}
		}
		return next;
	}
	
	/*
	 * 获取根
	 */
	public int getRoot(){
		return root.firstValue;
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
		private int firstValue;
		private int secondValue;
		private Node left;
		private Node mid;
		private Node right;
		private Node dad;
		
		Node(int firstvalue, Node dad) {
			this.firstValue = firstvalue;
			this.dad = dad;
			this.left = null;
			this.mid = null;
			this.right = null;
		}
		
		Node(int firstvalue, int secondValue, Node dad){
			this.firstValue = firstvalue;
			this.secondValue = secondValue;
			this.dad = dad;
			this.left = null;
			this.mid = null;
			this.right = null;
		}
	}
}
