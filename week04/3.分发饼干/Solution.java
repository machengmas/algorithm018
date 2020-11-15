import java.util.Arrays;

/**
 * 题目概述： 分发饼干
 * leetcode链接：https://leetcode-cn.com/problems/assign-cookies/
 */
public class Solution {
    /**
     * 解题思路：对两个数组进行排序，将最大号的的饼干给胃口最大的孩子，这样就不会浪费大饼干，最后统计孩子的数量即可。
     * 时间复杂度：O(nlogn+mlogm) 排序的时间复杂度
     * 空间复杂度：O(1)
     */
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) throw new RuntimeException("illegal input!");
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = g.length - 1;
        int si = s.length - 1;
        int res = 0;
        while (gi >= 0 && si >= 0) {
            if (s[si] >= g[gi]) {
                si--;
                res++;
            }
            gi--;
        }
        return res;
    }

}
