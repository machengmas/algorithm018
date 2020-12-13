package com.thunisoft.algorithm;

import java.util.HashMap;

/**
 * 题目概述： LRU 缓存机制
 * 缓存淘汰策略（或者说是缓存淘汰算法），最近使用的被保留，最早使用的被淘汰，对应java中的实现有LinkedHashMap，
 * 这里我们使用HashMap+自己实现的简单的双向链表来实现LRU算法
 * 时间复杂度：O(1)  这里实现的链表的插入、删除都是O(1),HashMap的查询我们认为也是O(1)，总的时间复杂度就是O(1)
 * leetcode链接：https://leetcode-cn.com/problems/lru-cache/#/
 */
public class LRUCache {
    class Node {
        public int key, val;
        public Node pre, next;

        public Node() {

        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleLinkedList {
        private int size;
        //虚拟头、尾指针
        private Node vrHead, vrTail;

        public DoubleLinkedList() {
            //虚拟头尾指针是一个小技巧，可以减少很多判空
            vrHead = new Node();
            vrTail = new Node();
            vrHead.next = vrTail;
            vrTail.pre = vrHead;
        }

        public void addFirst(Node node) {
            node.next = vrHead.next;
            node.pre = vrHead;
            vrHead.next.pre = node;
            vrHead.next = node;
            size++;
        }

        public Node removeLast() {
            if (size == 0) {
                return null;
            }
            Node node = vrTail.pre;
            remove(node);
            return node;
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        public int size() {
            return size;
        }
    }

    private HashMap<Integer, Node> cacheMap;
    private DoubleLinkedList doubleLinkedList;
    private int capacity;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>();
        this.capacity = capacity;
        doubleLinkedList = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            //我们假定不存在返回-1
            return -1;
        }
        Node node = cacheMap.get(key);
        moveTohead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cacheMap.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            doubleLinkedList.addFirst(newNode);
            //添加缓存
            cacheMap.put(key, newNode);

            //判断是否超出容量，超出容量的话需要移除最后一个元素，并删除对应缓存
            if (doubleLinkedList.size > capacity) {
                Node lastNode = doubleLinkedList.removeLast();
                cacheMap.remove(lastNode.key);
            }
        } else {
            //即时缓存中存在这个node，也需要去更新，因为val可能变了
            node.val = value;
            cacheMap.put(key, node);

            moveTohead(node);
        }
    }

    private void moveTohead(Node node) {
        doubleLinkedList.remove(node);
        doubleLinkedList.addFirst(node);
    }

}
