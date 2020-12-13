package com.thunisoft.algorithm;

/**
 * 题目概述： 位1的个数
 * leetcode链接：https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class HammingWeight {
    /**
     * 解法：将数字n和1进行与运算，得到数字的二进制的最后一位，如果是1就结果+1，
     * 每次与运算后将n右移一位
     * 时间复杂度：O(1)
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n =n>> 1;
        }
        return res;

    }

    /**
     * 解法：将数字n和n-1进行与运算，消除掉最末尾的1，将得到的数赋给n，不停的与n-1,直到n==0
     * 每次与运算后将n右移一位
     * 时间复杂度：O(1)
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }
}
