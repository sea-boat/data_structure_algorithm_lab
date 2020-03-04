package com.seaboat.dsa.heap;

/**

* @author awang
* @version 1.0
* @date 2019年4月1日 下午6:50:02
* 
*/

public class BinaryHeap {

	private Integer[] a;     //存储数组
	private Integer[] b;      //过渡数组
	private int index;
	
	public BinaryHeap(int[] b) {     //用一个数组初始化二叉堆
		a = new Integer[0];
		insertArray(b);
	}
	public BinaryHeap() {       //直接初始化一个空的二叉堆
		a = new Integer[0];
	}
	
	/*
	 * 插入单个整数
	 */
	public void insertNum(int num) {
		b = new Integer[a.length+1];         //为数组扩增1长度
		if(a.length != 0){              //当原数组长度不为0时，把前面的值都赋给扩增数组
			for(int i = 0; i < a.length; i++) {
				b[i] = a[i];
			}
		}	
		b[a.length] = num;         //帮入参设为扩增数组最后一个元素
		index = a.length;
		for(int i = 1; i > 0; i++) {
			int nextIndex;
			nextIndex = (index-1)/2;          //父节点的指针
			if(nextIndex < 0 || b[nextIndex] <= b[index]){ //当父节点指针小于0或者父节点值小于等于当前节点值时退出循环
				break;
			}
			b[nextIndex] = b[nextIndex] + b[index];   //与父节点交换值
			b[index] = b[nextIndex] - b[index];
			b[nextIndex] = b[nextIndex] - b[index];
			index = nextIndex;       //把当前指针指向父节点指针然后继续
		}
		a = b;         //交换完后把数组b赋值给数组a
	}
	
	/*
	 * 插入一个数组，可以把新数组插入到空数组，也可以插入到已有二叉堆
	 */
	public void insertArray(int[] array){
		b = new Integer[a.length+array.length];      //初始化临时数组用于存放原数组与新插入数组
		int sonIndex = b.length - 1;
		if(a.length != 0){                //插入原数组各元素的值
			for(int i = 0; i < a.length; i++) {
				b[i] = a[i];
			}
		}
		for(int i = a.length; i < b.length; i++){   //  插入新数组各元素的值
			b[i] = array[i - a.length];
		}
		for(int i = b.length -1; i >= 0; i--){
			if(i * 2 + 1 < b.length){    //首先判断是否是非叶子节点，即是否有子节点
				if(i * 2 + 2 < b.length){
					sonIndex = b[i * 2 + 1] > b[i * 2 + 2] ? i * 2 + 2 : i * 2 + 1 ;
				}else{
					sonIndex = i * 2 + 1;
				}
			}else{
				continue;        //当为非叶子节点时进入下一个循环
			}
			int currentIndex = i;
			for(int j = 1 ; j > 0; j++){
				if(b[currentIndex] > b[sonIndex]){
					b[currentIndex] = b[currentIndex] + b[sonIndex];      //把比父节点小的子节点与父节点交换
					b[sonIndex] = b[currentIndex] - b[sonIndex];
					b[currentIndex] = b[currentIndex] - b[sonIndex];
					currentIndex = sonIndex;
					if(sonIndex * 2 + 1 < b.length){              //当子节点还有子节点时，继续子节点与它的子节点比较
						if(sonIndex * 2 + 2 < b.length){
							//从下一节点的子节点中选择较小的节点来与该节点比较
							sonIndex = b[sonIndex * 2 + 1] > b[sonIndex * 2 + 2] ? sonIndex * 2 + 2 : sonIndex * 2 + 1 ;
						}else{
							sonIndex = sonIndex * 2 + 1;        //当下一节点只有一个子节点直接去该子节点与该节点比较
						}
					}else{
						break;
					}
				}else{
					break;
				}
			}
		}
		a = b;
	}
	
	/*
	 * 删除最小值
	 */
	public void deleteMin(){
		b = new Integer[a.length-1];     //创建一个长度-1的数组来存储删除最小值后的原数组
		b[0] = a[a.length-1];           //把原数组的最后一个元素赋值给新数组第一个元素
		for(int i = 1; i < b.length; i++) {   //把原数组中间的元素赋值给新数组
			b[i] = a[i];
		}
		index = 0;
		for(int i = 1; i > 0; i++) {
			int nextIndex = 0;
			if(2 * index + 1 == b.length - 1){      //当子节点只有一个时，直接取那个子节点
				nextIndex = 2 * index + 1;
			}else if(2 * index + 1 >= b.length){   //当没有子节点时停止父节点与子节点的比较
				break;
			}else if(b[2 * index + 1] > b[2 * index + 2]){   //比较两个子节点的值，取较小的子节点的指针
				nextIndex = 2 * index + 2;
			}else{
				nextIndex = 2 * index + 1;
			}
			//当较小的子节点值大于或等于当前节点值时退出循环
			if(b[nextIndex] >= b[index]){ 
				break;
			}
			b[nextIndex] = b[nextIndex] + b[index];   //与子节点交换值
			b[index] = b[nextIndex] - b[index];
			b[nextIndex] = b[nextIndex] - b[index];
			index = nextIndex;       //把当前指针指向交换值的子节点然后继续
		}
		a = b;         //交换完后把数组b赋值给数组a
	}
	
	public int getMin(){
		return a[0];
	}
	public Integer[] getArray() {
		return a;
	}
	public int getLength() {
		return a.length;
	}
}
