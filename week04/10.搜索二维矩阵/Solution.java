/**
 * 题目概述： 搜索二维矩阵
 * leetcode链接：https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class Solution {
    /**
     * 解题思路：有边界，递增，有索引，满足二分查找条件，采用二分查找算法
     * 时间复杂度：O(logn) n为数组的元素个数
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null||matrix.length==0) return false;

        int n = matrix[0].length;

        int left = 0;
        int right = matrix.length * n - 1;
        int middle,num;
        while (left <= right) {
            middle = (left + right) / 2;
            num = matrix[middle / n][middle % n];

            if (num == target) return true;
            else if (num < target) left = middle + 1;
            else right = middle - 1;
        }
        return false;
    }


}
