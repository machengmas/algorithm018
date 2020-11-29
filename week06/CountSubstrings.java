package com.thunisoft.algorithm;

/**
 * 题目概述： 回文子串
 * leetcode链接：https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class CountSubstrings {
    /**
     * 解法：动态规划，假设dp[i][j]为从位置i到位置j的子串是否为回文串，因为是回文串，s[i]==s[j]
     * （1）i，j之间的元素小于2个,这种情况下必为回文串
     * （2）i，j之间的元素大于等于2个，只要dp[i+1][j-1]是回文串即可
     * 注意：这里在组合每一个子串的时候需要双重for循环，将外层for循环设置为子串的最后一个位置（j）,内层循环设置为最前面一个位置(i)
     * 时间复杂度：O(m*n)  grid数组的元素个数
     */
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    res++;
                    dp[i][j] = true;
                }

            }
        }
        return res;
    }
}
