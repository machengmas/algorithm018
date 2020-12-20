package com.thunisoft.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目概述： 仅仅反转字母
 * leetcode链接：https://leetcode-cn.com/problems/reverse-only-letters/
 */
public class ReverseOnlyLetters {

    /**
     * 解法：双指针法，一个指针指向字符串开头，一个指针指向字符串结尾，互相交换字符。若不是字母，i后移或者j前移
     * 时间复杂度：O(n)  n表示字符转s的长度
     */
    public String reverseOnlyLetters(String S) {
        if (S == null) {
            throw new RuntimeException("param S can not be null");
        }
        int i = 0, j = S.length()-1;
        char[] chars = S.toCharArray();
        while (i < j) {
            if (!Character.isLetter(chars[i])) {
                i++;
                continue;
            }
            if (!Character.isLetter(chars[j])) {
                j--;
                continue;
            }
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
        return new String(chars);
    }

    /**
     * 解法：利用栈的先进后出的特性
     * 时间复杂度：O(n)  n表示字符转s的长度，实际上应该是2n
     */
    public String reverseOnlyLetters(String S) {
        if (S == null) {
            throw new RuntimeException("param S can not be null");
        }
        Deque<Character> stack = new LinkedList<>();
        for (char c : S.toCharArray()) {
            if (Character.isLetter(c)) {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(stack.pop());
            } else {
                res.append(c);
            }

        }
        return res.toString();
    }
}
