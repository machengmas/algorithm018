import java.util.*;

/**
 * 题目概述： 前 K 个高频元素
 * leetcode链接：https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class Solution1 {
    /**
     * 解法：使用堆来解决
     * 第一步：遍历数组统计出每个数字出现的次数，存入map
     * 第二步：维护一个小顶堆
     * 第三步：遍历map，如果堆的大小小于k，往里面加入map中的元素；如果堆的大小等于k：
     * 判断堆顶元素（数字出现的次数）大于遍历到的元素，则舍弃这个元素，否则替换堆顶的元素
     * 第四部：取出堆中的元素赋值给数组返回
     * 时间复杂度：O(nlogk) 遍历map是O(n),map中插入堆元素是O(logk),所以总的时间复杂度是O(nlogk)
     * 空间复杂度：O(n)哈希表的大小为 O(n)，而堆的大小为 O(k)，共计还是 O(n)。
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minPriorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (minPriorityQueue.size() == k) {
                if (minPriorityQueue.peek()[1] < count) {
                    minPriorityQueue.poll();
                    minPriorityQueue.offer(new int[]{num, count});
                }
            } else {
                minPriorityQueue.offer(new int[]{num, count});
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minPriorityQueue.poll()[0];
        }
        return result;
    }

}
