#数组
###java实现  
ArrayList，相比于java的数组类型，主要是长度可变（扩容机制）  
时间复杂度：查询O(1),增删改O(n)
#链表
###基本实现
	class LinkedList {   
    	/* Linked list Node*/
    	class Node { 
       	int data; 
        	Node next; 
  
        	// Constructor to create a new node 
        	// Next is by default initialized 
        	// as null 
        	Node(int d) { data = d; } 
    	} 
	}
###java实现
LinkedList，与基本实现的区别在于java中是双向链表，不止有next Node，也有pre Node。且java中还有头指针head和尾指针tail。  
时间复杂度：查询O（n）,增删改O(1)。
#跳表
链表的基础上，使用空间换时间的思想，采用二分思想，做到查询、增删改的时间都是O(logn),空间复杂度是O(n)。  
p.s. redis使用跳表实现。
###双端队列（栈和队列的结合体）
双端都可以进出元素。
###java stack的实现
使用vector(线程安全的数组)，也就是说栈的实现是数组。栈在java中是以类存在的。  
p.s. vector虽然方法内部是线程安全的的，但是对于复合操作还是需要同步处理，所以vector某种意义上来说是没有意义的，还会拖慢方法速度。从这个角度上来说，更应该使用双端队列来代替使用stack。
###java queue的实现
java中queue是接口，他有很多实现，包括以数组的方式ArrayDeque实现或者以链表的方式LinkedList的实现。
###java 双端队列dqueue
java中的dqueue是接口，他也有很多实现，包括以数组的方式ArrayDeque实现或者以链表的方式LinkedList的实现。
#优先队列
一种抽象的数据结构，要求出队时是按照优先级出队的。根据实现不同时间复杂度不一样，比如链表实现，入队O(1),出队O(n);二叉堆实现，入队O(logn),出队O(logn)。  
java中对应的是PriorityQueue，是一个类,使用二叉堆的实现。

#Hash
使用hash函数，将要存储的值对应到唯一的一个地址上（在不产生碰撞的情况下），插入删除查询都是O(1)的复杂度。  
java中尝试用的是HashSet,HashMap。

#树
在链表的基础上不止有一个next指针，变成有多个next指针
###二叉树
在链表的基础上有两个next指针 
 
	public class TreeNode {
   		int val;
      	TreeNode left;
      	TreeNode right;

    	TreeNode() {
   		}

   		TreeNode(int val) {
   			this.val = val;
     	}

  		TreeNode(int val, TreeNode left, TreeNode right) {
      		this.val = val;
        	this.left = left;
        	this.right = right;
     	}
   	}	
###二叉搜索树（排序二叉树）
满足三个条件：  
1.左子树的所有节点小于根节点--注意不只是左孩子小于根节点  
2.右子树的所有节点大于根节点--注意不只是右孩子大于根节点  
3.以此类推左子树也满足1，2两点，右子树也满足1，2两点。----这就是二叉搜索树的重复性

以此性质：所以中序遍历就正好是升序的

时间复杂度：
查询：类似二分查找，O（logn）。
插入：查询不到这个元素返回的那个null节点位置插入即可，所以还是O（logn）
删除：在左子树中找到刚小于删除节点的那个节点，或者在右子树中找到刚大于删除节点的那个节点，还是O（logn）

#堆
一种抽象数据结构，可以快速的获取很多数据中的最大的（大顶堆）或者最小的（小顶堆）元素
##时间复杂度
堆对时间复杂度是有要求的，或者可以说满足下面的时间复杂度的数据结构才可以称为堆（二三堆是特例）  
查询：O(1)  
插入：O(logn)  
删除：O(logn)
##堆的实现
常见的有二叉堆或者更优的斐波那契堆
###二叉堆
通过完全二叉树实现
####完全二叉树
1.是一颗完全树  
2.根节点总是大于等于左右儿子  
p.s.完全二叉树不是二叉搜索树。用二叉搜索树也可以实现堆，但是查询不是O(1),二是O(logn)  

####二叉堆的实现
完全二叉树因为其元素特点，我们可以不用链表节点来实现，直接用数组就可以实现。
所以二叉堆一般也就直接用数组来实现。  
此数组的特点：  
1.索引为i的左孩子节点的索引2i+1;  
2.索引为i的右孩子节点的索引2i+2;  
3.索引为i的父节点的索引(i-1)/2取整

###二叉堆的时间复杂度分析
查询：O(1)  没啥说的，数组的第一个元素
插入：O(logn)  插入的数组的最后一位，依次和父节点比较大小，向上交换。
删除：O(logn) 删除数组第一个元素后，将随后一个元素填入第一个元素中，依次和左右孩子节点比较大小，取最大（或者最小）向下交换。

#图
bfs和dfs，注意和树的区别在于遍历时要加一个visited集合过滤已经被访问过的节点，因为图是互相连接的，会有大量重复节点被访问

#递归
##模板代码

	// Java
	public void recur(int level, int param) { 
  		// terminator 
  		if (level > MAX_LEVEL) { 
    		// process result 
    		return; 
  		}
  		// process current logic 
  		process(level, param); 
  		// drill down 
  		recur( level: level + 1, newParam); 
  		// restore current status 
  		
	}
	
	
	
#分治
##模板代码
	private static int divide_conquer(Problem problem, ) {
  
  		if (problem == NULL) {
    		int res = process_last_result();
    		return res;     
  		}
  		subProblems = split_problem(problem)
  
  		res0 = divide_conquer(subProblems[0])
  		res1 = divide_conquer(subProblems[1])
  
  		result = process_result(res0, res1);
  
  		return result;
	}
	
#深度优先遍历(DFS)
##模板代码（递归）
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }


    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }
    
##模板代码（循环）
#广度优先遍历(BFS)
	public class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;

    	TreeNode(int x) {
        	val = x;
    	}
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> allResults = new ArrayList<>();
    	if (root == null) {
        	return allResults;
    	}
    	Queue<TreeNode> nodes = new LinkedList<>();
    	nodes.add(root);
    	while (!nodes.isEmpty()) {
        	int size = nodes.size();
        	List<Integer> results = new ArrayList<>();
        	for (int i = 0; i < size; i++) {
            	TreeNode node = nodes.poll();
            	results.add(node.val);
            	if (node.left != null) {
                	nodes.add(node.left);
            	}
            	if (node.right != null) {
                	nodes.add(node.right);
            	}
        	}
        	allResults.add(results);
    	}
   		return allResults;
	}





