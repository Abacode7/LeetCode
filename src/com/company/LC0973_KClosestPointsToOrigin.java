package com.company;


import javafx.util.Pair;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class LC0973_KClosestPointsToOrigin {
    public static void main(String[] args){
        int[][] input = {{0, 1}, {1, 0}};
        System.out.println(kClosest(input, 2));
    }

    public static int[][] kClosest(int[][] points, int k) {
        Set<Pair<Integer, Double>> distance = new TreeSet<>(new Comparator<Pair<Integer, Double>>() {
            @Override
            public int compare(Pair<Integer, Double> o1, Pair<Integer, Double> o2) {
                int value =  Double.compare(o1.getValue(), o2.getValue());
                if(value == 0) return 1;
                return value;
            }
        });

        for(int i=0; i<points.length; i++){
            double disValue = getDistance(points[i]);
            Pair<Integer, Double> pairValue = new Pair<>(i, disValue);
            distance.add(pairValue);
        }

        int[][] result = new int[k][2];
        Iterator<Pair<Integer, Double>> iterator = distance.iterator();
        int i=0;
        while(iterator.hasNext() && i < k){
            Pair<Integer, Double> currentPair = iterator.next();

            result[i][0] = points[currentPair.getKey().intValue()][0];
            result[i][1] = points[currentPair.getKey().intValue()][1];
            i++;
        }

        return result;
    }

    public static double getDistance(int[] coordinate){
        int x = coordinate[0];
        int y = coordinate[1];

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
