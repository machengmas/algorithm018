import java.util.*;

/**
 * 题目概述： 组合
 * leetcode链接：https://leetcode-cn.com/problems/combinations/
 */
public class Solution {
    /**
     * 解题思路：在n个数当中选出k个数，我们先选出第一个一个数，在剩下的n-1当中选出k-1个数，采用递归的方式
     *         搜索起点不断增加，保证不会出现重复的选择
     *         用栈来记录选出的每一个数，递归的终点就是栈的长度==k
     */
    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n < k) throw new RuntimeException("illegal input!n can not < k");
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int start, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.push(i);
            dfs(n, k, i + 1, path, res);
            path.pop();
        }
    }


}
