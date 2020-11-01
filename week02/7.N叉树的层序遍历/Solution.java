import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目概述： N叉树的层序遍历
 * leetcode链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 */
public class Solution {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    /**
     * 解法：第一步：写出使用队列对树进行广度优先搜索的模板代码
     * 第二步：加一层循环遍历当前队列的元素（并且从队列中移除），这样就可以保存每一层的所有节点，且不会增加时间复杂度
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                result.add(node.val);
                queue.addAll(node.children);
            }
            results.add(result);
        }
        return results;
    }

}
