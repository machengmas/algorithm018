import java.util.ArrayList;
import java.util.List;

/**
 * 题目概述： 二叉树的前序遍历
 * leetcode链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/submissions/
 */
public class Solution {

    // Definition for a binary tree node.
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


    /**
     * 解法：递归
     * 时间复杂度：O(n) 把所有节点遍历一次，所以是O(n)
     * 空间复杂度：O(n) 空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(result, root);
        return result;
    }

    private void order(List<Integer> result, TreeNode root) {
        if (root == null) return;
        result.add(root.val);
        order(result, root.left);
        order(result, root.right);
    }
}
