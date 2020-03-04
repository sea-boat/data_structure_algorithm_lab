package com.seaboat.dsa.sort.comparison;

/**

* @author awang
* @version 1.0
* @date 2019年3月17日 下午6:40:28
* 
*/

public class SelectMethod {
	
	private int[] array;
	
	public SelectMethod(int[] array) {
		this.array = array;
	}
	
	public void sort(String method){
		
		switch(method){
			
		case "BubbleSort": 
			System.out.println("BubbleSort:");
			BubbleSort b = new BubbleSort(array);
			b.sort();
			break;
			
		case "SelectionSort":
			System.out.println("SelectionSort:");
			SelectionSort s = new SelectionSort(array);
			s.sort();
			break;
			
		case "InsertionSort":
			System.out.println("InsertionSort:");
			InsertionSort i = new InsertionSort(array);
			i.sort();
			break;
			
		case "QuickSort":
			System.out.println("QuickSort:");
			QuickSort q = new QuickSort(array);
			System.out.println("The times of the QuickSort：" + q.sort(0, array.length-1));
			break;
			
		case "ShellSort":
			System.out.println("ShellSort:");
			ShellSort h = new ShellSort(array);
			h.sort((array.length-1)/2);
			break;
			
		case "MergeSort":
			System.out.println("MergeSort:");
			MergeSort m = new MergeSort(array);
			m.sort(0, array.length-1);
			break;
			
		default: 
			System.out.println("没有该法！");
			break;
		}
		
	}

}
