/**
 * 题目概述： 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * leetcode链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class Solution {
    // Definition for a Node.


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode res;

    public Solution() {
        this.res = null;
    }

    /**
     * 解题思路：
     * 此公共祖先root，一定是以下三种情况之一
     * （1）p和q分别在公共祖先两侧（左子树或者右子树中）
     * （2）root正好为p，q必然在他的左子树或者右子树中
     * （3）root正好为q,p必然在他的左子树或者右子树中
     * 也就是我们需要写一个递归方法判断node是否包含左子树或者右子树
     * 时间复杂度：O(n) 所有节点有且只会被访问一次
     * 空间复杂度：O(n) 取决于二叉树的深度，最坏为一条链
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean leftHas = dfs(root.left, p, q);
        boolean rightHas = dfs(root.right, p, q);
        if ((leftHas && rightHas) || ((root.val == p.val || root.val == q.val) && (leftHas || rightHas))) res = root;
        return leftHas || rightHas || root.val == p.val || root.val == q.val;

    }


}
