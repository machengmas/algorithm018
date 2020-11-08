import java.util.*;

/**
 * 题目概述： 全排列
 * leetcode链接：https://leetcode-cn.com/problems/permutations/
 */
public class Solution {
    /**
     * 解题思路：dfs+回溯，使用栈来记录所有排列元素，使用布尔类型数组来记录已使用过的数字
     *         递归的终止条件：栈中的元素等于数组的长度（也就是树的深度等于数组的长度，表示一组数排列完成）
     *         注意：回溯一定要将之前的状态重置。
     * 时间复杂度：回溯的时间复杂度一般都是指数级别
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Deque<Integer> path = new LinkedList<>();
        boolean[] useNums = new boolean[nums.length];
        dfs(nums, path, useNums, res);
        return res;
    }

    private void dfs(int[] nums, Deque<Integer> path, boolean[] useNums, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            //这里需要new ArrayList<>(path)，也就是进行一份拷贝，不然等递归完成，这唯一的一份path对象就会为[],res里就会是好多[]
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (useNums[i]) continue;
            path.push(nums[i]);
            useNums[i] = true;
            dfs(nums, path, useNums, res);
            useNums[i] = false;
            path.pop();
        }
    }

}
