package EasyLevelQuestions;

public class CountPrimes {
    public static void main(String[] args){
        int input = 10;
        System.out.println(countPrimes(input)); // 2 3 5 7
    }

    // Runtime O(n), Space O(n)
    public static int countPrimesImproved(int n) {
        // Sieve of eratosthenes
        // mark all values from 2 to n as prime.
        // for all primes from 2 to sqrt(n), mark
        // their multiples as non-prime.
        // count all prime values.

        if(n <= 2) return 0;

        boolean[] sieve = new boolean[n];
        for(int i=2; i<n; i++){
            sieve[i] = true;
        }

        for(int i=2; i*i <= n; i++){
            if(sieve[i]){
                for(int j = i*i; j < n; j+=i){
                    sieve[j] = false;
                }
            }
        }

        int count = 0;
        for(int i=2; i<n; i++){
            if(sieve[i]) count++;
        }
        return count;
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
