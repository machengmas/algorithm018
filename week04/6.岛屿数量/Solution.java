import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目概述： 岛屿数量
 * leetcode链接：https://leetcode-cn.com/problems/number-of-islands/
 */
public class Solution {
    /**
     * 解题思路：遍历数组，遇到每一个1，记录为一个岛屿，并将周围的1都置为0。
     * 将周围的1置为0采用深度遍历-dfs解法，或者广度遍历-bfs解法
     * <p>
     * 时间复杂度：O(M*N) ：数组的总个数。
     * 注意 ：dfs的时间复杂度，我们假设所有数组中的数字都为1的情况，在for循环执行第一次的情况下就会将除第一个数字外所有数字置为0，
     * 后面循环执行的时候dfs都不会执行，那么这里只用考虑双层for循环的时间复杂度即可。
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int hl = grid.length;
        int vl = grid[0].length;
        int res = 0;
        for (int i = 0; i < hl; i++) {
            for (int j = 0; j < vl; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
//                    bfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int h, int v) {
        int hl = grid.length;
        int vl = grid[0].length;
        if (h < 0 || v < 0 || h >= hl || v >= vl || grid[h][v] == '0') return;

        grid[h][v] = '0';
        dfs(grid, h + 1, v);
        dfs(grid, h - 1, v);
        dfs(grid, h, v + 1);
        dfs(grid, h, v - 1);
    }

    private void bfs(char[][] grid, int h, int v) {
        int hl = grid.length;
        int vl = grid[0].length;
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{h, v});
        while (!deque.isEmpty()) {
            int[] node = deque.poll();
            h = node[0];
            v = node[1];
            if (h >= 0 && h < hl && v >= 0 && v < vl && grid[h][v] == '1') {
                grid[h][v] = '0';
                deque.offer(new int[]{h + 1, v});
                deque.offer(new int[]{h - 1, v});
                deque.offer(new int[]{h, v + 1});
                deque.offer(new int[]{h, v - 1});
            }
        }
    }


}
