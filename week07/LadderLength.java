package com.thunisoft.algorithm;

import java.util.*;

/**
 * 题目概述： 单词接龙
 * leetcode链接：https://leetcode-cn.com/problems/word-ladder/
 * 解题思路：两个单词更改一个字母就可以互相转化，说明它们之间有一个双向边，多个单词互相转化构成了一个无向图，也就是说在这个无向图中找最短路径
 *      * 需要注意的是：我们需要判断每个单词转化一个字母后在字典序列中能否找到，直至endWord，否则就是这个无向图无法构成，那就返回0。
 *      * 每个单词的长度是一样的，构建这个无向图的时间复杂度是O(n*wordLength)  n是wordList的大小。
 *      * 因为每个单词的变化只会修改一个字母，这个一个字母肯定是在26个之间，也就是单词的变化的时间复杂度是O(26*wordLength),使用hash将
 *      * wordList存储起来，这样构建这个无向图和每个字典中的单词对比的时间就为O(1),那么构建这个无向图的时间复杂度就是O(26*wordLength),
 *      * 常数忽略，也就是O(wordLength).
 */
public class LadderLength {
    /**
     * 解法1：BFS
     * 时间复杂度：O(wordLength*n) ：广度优先遍历的O(n)*构造无向图的O(wordLength)
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> visitedWord = new HashSet<>();
        visitedWord.add(beginWord);

        Deque<String> dequeWord = new LinkedList<>();
        dequeWord.offer(beginWord);
        int res = 1;

        while (!dequeWord.isEmpty()) {
            int size = dequeWord.size();
            for (int i = 0; i < size; i++) {
                String currWord = dequeWord.poll();
                if (findEndWord(currWord, endWord, wordSet, visitedWord, dequeWord)) {
                    return res + 1;
                }
            }
            res++;
        }
        return 0;
    }

    private boolean findEndWord(String currWord, String endWord, Set<String> wordSet, Set<String> visitedWord, Deque<String> dequeWord) {
        char[] currWordArray = currWord.toCharArray();
        for (int i = 0; i < currWordArray.length; i++) {
            char originalChar = currWordArray[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (originalChar == j) {
                    continue;
                }
                currWordArray[i] = j;
                String nextWord = String.valueOf(currWordArray);
                if (wordSet.contains(nextWord)) {
                    if (endWord.equals(nextWord)) {
                        return true;
                    }

                    if (!visitedWord.contains(nextWord)) {
                        dequeWord.offer(nextWord);
                        visitedWord.add(nextWord);
                    }
                }
            }
            currWordArray[i] = originalChar;
        }
        return false;
    }


    /**
     * 解法2：双向BFS,可以使用双向BFS的原因是因为起点和终点是确定的，
     *      双向BFS优于BFS的原因是因为由顶点的扩散都是越来越大的，那么由两个顶点分别扩散就会省时（面积减小为两个三角形相交的菱形）
     * 时间复杂度：O(wordLength*n) ：这里就简单认为它是优于BFS的O(wordLength*n)即可
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> visitedWord = new HashSet<>();
        visitedWord.add(beginWord);


        //这里使用两个set来代替BFS中的队列
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int res = 1;

        while (!beginVisited.isEmpty()&&!endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }

            Set<String> nextLevelVisited = new HashSet<>();
            for (String currWord : beginVisited) {
                if (findEndWord(currWord, endVisited, wordSet, visitedWord, nextLevelVisited)) {
                    return res + 1;
                }
            }
            beginVisited=nextLevelVisited;
            res++;
        }
        return 0;
    }

    private boolean findEndWord(String currWord, Set<String> endVisited, Set<String> wordSet, Set<String> visitedWord, Set<String> nextLevelVisited) {
        char[] currWordArray = currWord.toCharArray();
        for (int i = 0; i < currWordArray.length; i++) {
            char originalChar = currWordArray[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (originalChar == j) {
                    continue;
                }
                currWordArray[i] = j;
                String nextWord = String.valueOf(currWordArray);
                if (wordSet.contains(nextWord)) {
                    if (endVisited.contains(nextWord)) {
                        return true;
                    }

                    if (!visitedWord.contains(nextWord)) {
                        nextLevelVisited.add(nextWord);
                        visitedWord.add(nextWord);
                    }
                }
            }
            currWordArray[i] = originalChar;
        }
        return false;
    }


}
