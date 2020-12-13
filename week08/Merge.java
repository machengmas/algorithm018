package com.thunisoft.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 题目概述： 合并区间
 * leetcode链接：https://leetcode-cn.com/problems/merge-intervals/
 */
public class Merge {
    /**
     * 解法：对区间按照左端点排序，将第一个区间加入结果集，然后分情况考虑：
     * 1.如果下一个区间的左边界大于上一个区间的有边界，那么下一个区间直接加入结果集
     * 2.否则，更新上一个区间的右边界为二者两个区间的有边界的最大值
     * 时间复杂度：O(nlogn) n是区间的数量，其实就是排序的时间复杂度
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        int[] preInterval = intervals[0];
        res.add(preInterval);
        for (int[] interval : intervals) {
            if (interval[0] > preInterval[1]) {
                res.add(interval);
                preInterval = interval;
            } else {
                preInterval[1] = Math.max(preInterval[1], interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
