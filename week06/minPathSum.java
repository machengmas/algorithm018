package com.thunisoft.algorithm;

/**
 * 题目概述： 最小路径和
 * leetcode链接：https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class minPathSum {
    /**
     * 解法：动态规划，假设dp[i][j]为i，j这个位置的最小路径，由于i，j只可能由它上方或者左方走过来，
     * 得出dp方程dp[i][j]=Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
     * 时间复杂度：O(m*n)  grid数组的元素个数
     */
    public int minPathSum(int[][] grid) {
        if (grid == null) {
            throw new RuntimeException("param can not be grid");
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
