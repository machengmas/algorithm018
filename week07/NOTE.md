#字典树
也是一种树结构，也叫单词查找树或者前缀树。  
##功能
统计和排序大量字符串（不仅是字符串），比如搜索引擎词频统计。  
查询效率比哈希高，因为哈希产生碰撞后，查询可能是O(n)或者是O(logn)
##特点
1.结点本身不存完整的单词，只存一个字符。  
2.从根节点到某一节点，路径上经过的字符的连接为该节点对应的字符串。  
3.每个节点的子节点（或者是路径）代表的字符串必须不相同。（不然下一个字符不知道该往哪条路走了）

##解决字典树的相关问题
先要自己实现一个字典树。
##模板代码
	class Trie {
   		private boolean isEnd;
    	private Trie[] next;
	    /** Initialize your data structure here. */
    	public Trie() {
       	isEnd = false;
        	next = new Trie[26];
    	}
    
    	/** Inserts a word into the trie. */
    	public void insert(String word) {
      		if (word == null || word.length() == 0) return;
        	Trie curr = this;
        	char[] words = word.toCharArray();
        	for (int i = 0;i < words.length;i++) {
            	int n = words[i] - 'a';
            	if (curr.next[n] == null) curr.next[n] = new Trie();
            	curr = curr.next[n];
        	}
        	curr.isEnd = true;
    }
    
    	/** Returns if the word is in the trie. */
    	public boolean search(String word) {
      		Trie node = searchPrefix(word);
        	return node != null && node.isEnd;
    	}
    
    	/** Returns if there is any word in the trie that starts with 		the given prefix. */
    	public boolean startsWith(String prefix) {
        	Trie node = searchPrefix(prefix);
        	return node != null;
    	}

    	private Trie searchPrefix(String word) {
        	Trie node = this;
        	char[] words = word.toCharArray();
        	for (int i = 0;i < words.length;i++) {
            	node = node.next[words[i] - 'a'];
            	if (node == null) return null;
        	}
        	return node;
    	}
    }


#并查集
##适用场景
组团或者配对问题（朋友圈问题）
##模板代码
	class UnionFind { 
		private int count = 0; 
		private int[] parent; 
		public UnionFind(int n) { 
			count = n; 
			parent = new int[n]; 
			for (int i = 0; i < n; i++) { 
				parent[i] = i;
			}
		} 
		public int find(int p) { 
			while (p != parent[p]) { 
				parent[p] = parent[parent[p]]; 
				p = parent[p]; 
			}
			return p; 
		}
		public void union(int p, int q) { 
			int rootP = find(p); 
			int rootQ = find(q); 
			if (rootP == rootQ) return; 
			parent[rootP] = rootQ; 
			count--;
		}
	}
#高级搜索
##剪枝
1.缓存
2.删除次要的枝节
3.广度优先在某种程度上比深度搜索更智能。
##双向BFS
从两个方向BFS遍历，直到某个节点相遇。
##启发式搜索
普通搜索+优先队列
###模板代码
	public class AStar
		{
		public final static int BAR = 1; // 障碍值
		public final static int PATH = 2; // 路径
		public final static int DIRECT_VALUE = 10; // 横竖移动代价
		public final static int OBLIQUE_VALUE = 14; // 斜移动代价
		
		Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
		List<Node> closeList = new ArrayList<Node>();
		
		/**
		 * 开始算法
		 */
		public void start(MapInfo mapInfo)
		{
			if(mapInfo==null) return;
			// clean
			openList.clear();
			closeList.clear();
			// 开始搜索
			openList.add(mapInfo.start);
			moveNodes(mapInfo);
		}
	

		/**
		 * 移动当前结点
		 */
		private void moveNodes(MapInfo mapInfo)
		{
			while (!openList.isEmpty())
			{
				Node current = openList.poll();
				closeList.add(current);
				addNeighborNodeInOpen(mapInfo,current);
				if (isCoordInClose(mapInfo.end.coord))
				{
					drawPath(mapInfo.maps, mapInfo.end);
					break;
				}
			}
		}
		
		/**
		 * 在二维数组中绘制路径
		 */
		private void drawPath(int[][] maps, Node end)
		{
			if(end==null||maps==null) return;
			System.out.println("总代价：" + end.G);
			while (end != null)
			{
				Coord c = end.coord;
				maps[c.y][c.x] = PATH;
				end = end.parent;
			}
		}
	

		/**
		 * 添加所有邻结点到open表
		 */
		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
		{
			int x = current.coord.x;
			int y = current.coord.y;
			// 左
			addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
			// 上
			addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
			// 右
			addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
			// 下
			addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
			// 左上
			addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
			// 右上
			addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
			// 右下
			addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
			// 左下
			addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
		}
	

		/**
		 * 添加一个邻结点到open表
		 */
		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
		{
			if (canAddNodeToOpen(mapInfo,x, y))
			{
				Node end=mapInfo.end;
				Coord coord = new Coord(x, y);
				int G = current.G + value; // 计算邻结点的G值
				Node child = findNodeInOpen(coord);
				if (child == null)
				{
					int H=calcH(end.coord,coord); // 计算H值
					if(isEndNode(end.coord,coord))
					{
						child=end;
						child.parent=current;
						child.G=G;
						child.H=H;
					}
					else
					{
						child = new Node(coord, current, G, H);
					}
					openList.add(child);
				}
				else if (child.G > G)
				{
					child.G = G;
					child.parent = current;
					openList.add(child);
				}
			}
		}
	

		/**
		 * 从Open列表中查找结点
		 */
		private Node findNodeInOpen(Coord coord)
		{
			if (coord == null || openList.isEmpty()) return null;
			for (Node node : openList)
			{
				if (node.coord.equals(coord))
				{
					return node;
				}
			}
			return null;
		}
	

	

		/**
		 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
		 */
		private int calcH(Coord end,Coord coord)
		{
			return Math.abs(end.x - coord.x)
					+ Math.abs(end.y - coord.y);
		}
		
		/**
		 * 判断结点是否是最终结点
		 */
		private boolean isEndNode(Coord end,Coord coord)
		{
			return coord != null && end.equals(coord);
		}
	

		/**
		 * 判断结点能否放入Open列表
		 */
		private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
		{
			// 是否在地图中
			if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
			// 判断是否是不可通过的结点
			if (mapInfo.maps[y][x] == BAR) return false;
			// 判断结点是否存在close表
			if (isCoordInClose(x, y)) return false;
	

			return true;
		}
	

		/**
		 * 判断坐标是否在close表中
		 */
		private boolean isCoordInClose(Coord coord)
		{
			return coord!=null&&isCoordInClose(coord.x, coord.y);
		}
	

		/**
		 * 判断坐标是否在close表中
		 */
		private boolean isCoordInClose(int x, int y)
		{
			if (closeList.isEmpty()) return false;
			for (Node node : closeList)
			{
				if (node.coord.x == x && node.coord.y == y)
				{
					return true;
				}
			}
			return false;
		}
	}
	
#平衡树
为什么要有平衡树？  
我们知道二叉搜索树的增删改查的时间复杂度是O（logn),但是这个时间复杂度是平均来说的，在树的结构是一根棍子的形状（也就是退化成链表了），它的时间复杂度就是O(n)了，所以为了维护二叉搜索树操作的时间复杂度为O（logn)，我们就要使这棵树各个节点尽可能的分布在左右两侧，也就是需要平衡，平衡树的概念由此而生

##AVL树
右子树的高度-左子树的高度=0或者-1或者1也可以是左子树的高度-右子树的高度）    
引入概念  平衡因子  在{-1，0，1}之间  
###如何维护一个二叉搜索树的平衡达到AVL树？
（1）左旋（2）右旋（3）左右旋（4）右左旋  
p.s. 注意带有左右子树的旋转
###AVL树的缺点
1.每个节点需要额外的信息存储平衡因子，这个平衡因子需要int型来存储，当树的节点成千上亿时就会很占用存储  
2.调整的很频繁，动一个节点，可能就需要调整很多，有协场景我们可以允许高度差为2或者3但是不需要频繁调整
##红黑树（近似平衡二叉树）
允许左右高度差可以为2倍
##AVL树和红黑树对比
###性质对比
1.AVL更快的查询，因为它更平衡  
2.红黑树的增删更快，因为它维护平衡需要的成本更低  
3.AVL存储额外信息需要int，红黑树是bit，红黑树需要的额外存储空间更少  
4.AVL更适合查询，红黑树适合增删较多  
###举例场景
1.高级语言的库函数，比如set或者map里的实现，用的红黑树较多  
2.数据库一般用AVL树，因为查询较多，比如微博，你肯定是刷的较多，发的较少。  


