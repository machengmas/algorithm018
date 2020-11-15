/**
 * 题目概述： 柠檬水找零
 * leetcode链接：https://leetcode-cn.com/problems/lemonade-change/
 */
public class Solution {
    /**
     * 解题思路： 因为10元是5元的倍数，所以能找10元不要找给2个5元，满足贪心的特征，使用贪心算法解题。
     * 使用两个计数器分别记录剩余5元和10元的数量，当出现5元数量<0即出现了无法找零的问题了
     * 时间复杂度：O(n)  bills的元素个数
     * 空间复杂度：O(1)
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills == null) return true;
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                five--;
                ten++;
            } else if (ten > 0) {
                five--;
                ten--;
            } else {
                five -= 3;
            }
            if (five < 0) return false;
        }
        return true;
    }

}
