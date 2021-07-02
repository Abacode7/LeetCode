package EasyLevelQuestions;

public class CountPrimes {
    public static void main(String[] args){
        int input = 10;
        System.out.println(countPrimes(input)); // 2 3 5 7
    }

    // Time limit exceeded
    public static int countPrimes(int n) {
        int count = 0;
        for(int i=2; i<n; i++){
            if(isPrime(i)) count++;
        }
        return count;
    }

    public static boolean isPrime(int n){
        if(n < 2) return false;
        for(int i=2; i * i <= n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
}
