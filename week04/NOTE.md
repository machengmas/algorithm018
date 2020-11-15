#遍历： 
每个节点有且只访问一次
##深度优先
###代码模板
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

##广度优先 
###代码模板
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

#贪心算法
##贪心和动态规划的区别
贪心要求当前状态下每步都是最优解以达到全局最优，没有回退功能。动态规划根据之前的结果对当前做出选择，有回退功能。
#二分查找
##前提
1.单调  
2.有边界  
3.有索引  
##模板代码
	public int binarySearch(int[] array, int target) {
    	int left = 0, right = array.length - 1, mid;
    	while (left <= right) {
        	mid = (right - left) / 2 + left;
			if (array[mid] == target) {
            	return mid;
        	} else if (array[mid] > target) {
            	right = mid - 1;
        	} else {
            	left = mid + 1;
        	}
      	}
    	return -1;
	}
