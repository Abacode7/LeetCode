package com.company;

import java.util.LinkedHashMap;

public class LC0146_LRUCache {
    public static void main(String[] args){
        // Write sample
    }

    class LRUCache {
        // Solution 2
        private LinkedHashMap<Integer, Integer> map;
        private int MAX_CAPACITY;

        public LRUCache(int capacity) {
            this.MAX_CAPACITY = capacity;
            map = new LinkedHashMap<>(MAX_CAPACITY);
        }

        public int get(int key) {
            Integer value = map.remove(key);
            if(value == null) return -1;

            map.put(key, value);
            return value;
        }

        // Manual removal
        public void put(int key, int value) {
            if(map.containsKey(key)){
                map.remove(key);
            }

            if(map.size() >= MAX_CAPACITY){
                map.remove(map.keySet().iterator().next());
            }
            map.put(key, value);
        }
    }
}
