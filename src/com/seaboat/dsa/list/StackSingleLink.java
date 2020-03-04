package com.seaboat.dsa.list;

/**

* @author awang
* @version 1.0
* @date 2019年3月23日 上午9:47:50
* 
* @ 单向链表实现栈
*/

public class StackSingleLink {
    private SingleLinkedList link;
    
    public StackSingleLink(){
        link = new SingleLinkedList();
    }
    
    //添加元素
    public void push(Object obj){
        link.addHead(obj);
    }
    
    //移除栈顶元素
    public Object pop(){
        Object obj = link.deleteHead();
        return obj;
    }
    
    //判断是否为空
    public boolean isEmpty(){
        return link.isEmpty();
    }
    
    //打印栈内元素信息
    public void display(){
        link.display();
    }

}