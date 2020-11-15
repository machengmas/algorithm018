/**
 * 题目概述： 买卖股票的最佳时机 II
 * leetcode链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution {
    /**
     * 解题思路： 只要后一天比前一天的高，我们就买卖，保证每一步都是最优解，这样赚取的利润是最大的，使用贪心算法。
     * 时间复杂度：O(n)  遍历一次prices
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        int res=0;
        if (prices == null) return res;
        for (int i = 1; i < prices.length; i++) {
            int temp=prices[i]-prices[i-1];
            if (temp>0) res+=temp;
        }
        return res;
    }

}
