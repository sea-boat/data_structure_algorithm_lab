package com.seaboat.dsa.tree;

/**

* @author awang
* @version 1.0
* @date 2019年5月22日 下午2:38:26
* 
*/

public class TrieTree {

	private Node root;
	
	public TrieTree(){
		this.root = new Node(null);
	}
	
	/*
	 * 插入字符串
	 */
	public boolean insert(String word){
		char ch;
		Node current = root;
		for(int i = 0; i < word.length(); i++){			//从插入字符串的第一个字符开始陆续进行插入
			ch = word.charAt(i);
			for(int j = 0; j < current.chars.length; j++){
				if(current.chars[j] == null){			//当在当前节点内找不到对应字符时，在节点内插入新的字符
					current.chars[j] = new character(ch);
					if(i == word.length() - 1){			//当当前字符为字符串最后一个时，把该字符的单词标记改成true
						current.chars[j].end = true;
					}
					current.chars[j].down = new Node(current);
					current = current.chars[j].down;
					break;
				}
				if(current.chars[j].value == ch){		//当在当前节点内找到对应字符时
					if(i == word.length() - 1){			//当当前字符为字符串最后一个时，把该字符的单词标记改成true
						current.chars[j].end = true;
					}
					current = current.chars[j].down;
					break;
				}
			}
		}
		return true;
	}
	
	/*
	 * 查询字符串
	 */
	public boolean search(String word){
		char ch;
		Node current = root;
		for(int i = 0; i < word.length(); i++){		//对字符串的字符一个一个字符逐个进行查找
			ch = word.charAt(i);
			for(int j = 0; j < current.chars.length; j++){		//对当前节点内的所有字符进行比较，查找是否有相应的字符
				character c = current.chars[j];
				if(c == null){				//当找不到相同字符时，返回false
					return false;
				}
				if(c.value == ch){			//当找到相同字符时
					if(i == word.length() - 1 && c.end){		//当当前字符为最后一个字符且单词标记为true时
						return true;
					}
					current = c.down;		//否则继续往下寻找
					break;
				}
			}
		}
		return false;
	}
	
	/*
	 * 删除字符串
	 */
	public boolean delete(String word){
		char ch;
		Node current = root;
		for(int i = 0; i < word.length(); i++){			//查找字符串的字符
			ch = word.charAt(i);
			for(int j = 0; j < current.chars.length; j++){		//在各个节点中查找对应的字符
				character c = current.chars[j];
				if(c == null){						//当找不到时返回false
					return false;
				}
				if(c.value == ch){				//当找到时
					if(i == word.length() - 1 && c.end){	//若是字符串最后一个字符，且单词标志为true时，开始进行删除操作
						if(c.down != null && c.down.chars[0] != null){		//当最后字符所在节点还有子节点时，直接把单词标记改为false，然后退出方法
							current.chars[j].end = false;
							return true;
						}
						for(int k = word.length() - 1; k >= 0; k--){		//否则从当前往上找，直到找到拥有多个字符的节点或者单词标记为true的节点
							ch = word.charAt(k);
							if(current.chars[1] != null){		//当当前节点拥有多个字符时
								for(int l = 0; l < current.chars.length; l++){
									if(current.chars[l].value == ch){
										current.chars[l] = null;
										int a = l;
										while(current.chars[a+1] != null){
											current.chars[a] = current.chars[++a];
										}
										return true;
									}
								}
							}else{			//当当前节点只有一个字符时
								current = current.dad;
								if(k == 0){			//若是找不到根节点时仍找不到拥有多个字符的节点或者单词标记为true的节点时，把根节点重置
									root = new Node(null);
									return true;
								}
								if(current.chars[0].value == word.charAt(k - 1)){
									if(current.chars[0].end){		//当当前节点拥有单词标志时
										current.chars[0].end = false;
										current.chars[0].down = new Node(null);
										return true;
									}
								}
							}
						}
						return true;
					}
					// 当当前字符不是最后一个字符时，继续往下寻找后续字符
					current = c.down;
					break;
				}
			}
		}
		return false;
	}
	
	public void getResult(){
		this.get(root);
		System.out.println();
	}
	private void get(Node node){
		for(int i = 0; i < node.chars.length; i++){
			if(node.chars[i] == null){
				break;
			}
			if(node.chars[i].down != null){
				get(node.chars[i].down);
			}
			System.out.print(node.chars[i].value + "  ");
		}
	}
	
	/*
	 * 字母对象
	 */
	class character{
		char value;
		boolean end;
		Node down;
		
		public character(char value){
			this.value = value;
			this.end = false;
			this.down = null;
		}
	}
	/*
	 * 节点对象
	 */
	class Node{
		character[] chars;
		Node dad;
		
		public Node(Node dad){
			this.chars = new character[26];
			this.dad = dad;
		}
	}
}
