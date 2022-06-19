package com.company;

public class LC1701_AverageWaitingTime {
    public static void main(String[] args){
        int[][] customers = {{1,2},{2,5},{4,3}};
        System.out.println(averageWaitingTime(customers));
    }

    public static double averageWaitingTime(int[][] customers) {
        double totalWaitTime = 0;
        int startTime = customers[0][0];

        int i = 0;
        while(i < customers.length){
            if(startTime < customers[i][0]) startTime = customers[i][0];
            totalWaitTime += startTime - customers[i][0] + customers[i][1];
            startTime += customers[i][1];
            i++;
        }
        return totalWaitTime/customers.length;
    }
}
