import java.util.Arrays;

/**
 * 题目概述：有效的字母异位词
 * leetcode链接：https://leetcode-cn.com/problems/valid-anagram/
 */
public class Solution {
    /**
     * 解法1：排序后比较是否相等
     *
     * 时间复杂度：就是排序的复杂度，O(nlogn)
     * 空间复杂度：取决于排序算法，heapSort O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    /**
     * 解法2：创建一个数组，统计s字符串中每个字母出现的次数，每次出现+1；t字符串每个字母出现对应-1。
     * 最后遍历整个数组，如果数组中有不等于0的项就说明不是有效的字母异味词。
     * 数组的下标对应26个字母的顺序
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1),数组最大就是26.
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 'a']++;
            result[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) return false;
        }
        return true;
    }


}
