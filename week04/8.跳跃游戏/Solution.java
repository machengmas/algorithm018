/**
 * 题目概述： 跳跃游戏
 * leetcode链接：https://leetcode-cn.com/problems/jump-game/
 */
public class Solution {
    /**
     * 解题思路：如果某个格子可跳跃的最远距离是x，那么x前面的格子都可以作为起跳点；
     * 遍历整个数组从所有可以起跳的点跳跃，更新最远距离x，如果可以一直跳到最后就成功了
     * 时间复杂度：O(n)
     */
    public boolean canJump(int[] nums) {
        if (nums == null) return false;
        int maxDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxDistance) return false;
            maxDistance = Math.max(maxDistance, i + nums[i]);
        }
        return true;
    }
}
