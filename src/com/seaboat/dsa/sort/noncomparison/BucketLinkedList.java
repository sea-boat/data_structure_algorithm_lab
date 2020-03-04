package com.seaboat.dsa.sort.noncomparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月23日 上午10:44:17
* 
*/

public class BucketLinkedList {

	private Node head;
	private int size;
	
	public BucketLinkedList() {
		size = 0;
		head = null;
	}
	
	private class Node{
		
		private int data;
		private Node next;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	public void insert(int data) {       //有序插入
		Node node = new Node(data);
		Node previous = null;
		Node current = null;
		
		if(head == null){
			head = node;
			size++;
		}else{
			current = head;
			//此处把data=current.data的情况考虑进去是为了保证数组的稳定性,后进来的等于current的值也排在current后面
			while(current != null && data >= current.data){
				previous = current;
				current = current.next;
			}
			if(current == head){    //当插入数据小于头对象数据时，直接插在链表头端
				node.next = head;
				head = node;
				size++;
			}else{                //其他就插入链表中间
				previous.next = node;
				node.next = current;
				size++;
			}
		}
	}
	
	public int getHead(){
		return head.data;
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean deleteHead(){
		if(head == null){
			return false;
		}else{
			head = head.next;      //把头对象的下一对象设为头对象，这样原来的头对象就被舍弃
			size--;
			return true;
		}
	}
}
