package EasyLevelQuestions;

import java.util.Arrays;

public class RotateImage {
    public static void main(String[] args){
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        // expected output = [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        rotate(matrix);
        for(int[] values: matrix){
            System.out.println(Arrays.toString(values));
        }
    }

    // Runtime O(N) where N is number of elements
    // in Matrix i.e Linear wrt elements in matrix
    // Space O(1)
    public static void rotate(int[][] matrix) {
        int N = matrix.length;

        // Find the matrix transpose
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse elements of each row
        // Or swap values across symmetric columns
        for(int i=0; i<N; i++){
            for(int j=0; j<N/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][N-1-j];
                matrix[i][N-1-j] = temp;
            }
        }
    }
}
