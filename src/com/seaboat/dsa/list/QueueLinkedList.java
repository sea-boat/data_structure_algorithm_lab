package com.seaboat.dsa.list;

/**

* @author awang
* @version 1.0
* @date 2019年3月23日 上午9:49:14
* 
* @ 双端链表实现队列
* 
*/

public class QueueLinkedList {
    
    private DoublePointLinkedList dp;
    
    public QueueLinkedList(){
        dp = new DoublePointLinkedList();
    }
    public void insert(Object data){
        dp.addTail(data);
    }
    
    public void delete(){
        dp.deleteHead();
    }
    
    public boolean isEmpty(){
        return dp.isEmpty();
    }
    
    public int getSize(){
        return dp.getSize();
    }
    
    public void display(){
        dp.display();
    }
    
}