package com.seaboat.dsa.heap;

/**

* @author awang
* @version 1.0
* @date 2019年4月5日 下午5:34:26
* 斐波那契堆
* 该类还存在隐患问题，需要对于每个节点添加个父节点指针属性，然后修改一下代码中获取父节点的方式。
*/

public class FibonacciHeap {
	private BinomialTree headTree;
	private BinomialTree minTree;
	private BinomialTree currentTree;      //getNext方法中的当前树
	private static int NO;           //用于存储getNext方法的当前树为堆中第几棵树
	
	public FibonacciHeap(){
		this.headTree = null;
		this.minTree = null;
		this.currentTree = null;
		NO = 0;
	}
	
	/**
	 * 插入一棵二项树，参数为树的度数以及所要插入的二项树数组
	 * 所插入数组必须符合是二项树，并指明该二项树的编号，不可直接使用BinomialHeap类创建的对象
	 */
	public void insertTree(int id, int[] tree){
		boolean success = false;
		BinomialTree bt = new BinomialTree(id, tree);     //为将要插入的数组创建一个类内二项树对象
		BinomialTree current = headTree;           //刚开始把当前树指向head树
		if(headTree == null){       //当head树为null也就是斐波那契堆为空时
			headTree = bt;
			minTree = headTree;
			return;
		}else{
			while(true){
				if(current.id > id){                           //当当前树的编号大于插入树的编号时
					if(current == headTree){               //当当前树符合上面条件并为头树时
						current.preTree = bt;
						bt.nextTree = current;
						headTree = bt;
					}else{                               //或者不为头树时
						bt.preTree = current.preTree;
						current.preTree.nextTree = bt;
						current.preTree = bt;
						bt.nextTree = current;
					}
					success = true;
				}else{
					if(current.nextTree == null){			//当当前树编号小于插入树的编号并当前树的 下一棵树为空时
						current.nextTree = bt;
						bt.preTree = current;
						success = true;
					}
				}
				if(success){              //当把树插入到堆里后
					if(minTree.tree[0] > bt.tree[0]){      //维护最小指针
						minTree = bt;
					}
					break;				//退出循环
				}
				current = current.nextTree;         //当该轮循环没有把树插入到堆里时，把当前树指向下一棵树
			}
		}
	}
	
	/**
	 * 合并两个斐波那契堆
	 */
	public void mergeHeap(FibonacciHeap fh){
		BinomialTree current = fh.headTree;    //从待合并堆的头树开始
		while(true){                         //一个一个插入到要合并到的堆中
			insertTree(current.id, current.tree);
			if(current.nextTree == null){
				break;
			}
			current = current.nextTree;
		}
	}
	
	/**
	 * 删除最小节点值并把最小节点值所在的树剩下的子树放进堆中然后合并堆中相同度的树以及维护最小指针
	 */
	public void deleteMinNode(){
		if(minTree == null){
			return;
		}
		int[] nodes = new int[minTree.tree.length - 1];          //用于存放删去最小节点值后的所有子树节点
		int index = 0;
		for(int i = 1; i < this.minTree.tree.length; i++){      //把所有子树节点值放进nodes数组中
			nodes[index++] = minTree.tree[i];
		}
		index = 0;
		for(int i = 0; i < this.minTree.id; i++){       //把最小值树的每棵子树放进堆中
			int sonIndex = 0;
			int[] sonTree = new int[(int)Math.pow(2, i)];
			for(int j = 0; j < sonTree.length; j++){    //把每棵子树的节点提取出来然后插入到堆中
				sonTree[sonIndex++] = nodes[index++];
			}
			insertTree(i, sonTree);
		}
		if(minTree == headTree){						//然后把最小值树移除出堆，当最小指针指向头树时
			minTree.nextTree.preTree = null;
			headTree = minTree.nextTree;
		}else if(minTree.nextTree == null){             //当最小指针指向最后一棵树时
			minTree.preTree.nextTree = null;
		}else{											//当最小指针在堆中间时
			minTree.preTree.nextTree = minTree.nextTree;
			minTree.nextTree.preTree = minTree.preTree;
		}
		BinomialTree current = headTree;
		for(int i = 1; i > 0; i++){                        //合并堆中相同度的树
			if(current.nextTree == null){           //当当前树为堆中最后一棵树时合并完成退出循环
				break;
			}
			if(current.nextTree.id == current.id){                           //当当前树与下一棵树的度一样时进行合并
				int[] mergeTree = new int[(int)Math.pow(2, current.id + 1)];              //用于存储合并后的树
				
				if(current.nextTree.nextTree == null && current == headTree){            //对于合并时，有四种情况
					headTree = null;
				}else if(current.nextTree.nextTree == null && current != headTree){
					current.preTree.nextTree = null;
				}else if(current.nextTree.nextTree != null && current == headTree){
					headTree = current.nextTree.nextTree;
					headTree.preTree = null;
				}else{
					current.preTree.nextTree = current.nextTree.nextTree;
					current.nextTree.nextTree.preTree = current.preTree;
				}
				
				if(current.tree[0] < current.nextTree.tree[0]){							//合并两棵树
					for(int j = 0; j < Math.pow(2, current.id); j++){
						mergeTree[j] = current.tree[j];
						mergeTree[j + (int)Math.pow(2, current.id)] = current.nextTree.tree[j];
					}
					insertTree(current.id + 1, mergeTree);
				}else{
					for(int j = 0; j < Math.pow(2, current.id); j++){
						mergeTree[j] = current.nextTree.tree[j];
						mergeTree[j + (int)Math.pow(2, current.id)] = current.tree[j];
					}
					insertTree(current.id + 1, mergeTree);
				}
				current = headTree;													//合并完后把当前树重新指向头树
			}else{
				current = current.nextTree;										//当当前树与下一棵树度不一样时，把当前指针指向下一棵树
			}
		}
		current = headTree;
		minTree = current;
		while(true){								//然后需要重新维护最小树指针，查找最小树
			if(current.nextTree == null){
				break;
			}
			if(current.nextTree.tree[0] < minTree.tree[0]){
				minTree = current.nextTree;
			}
			current = current.nextTree;
		}
	}
	
	/**
	 * 减小特定的节点值，必须改成比原来节点值小的值，且nodeNum不能指向值为0的节点。treeNum指明是堆中第几棵树，nodeNum指明是树的第几个节点，value为所要改成的值
	 */
	public void decreaseNode(int treeNum, int nodeNum, int value){
		BinomialTree current = headTree;
		int nodeIndex = nodeNum - 1;
		for(int i = 1; i < treeNum; i++){             //获取指定的树
			if(current.nextTree == null){            //当指定的树的棵数大于堆中的总数时
				return;
			}
			current = current.nextTree;
		}
		//当要修改的节点的节点值为0即已经修改过或者输入值大于该节点时直接退出
		if(nodeNum > current.tree.length || value >= current.tree[nodeIndex] || current.tree[nodeIndex] == 0){         
			return;
		}
		if(nodeIndex == 0){                  //当节点为根节点时直接修改值，此时需要维护最小树
			current.tree[0] = value;
			if(current.tree[0] < minTree.tree[0]){
				minTree = current;
			}
			return;
		}
		/* 先分为树长度大于4跟小于等于4两种情况
		 * 然后再分别分为三种情况，1.当节点具有两个或两个以上的子树时。2.当节点只有一个子树时。3.当节点为叶子节点时
		 */
		if(current.tree.length <= 4){        //树长度小于等于4时，然后此时只具备2、3两种情况
			if((nodeIndex + 3) % 4 == 0 || (nodeIndex + 1) % 4 == 0){
				current.tree[nodeIndex] = value;
				if(value < current.tree[nodeIndex - 1]){
					insertTree(0, new int[]{current.tree[nodeIndex]});
					current.tree[nodeIndex] = 0;
				}
			}else{
				current.tree[nodeIndex] = value;
				if(value < current.tree[nodeIndex - 2]){
					insertTree(1, new int[]{current.tree[nodeIndex], current.tree[nodeIndex + 1]});
					current.tree = new int[]{current.tree[0], current.tree[1]};
					current.id--;
				}
			}
		}else{										//树长度大于4时，此时1、2、3三种可能都有可能出现
			current.tree[nodeIndex] = value;
			if(nodeIndex % 4 == 0){     //第一种情况
				int half = (int)Math.pow(2, current.id - 1);
				if(nodeIndex >= half){					//当待减小的节点处于当前树的下半区间时，把整个下半区间子树移除出当前树并插入到堆中。
					int[] tree = new int[half];
					int index = 0;
					int num = 0;
					BinomialTree next = headTree;
					for(int i = half; i < current.tree.length; i++){
						tree[index++] = current.tree[i];
					}
					insertTree(current.id - 1, tree);
					for(int i = 1; i > 0; i++){
						if(next.id == current.id){
							num = i;
							break;
						}
						next = next.nextTree;
					}
					decreaseNode(num - 1, nodeNum - half, value);			//然后再对移除出去的子树递归进行减小节点值操作
					tree = new int[half];
					for(int i = 0; i < half; i++){
						tree[i] = current.tree[i];
					}
					current.tree = tree;
					current.id--;
					for(int i = 1; i > 0; i++){								//当树的最后节点为0时，把该节点移除
						if(current.tree[current.tree.length - 1] == 0){
							tree = new int[current.tree.length - 1];
							for(int j = 0; j < tree.length; j++){
								tree[j] = current.tree[j];
							}
							current.tree = tree;
							if(tree.length < getFibonacciArray(current.id)){
								current.id--;
							}
						}else{
							break;
						}
					}
					insertTree(current.id, current.tree);			//然后为了编号的排序，重新插入到堆中，并把当前树删掉
					if(current == headTree){
						current.nextTree.preTree = null;
						headTree = current.nextTree;
					}else if(current.nextTree == null){            
						current.preTree.nextTree = null;
					}else{											
						current.preTree.nextTree = current.nextTree;
						current.nextTree.preTree = current.preTree;
					}
				}else{												//当待减小的节点处于上半区间时，才开始进行减小操作
					int currentRange = (int)Math.pow(2, current.id - 1);
					int lastRange = currentRange;
					for(int i = 1; i < current.id - 1; i++){
						currentRange -= (int)Math.pow(2, i);
						if(nodeIndex > currentRange){
							if(value < current.tree[currentRange]){
								int[] tree = new int[lastRange - nodeIndex];
								int index = 0;
								for(int j = nodeIndex; j < lastRange; j++){
									tree[index++] = current.tree[j];
									current.tree[j] = 0;
								}
								int id = 0;
								for(int j = 1; j > 0; j++){
									if(getFibonacciArray(j) <= tree.length && getFibonacciArray(j + 1) > tree.length){
										id = j;
										break;
									}
								}
								insertTree(id, tree);
							}
							break;
						}
					}
				}
			}
			if((nodeIndex - 1) % 4 == 0 || (nodeIndex - 3) % 4 == 0){  //第二种情况
				current.tree[nodeIndex] = value;
				if(value < current.tree[nodeIndex - 1]){
					insertTree(0, new int[]{current.tree[nodeIndex]});
					current.tree[nodeIndex] = 0;
				}
			}
			if((nodeIndex - 2) % 4 == 0){					//第三种情况
				if(value < current.tree[nodeIndex - 2]){
					insertTree(1, new int[]{current.tree[nodeIndex], current.tree[nodeIndex + 1]});
					if(nodeIndex + 2 == current.tree.length){          //当该子树为最后面一棵子树时，把它从当前树中移除
						int[] tree = new int[nodeIndex];
						for(int i = 0; i < nodeIndex; i++){
							tree[i] = current.tree[i];
						}
						current.tree = tree;
						if(current.tree.length < getFibonacciArray(current.id)){
							current.id--;
						}
					}else{
						current.tree[nodeIndex] = 0;
						current.tree[nodeIndex + 1] = 0;
					}
				}
			}
		}
	}
	
	/**
	 * 二项树内部类
	 */
	private class BinomialTree{
		private int id;   //编号
		private int[] tree;         //二项树数组
		private BinomialTree nextTree;        //当前树的下一棵树
		private BinomialTree preTree;			//当前树的前一棵树
		
		public BinomialTree(int id, int[] nodes){
			this.id = id;
			this.tree = new int[nodes.length];
			for(int i = 0; i < nodes.length; i++){
				tree[i] = nodes[i];
			}
		}
		
		/**
		 * 用于打印当前二项树的信息
		 */
		@Override
		public String toString(){
			StringBuffer s = new StringBuffer();
			s.append("当前树的编号为：" + id + "，");
			s.append("节点值分别为：");
			for(int i = 0; i < tree.length; i++){
				s.append(tree[i]);
				if(i != tree.length - 1){
					s.append(",");
				}
			}
			return s.toString();
		}
	}
	
	/**
	 * 获取head树
	 */
	public String getHead(){
		System.out.println("----------------------head树----------------------");
		return headTree.toString();
	}
	
	/**
	 * 查看每棵树
	 */
	public String getNext(){
		if(currentTree == null){
			NO++;
			currentTree = headTree;
			System.out.println("----------------------head树----------------------");
		}else{
			NO++;
			currentTree = currentTree.nextTree;
			System.out.println("----------------------第" + NO + "棵树----------------------");
		}
		return currentTree.toString();
	}
	
	public void getAll(FibonacciHeap fh){
		System.out.println("获取所有树。。。。");
		for(int i = 1; i > 0; i++){
			if(fh.hasNext()){
				System.out.println(fh.getNext());
			}else{
				break;
			}
		}
		resetNext();
	}
	
	/**
	 * 第二次获取所有树时需先执行该方法
	 */
	public void resetNext(){
		NO = 0;
		currentTree = null;
	}
	
	/**
	 * 检查对于getNext来说是否存在下一棵树
	 */
	public boolean hasNext(){
		if(headTree == null || (currentTree != null && currentTree.nextTree == null)){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取最小指针指向的树
	 */
	public String getMin(){
		System.out.println("----------------------min树-----------------------");
		return minTree.toString();
	}
	
	/**
	 * 获取度数所对应的斐波那契堆数列的元素，即最小节点数
	 */
	private int getFibonacciArray(int id){
		if(id == 0){
			return 1;
		}
		if(id == 1){
			return 2;
		}
		return getFibonacciArray(id-1) + getFibonacciArray(id-2);
	}
}
