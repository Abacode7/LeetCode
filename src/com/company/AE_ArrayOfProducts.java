package com.company;

public class AE_ArrayOfProducts {
    /**
     [5 5 20 40]
     [5 1 4 2]
     [40 8 8 2]
     **/
    public int[] arrayOfProducts(int[] array) {
        int n = array.length;
        int i = 0;
        int j = n-1;

        int[] forwardMultiple = new int[n], backwardMultiple = new int[n];
        while(i < n){
            forwardMultiple[i] = i-1 >=0 ? forwardMultiple[i-1] * array[i] : array[i];
            backwardMultiple[j] = j+1 < n ? backwardMultiple[j+1] * array[j] : array[j];
            i++;
            j--;
        }

        int[] result = new int[n];
        for(int k=0; k<n; k++){
            int fValue = k-1 >= 0 ? forwardMultiple[k-1] : 1;
            int bValue = k+1 < n ? backwardMultiple[k+1] : 1;
            result[k] = fValue * bValue;
        }
        // Write your code here.
        return result;
    }
}
