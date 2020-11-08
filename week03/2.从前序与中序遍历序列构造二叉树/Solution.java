import java.util.HashMap;
import java.util.Map;

/**
 * 题目概述： 从前序与中序遍历序列构造二叉树
 * leetcode链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
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

    private Map<Integer, Integer> indexMap;

    /**
     * 解题思路：根据前序遍历我们可以得到根节点，再根据根节点和中序遍历我们可以得到这个根节点的左子树和右子树，得出结论：
     * 根据前序和中序遍历我们可以递归得到整棵树。
     * 也就是说我们在构造每一个子树的时候需要它的前序和中序遍历结果，所以我们需要计算出每个子树的前序和中序遍历的索引作为。
     * 递归步骤：
     * 1.从前序遍历中找到根节点
     * 2.从中序遍历找到根节点的索引(先将中序的数值和索引存到map中，在递归中取出这样可降低时间复杂度)--空间换时间
     * 3.计算左子树的大小
     * 3.构造左子树：(1)左子树的前序索引=（父树的前序开头+1， 父树的前序开头+1+左子树的大小-1）
     * (2)左子树的中序索引=（父树的中序开头,根节点索引-1）
     * 4.构造右子树：(1)右子树的前序索引=（ 父树的前序开头+左子树的大小+1， 父树的前序结束）
     * (2)左子树的中序索引=（根节点的索引+1,父树的中序结束）
     * --分治思想
     * 时间复杂度：O(n) 所有节点有且只会被访问一次
     * 空间复杂度：O(n) 取决于二叉树的深度，最坏为一条链
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            throw new RuntimeException("illegal input param!");

        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = indexMap.get(root.val);
        int leftChildSize = rootIndex - inLeft;

        root.left = buildTree(preorder, preLeft + 1, preLeft + leftChildSize, inorder, inLeft, rootIndex - 1);
        root.right = buildTree(preorder, preLeft + leftChildSize + 1, preRight, inorder, rootIndex + 1, inRight);
        return root;
    }


}
