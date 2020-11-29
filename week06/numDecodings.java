package com.thunisoft.algorithm;

/**
 * 题目概述： 解码方法
 * leetcode链接：https://leetcode-cn.com/problems/decode-ways/
 */
public class numDecodings {


    /**
     * 解法：动态规划,分情况讨论：
     * 1.若s[i]='0':
     *      (1)s[i-1]='1'或'2',dp[i]=dp[i-2],因为最后两位必然只对应一个字符（最后两位是10或者20）
     *      (2)其余情况，后面两个数字必然无法对应任何字符，导致前面的字符也没有用，返回0（不可编码）。
     * 2.若s[i]!='0'
     *      (1)最后两位在1到26之间（s[i-1]='1'或者s[i-1]='2'并且[si]在1到6之间),dp[i]=dp[i-1] +dp[i-2]  这里dp[i-2]表示将最后两位合在一起考虑，
     *      dp[i-1]表示最后一位单独考虑，前一位合并到前面考虑。
     *      (2)最后两位不在1到26之间，那么dp[i]=dp[i-1](用curr的时候，由于写的是curr=curr，这种情况在代码中可以省略)。
     * 时间复杂度：O(n)  n表示字符转s的长度
     */
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int pre = 1;
        int curr = 1;
        char[] charArray = s.toCharArray();
        for (int i = 1; i < charArray.length; i++) {
            int temp = curr;
            if (charArray[i] == '0') {
                if (charArray[i - 1] == '1' || charArray[i - 1] == '2') {
                    curr = pre;
                } else {
                    return 0;
                }
            } else if (charArray[i - 1] == '1' || (charArray[i - 1] == '2' && charArray[i] >= '1' && charArray[i] <= '6')) {
                curr = curr + pre;
            }
            pre = temp;
        }
        return curr;
    }
}
