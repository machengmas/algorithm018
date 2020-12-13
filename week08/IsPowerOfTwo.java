package com.thunisoft.algorithm;

/**
 * 题目概述： 2的幂
 * leetcode链接：https://leetcode-cn.com/problems/power-of-two/
 */
public class IsPowerOfTwo {
    /**
     * 解法：x&(-x)==x   -x为x的补码(反码+1)，如果x为2的幂，那么x与-x一定只有最右边有相同的1
     * 时间复杂度：O(1)
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        long x=(long)n;
        return (x&(-x))==x;
    }


    /**
     * 解法：如果x为2的幂,x&(x-1)==0
     * 时间复杂度：O(1)
     */
    public boolean isPowerOfTwo1(int n) {
        if (n == 0) {
            return false;
        }
        long x=(long)n;
        return (x&(x-1))==0;
    }

}
