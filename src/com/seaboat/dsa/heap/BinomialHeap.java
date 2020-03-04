package com.seaboat.dsa.heap;

/**

* @author awang
* @version 1.0
* @date 2019年4月2日 下午7:25:17
* 
* 0与负数不可作为元素
*/

public class BinomialHeap {

	private int[] heap;      //二项堆数组
	
	public BinomialHeap(){
		heap = new int[1];
	}
	
	/*
	 * 插入一个正整数
	 */
	public void insert(int a){
		int index = 0;                     //用来存储树的根节点的下标
		int[] store = new int[heap.length+1];      //当需要合并树时，作为存储数组
		store[0] = a;
		for(int i = 0; i >= 0; i++){
			if(heap[index] != 0){               //当树根节点不为0即该树不为空时
				if(heap[index] > store[0]){     //当树的根节点大于存储数组的树的根节点时
					//直接把堆中树的节点存到存储数组中组合树的后面并把堆的树初始化
					int c = (int)Math.pow(2, i);
					for(int j = index; j < sumNode(i); j++){
						store[c++] = heap[j];
						heap[j] = 0;
					}
				}else{             //反之，先把存储数组中的树存到组合树的后半部分
					int c = 0;
					for(int j = (int)Math.pow(2, i); j < Math.pow(2, i+1); j++){
						store[j] = store[c++];
					}
					c = 0;
					for(int j = index; j < sumNode(i); j++){  //然后把堆中的树存到组合树的前半部分，并把堆的树初始化
						store[c++] = heap[j];
						heap[j] = 0;
					}
				}
			}
			else{              //当树根节点为0即该树为空时，把存储数组存进该树中
				int c = 0;
				for(int j = index; j < sumNode(i); j++){
					heap[j] = store[c++];
				}
				break;
			}
			
			index = sumNode(i);              //把指针指向下一棵树的根节点
			int nextlength = sumNode(i+1);     //下一棵树的长度
			if(nextlength > heap.length){       //当下一棵树长度大于二项堆数组长度时。
				int[] create = new int[heap.length];       //通过过渡数组把二项堆数组的元素转移到新扩增数组中
				for(int j = 0; j < heap.length; j++){
					create[j] = heap[j];
				}
				heap = new int[nextlength];
				for(int j = 0; j < create.length; j++){
					heap[j] = create[j];
				}
			}
		}
	}
	
	/*
	 * 删除最小值并合并剩余子树
	 */
	public void deleteMin(){
		int index = 0;           //该指针用于在堆中寻找最小值，只需寻找每棵树的根节点
		int d = 0;
		int[] delete;         //用于存储删除了最小值的那棵树
		int[] store;
		for(int i = 0; i >= 0; i++){
			if(heap[index] == getMin()){              //当找到最小值时
				delete = new int[(int)Math.pow(2, i)-1];
				store = new int[(int)Math.pow(2, i)];
				heap[index] = 0;                 //先把最小值删除，即改成0
				for(int j = index + 1; j < sumNode(i); j++){
					delete[d++] = heap[j];
					heap[j] = 0;
				}
				int index2 = 0;             //为delete数组中每棵树的根节点指针
				for(int j = 0; j < i; j++){    //j为delete的编号
					d = 0;
					for(int k = index2; k < sumNode(j); k++){
						store[d++] = delete[k];
					}
					int index3 = index2;   //为heap中可能用来存储delete的每棵树的根节点指针,然后下面就与插入代码类似
					for(int k = j; k >= 0; k++){
						d = 0;
						if(heap[index3] == 0){
							for(int l = index3; l < sumNode(k); l++){
								heap[l] = store[d++];
							}
							break;
						}else{
							if(heap[index3] > store[0]){
								int c = (int)Math.pow(2, k);
								for(int l = index3; l < sumNode(k); l++){
									store[c++] = heap[l];
									heap[l] = 0;
								}
							}else{
								int c = 0;
								for(int l = (int)Math.pow(2, k); l < Math.pow(2, k+1); l++){
									store[l] = store[c++];
								}
								c = 0;
								for(int l = index3; l < sumNode(k); l++){  
									store[c++] = heap[l];
									heap[l] = 0;
								}
							}
							index3 = sumNode(k);
						}
					}
					index2 = sumNode(j);
				}
				break;
			}else{
				index = sumNode(i);
			}
		}
	}
	
	/*
	 * 计算编号为id的节点数
	 */
	private int sumNode(int id){
		int sum = 0;
		for(int i = 0; i <= id; i++){
			sum += (int)Math.pow(2, i);
		}
		return sum;
	}
	
	/*
	 * 获取二项堆数组
	 */
	public int[] getArray(){
		return heap;
	}
	
	/*
	 * 获取二项堆的最小值
	 */
	public int getMin(){
		int min = heap[heap.length-1];
		int index = 0;
		for(int i = 0; i >= 0; i++){
			index = index + (int)Math.pow(2, i);
			if(index >= heap.length){
				break;
			}
			if(heap[index] != 0 && min > heap[index]){
				min = heap[index];
			}
		}
		return min;
	}
	
	/*
	 * 获取堆中的节点数
	 */
	public int getLength(){
		int sum = 0;
		int index = 0;
		if(heap.length == 0){
			return 0;
		}
		for(int i = 0; i >= 0; i++){
			if(sumNode(i-1) >= heap.length){      //当树的根节点指针大于堆长度时退出
				break;
			}
			if(heap[index] != 0){       //当树的根节点不为0时，直接把该树的节点数加到sum中
				sum += (int)Math.pow(2, i);
			}
			index = sumNode(i);    //下一棵树的根节点
		}
		return sum;
	}
}
