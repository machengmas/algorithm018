package com.thunisoft.algorithm;

/**
 * 题目概述： 最长有效括号
 * leetcode链接：https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    /**
     * 解法：动态规划：定义dp[i]是 以i结尾 的字符串的最大有效长度
     * 1.s[i]=='(' dp[i]=0,非有效字符串;
     * 2.s[i]==')',分情况讨论：
     * （1）s[i-1]=='（' dp[i]=dp[i-2]+2;
     * （2）s[i-1]=='）' 分情况讨论:
     * a.s[i-1-dp[i-1]]=='(' ;
     * b.dp[i]=0,非有效字符串;
     * 最后：取所有字符结尾最大的有效长度就是本题结果
     * 注意：因为dp数组默认都是0，所以代码编写是只用写上非0的情况就可以了
     * <p>
     * 时间复杂度：O(n)  n是字符串的长度
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
