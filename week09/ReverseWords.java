package com.thunisoft.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 题目概述： 翻转字符串里的单词
 * leetcode链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class ReverseWords {
    /**
     * 解法：使用java内置api,split（拆分），reverse（翻转）和 join（连接）等方法实现
     * 时间复杂度：O(n)  n表示字符转s的长度
     */
    public String reverseWords(String s) {
        if (s == null) {
            throw new RuntimeException("param s can not be null");
        }
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    /**
     * 解法：从后往前遍历字符串,将每一个单词取出，再拼接上空格即可
     * 时间复杂度：O(n)  n表示字符转s的长度
     */
    public String reverseWords(String s) {
        if (s == null) {
            throw new RuntimeException("param s can not be null");
        }
        s = s.trim();
        int end = s.length() - 1;
        int start = end;
        StringBuilder res = new StringBuilder();
        while (start >= 0) {
            while (start >= 0 && s.charAt(start) != ' ') start--;
            res.append(s.substring(start + 1, end + 1) + " ");
            while (start >= 0 && s.charAt(start) == ' ') start--;
            end = start;
        }
        return res.toString().trim();
    }
}
