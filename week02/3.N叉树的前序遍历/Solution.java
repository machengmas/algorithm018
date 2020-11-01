import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目概述： N叉树的前序遍历
 * leetcode链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
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

    ;

    /**
     * 解法：递归
     * 时间复杂度：O(n) 把所有节点遍历一次，所以是O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(result, root);
        return result;

    }

    private void preorder(List<Integer> result, Node root) {
        if (root == null) return;
        result.add(root.val);
        if (root.children == null) return;
        for (Node node : root.children) {
            preorder(result, node);
        }
    }


}
