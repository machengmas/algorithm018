package com.thunisoft.algorithm;

/**
 * 题目概述： 最大正方形
 * leetcode链接：https://leetcode-cn.com/problems/maximal-square/
 */
public class MaximalSquare {
    /**
     * 解法：动态规划，假设dp[i,j]为以i，j为右下角顶点的最大正方形的边长
     * dp[i,j]=min(dp[i-1,j],dp[i,j-1],dp[i-1,j-1])+1
     * 当前正方形的最大边长受限于它上面的最大边长和它左边的最大边长以及它斜上方的最大边长
     * p.s. 为什么还受限于它斜上方的最大边长呢？
     * 可以这么思考：它上面的最大边长和左边的最大边长的正方形再加上i,j组成的最大面积少了左上角的顶点也无法构成正方形
     * <p>
     * 时间复杂度：O(m*n)  数组的元素个数
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
