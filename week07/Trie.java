package com.thunisoft.algorithm;


/**
 * 题目概述： 实现 Trie (前缀树)
 * leetcode链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/#/description
 */
public class Trie {
    private class TrieNode {
        private TrieNode[] links;
        public static final int R = 26;
        boolean isEnd;

        public TrieNode() {
            this.links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (!trieNode.containsKey(curr)) {
                trieNode.put(curr, new TrieNode());
            }
            trieNode = trieNode.get(curr);
        }
        trieNode.setEnd(true);
    }

    public TrieNode searchPrefix(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (trieNode.containsKey(curr)) {
                trieNode = trieNode.get(curr);
            } else {
                return null;
            }
        }
        return trieNode;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode trieNode = searchPrefix(word);
        return trieNode != null && trieNode.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode trieNode = searchPrefix(prefix);
        return trieNode != null;
    }
}
