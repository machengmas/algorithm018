import java.util.*;

/**
 * 题目概述： 单词接龙
 * leetcode链接：https://leetcode-cn.com/problems/word-ladder/
 */
public class Solution {
    /**
     * 解题思路：两个单词更改一个字母就可以互相转化，说明它们之间有一个双向边，多个单词互相转化构成了一个无向图，也就是说在这个无向图中找最短路径
     * 需要注意的是：我们需要判断每个单词转化一个字母后在字典序列中能否找到，直至endWord，否则就是这个无向图无法构成，那就返回0。
     * 每个单词的长度是一样的，构建这个无向图的时间复杂度是O(n*wordLength)  n是wordList的大小。
     * 因为每个单词的变化只会修改一个字母，这个一个字母肯定是在26个之间，也就是单词的变化的时间复杂度是O(26*wordLength),使用hash将
     * wordList存储起来，这样构建这个无向图和每个字典中的单词对比的时间就为O(1),那么构建这个无向图的时间复杂度就是O(26*wordLength),
     * 常数忽略，也就是O(wordLength).
     * <p>
     * 时间复杂度：O(wordLength*n) ：广度优先遍历的O(n)*构造无向图的O(wordLength)
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) return 0;

        wordSet.remove(beginWord);

        Deque<String> deque = new LinkedList<>();
        deque.offer(beginWord);

        Set<String> visitedWord = new HashSet<>();
        visitedWord.add(beginWord);

        int step = 1;//记录经过了多少层
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                if (constructUndirectedGraph(deque.poll(), endWord, deque, visitedWord, wordSet)) return step + 1;
            }
            step++;
        }

        return 0;

    }

    private boolean constructUndirectedGraph(String currentWord, String endWord, Deque<String> deque, Set<String> visitedWord, Set<String> wordSet) {
        char[] currentWordArray = currentWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            char originalChar = currentWordArray[i];
            for (char a = 'a'; a <= 'z'; a++) {
                if (originalChar == a) continue;
                currentWordArray[i] = a;
                String nextWord = String.valueOf(currentWordArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) return true;
                    if (!visitedWord.contains(nextWord)) {
                        deque.offer(nextWord);
                        visitedWord.add(nextWord);
                    }
                }
            }
            currentWordArray[i] = originalChar;
        }
        return false;
    }

}
