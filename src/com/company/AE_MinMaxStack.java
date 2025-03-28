package com.company;

import java.util.ArrayList;

public class AE_MinMaxStack {
    /**
     * A Stack that also keeps track of the minimum and maximum values
     */
    static class MinMaxStack {
        private ArrayList<Integer> list;
        private ArrayList<MinMaxElement> minMaxList;

        public MinMaxStack(){
            list = new ArrayList<>();
            minMaxList = new ArrayList<>();
        }

        public int peek() {
            // Write your code here.
            int listSize = list.size();
            if(listSize <= 0) return -1;
            return list.get(listSize - 1);
        }

        public int pop() {
            // Write your code here.
            int listSize = list.size();
            if(listSize <= 0) return -1;

            int value = list.get(listSize - 1);

            list.remove(listSize - 1);
            minMaxList.remove(listSize - 1);
            return value;
        }

        public void push(Integer number) {
            // Write your code here.
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            if(!minMaxList.isEmpty()){
                MinMaxElement e = minMaxList.get(minMaxList.size() - 1);
                minValue = e.minValue;
                maxValue = e.maxValue;
            }

            minValue = Math.min(minValue, number);
            maxValue = Math.max(maxValue, number);

            list.add(number);
            minMaxList.add(new MinMaxElement(minValue, maxValue));
        }

        public int getMin() {
            // Write your code here.
            if(list.isEmpty()) return -1;
            MinMaxElement e = minMaxList.get(minMaxList.size() - 1);
            return e.minValue;
        }

        public int getMax() {
            // Write your code here.
            if(list.isEmpty()) return -1;
            MinMaxElement e = minMaxList.get(minMaxList.size() - 1);
            return e.maxValue;
        }
    }

    static class MinMaxElement {
        public int minValue;
        public int maxValue;

        public MinMaxElement(int minValue, int maxValue){
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
}
