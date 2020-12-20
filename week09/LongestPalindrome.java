package com.thunisoft.algorithm;

/**
 * 题目概述： 最长回文子串
 * leetcode链接：https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindrome {
    /**
     * 解法：动态规划
     * 首先，定义状态：dp[i][j]表示字符串s[i,j]是否是回文子串
     * 状态转移方程：dp[i][j]=(s[i]==s[j])&&dp[i+1][j-1]
     * 边界考虑：首先单个字母肯定是回文子串，当子串长度为2或者3时只用考虑s[i]==s[j]？即可
     * 使用start，maxLength记录起始位置和最长长度
     * 时间复杂度：O(n*n)  n表示字符转s的长度
     */
    public String longestPalindrome(String s) {
        if (s == null) {
            throw new RuntimeException("param s can not be null");
        }
        int length = s.length();
        boolean dp[][] = new boolean[length][length];
        // 初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        char[] chars = s.toCharArray();

        int maxLength = 1;
        int start = 0;

        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] == chars[j]) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j]) {
                    int curLength = j - i + 1;
                    if (curLength > maxLength) {
                        start = i;
                        maxLength = curLength;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    /**
     * 解法：中心扩展，遍历字符串s中的每个字符向两边扩展，记录最长的起点和终点，
     * 考虑两种情况：1.以单个字符向两边扩展 2以两个字符向两边扩展
     * 时间复杂度：O(n*n)  n表示字符转s的长度
     */
    public String longestPalindrome(String s) {
        if (s == null) {
            throw new RuntimeException("param s can not be null");
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end+1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
