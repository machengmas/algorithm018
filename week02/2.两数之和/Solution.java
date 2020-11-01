import java.util.HashMap;
import java.util.Map;

/**
 * 题目概述：两数之和,暴力破解就是双重循环，时间复杂度O(n2)，不采用这种方式
 * leetcode链接：https://leetcode-cn.com/problems/two-sum/
 */
public class Solution {
    /**
     * 解法：hash解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        return new int[]{};
    }


}
