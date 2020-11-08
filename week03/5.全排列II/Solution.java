import java.util.*;

/**
 * 题目概述： 全排列 II
 * leetcode链接：https://leetcode-cn.com/problems/permutations-ii/
 */
public class Solution {
    /**
     * 解题思路：在全排列的基础上剪枝（减掉重复的），重复的情况：对数组排个序，下一个元素和上一个元素相同并且上一个元素没有被正在使用（）
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
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
            if (i > 0 && nums[i] == nums[i - 1] && !useNums[i - 1]) continue;
            path.push(nums[i]);
            useNums[i] = true;
            dfs(nums, path, useNums, res);
            useNums[i] = false;
            path.pop();
        }
    }

}
